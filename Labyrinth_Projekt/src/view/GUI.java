/**
 * Die GUI Klasse kontrolliert den Graphischen Output.
 * @author Tom Quinders
 * @version 0.0.8
 */

package view;

import data.Objekte;
import data.ImageLoader;
import data.ObjectLoader;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Locale;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.media.j3d.VirtualUniverse;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;

import control.Spiel;

@SuppressWarnings("serial")
public class GUI extends JFrame
{
	private VirtualUniverse vU;
	private BranchGroup bG;
	private BranchGroup viewBG;
	private Canvas3D cv3d;
	private ViewPlatform camera;
	private KeyNavigatorBehavior keybhv;

	JPanel mainPanel;
	/**
	 * Konstruktor, der das Fenster initialisiert und Buttons für "Start" und "Hilfe" bereitstellt.
	 * Der Button "Start" führt starteSpiel() aus.
	 */
	public GUI()
	{		
		this.setSize(1024, 768);
		mainPanel = new JPanel(new FlowLayout());
		JButton startButton = new JButton("Start");
		JButton helpButton = new JButton("Hilfe");
		
		mainPanel.add(startButton);
		mainPanel.add(helpButton);
		this.getContentPane().add(mainPanel);
		this.setVisible(true);
		
		startButton.addActionListener (new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				starteSpiel();
			}	
		});
		helpButton.addActionListener (new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				hilfeAnzeigen();
			}
		});
	}
	/**
	 * Startet das Spiel durch klicken des "Start" Buttons.
	 * Erstellt ein Universe und führt Methoden aus, um die 3D Umgebung zu erzeugen.
	 */
	private void starteSpiel() 
	{
		Spiel s = new Spiel();
		this.getContentPane().removeAll();
		vU = new VirtualUniverse();
		Locale loc = new Locale(vU);
		
		bG = new BranchGroup();
		viewBG = new BranchGroup();
		viewBG.addChild(setUpCamera());
		cv3d = createCanvas();
		
		//Setups für Objekte in der Szene und Lichter
		
		objectSetup();
		itemSetup();
		planeSetup();
		lightSetup();

		viewBG.addChild(keybhv);
		bG.addChild(viewBG);
		loc.addBranchGraph(bG);

		//View Setup
		
		View view = new View();
		view.setBackClipDistance(5000.0);
		view.setPhysicalBody(new PhysicalBody());
		view.setPhysicalEnvironment(new PhysicalEnvironment());
		view.addCanvas3D(cv3d);
		view.attachViewPlatform(camera);

		//Alles zum Fenster hinzufügen
		
		this.getContentPane().add(cv3d);
		this.getContentPane().validate();
	}
	
	private void hilfeAnzeigen() 
	{
		this.getContentPane().removeAll();
		JTextArea hilfeText = new JTextArea("Das kann auch gerne wer anders schreiben.",10,100);
		final int SCROLLBARS_NONE;
		hilfeText.setEditable(false);
		this.getContentPane().add(hilfeText);
		this.getContentPane().validate();
		
	}		
		
	/**
	 * Kamera Setup, erstellt eine TransformGroup für die Kamera
	 * @return gibt die TransformGroup für die Kamera zurück
	 */
	private TransformGroup setUpCamera()
	{	
		camera = new ViewPlatform();
		
		//Nimmt die PlayerStartPosition aus der Objekte Klasse, die aus dem Bild extrahiert wurde
		//und modifiziert sie, um sie für den Kamera Startpunkt zu benutzen
		float startX = (float) Objekte.getPlayerStart().getX() *5;
		float startY = (float) Objekte.getPlayerStart().getY() *5;

		Vector3f camStart = new Vector3f(startX,7.5f,startY);
		TransformGroup viewTrans = new TransformGroup();
		
		//erlaubt der Kamera sich zu bewegen
		viewTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		viewTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		viewTrans.setCapability(TransformGroup.ALLOW_LOCAL_TO_VWORLD_READ);
		
		Transform3D startTrans = new Transform3D();
		startTrans.setTranslation(camStart);
		viewTrans.setTransform(new Transform3D(startTrans));
		viewTrans.addChild(camera);
		
		//fügt der KameraGruppe den Navigator hinzu
		keybhv = new KeyNavigatorBehavior(viewTrans);
		keybhv.setSchedulingBounds(new BoundingSphere(new Point3d(), 1500.0));
		
		return viewTrans;
	}

	/**
	 * Erstellt den Boden und fügt ihn der Gesamtszene hinzu
	 */
	private void planeSetup()
	{
		Transform3D bodenTrans = new Transform3D();
		TransformGroup bodenTG = new TransformGroup();

		//modifiziert die Größe des Bodens
		float planeY = (float) ImageLoader.getImageHeight() *10f;
		float planeX = (float) ImageLoader.getImageWidth() *10f;
		
		//erstellt eine Appearance für den Boden
		Color3f c3f = new Color3f(0.3f, 0.3f, 0.3f);
		Appearance ap = new Appearance();
		ColoringAttributes cA = new ColoringAttributes(c3f, ColoringAttributes.NICEST);
		ap.setColoringAttributes(cA);
				
		Box plane = new Box(planeX, 0.0f, planeY, ap);
		
		//float planeYPos = planeY/2;
		//float planeXPos = planeX/2;
		
		Vector3f planePos = new Vector3f(0, 0, 0);
		bodenTrans.setTranslation(planePos);
		bodenTG.addChild(plane);
		bodenTG.setTransform(bodenTrans);
		//fügt den Boden der Gesamtszene hinzu
		bG.addChild(bodenTG);
	}

	/**
	 * Lädt die Wände und die Texturen für die Wände
	 */
	private void objectSetup()
	{
<<<<<<< HEAD
		//Lädt die Textur		
		TextureLoader loader = new TextureLoader("src\\view\\testtext.jpg", "RGB", new Container());
=======
		//textures
		
		TextureLoader loader = new TextureLoader("src\\view\\Textur.jpg", "RGB", new Container());
>>>>>>> 739436b691ef564d5d43cedbf8034d877cdac057
		Texture wallTex = loader.getTexture();
		
		wallTex.setBoundaryModeS(Texture.WRAP);
		wallTex.setBoundaryModeT(Texture.WRAP);
		
		TextureAttributes wallTexAt = new TextureAttributes();
		wallTexAt.setTextureMode(TextureAttributes.REPLACE);
		
		Appearance ap = new Appearance();
		ap.setTexture(wallTex);
		ap.setTextureAttributes(wallTexAt);
		
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		
		//Erstellt die Wände als Boxen für jeden schwarzen Pixel
		ArrayList<Point> p = Objekte.getPunkte();
		
		float xToZero = (float) ((float) 0-p.get(0).getX()*10);
		float yToZero = (float) ((float) 0-p.get(0).getY()*10);
		
		for(int i = 0; i < p.size(); i++)
		{
			Transform3D cubeTrans = new Transform3D();
			TransformGroup cubeTG = new TransformGroup();
			
			Point po = p.get(i);
			float x = (float) po.getX();
			float y = (float) po.getY();
			
			x = x*10;
			y = y*10;
			x = x + xToZero;
			y = y + yToZero;
			
			Box cube = new Box(5.0f, 20.0f, 5.0f, primflags, ap);
			cube.setCollidable(true);
			Vector3f cubePos = new Vector3f(x,10.0f,y);
			
			cubeTrans.setTranslation(cubePos);
			cubeTG.setTransform(cubeTrans);
			cubeTG.addChild(cube);
			//Fügt die einzelnen Wände der Szene hinzu
			bG.addChild(cubeTG);
		}
	}

	/**
	 * Lädt Items für die jeweiligen blauen Punkte auf dem Bild.
	 */
	private void itemSetup()
	{
		ArrayList<Point> p = Objekte.getPunkteBlau();
		
		if (p.size() != 0)
		{
			//System.out.println(p.size());
			//System.out.println("Items sind vorhanden");
			for(int i = 0; i < p.size(); i++)
			{
				TransformGroup tg = new TransformGroup();
				tg.addChild(ObjectLoader.getItem(0));
				
				float Y = (float) p.get(i).getY();
				float X = (float) p.get(i).getX();
				X = X*5;
				Y = Y*5;
				
				Vector3f itemPos = new Vector3f(X, 0.0f, Y);
				Transform3D t3d = new Transform3D();
				t3d.setTranslation(itemPos);
				tg.setTransform(t3d);
				
				bG.addChild(tg);
			}
		}
	}
	
	/**
	 * Erstellt die Lichter für die Szene
	 */
	private void lightSetup()
	{		
		//erstellt ein neues Directional Light
		Color3f lightcolor = new Color3f(1f, 1f, 1f);
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 1000.0);
		Vector3f light1dir = new Vector3f(4.0f, -7.0f, -12.0f);
		
		DirectionalLight light = new DirectionalLight(lightcolor, light1dir);
		light.setInfluencingBounds(bounds);
		bG.addChild(light);
		//erstellt ein neues Ambient Light
		AmbientLight aLight = new AmbientLight(lightcolor);
		aLight.setInfluencingBounds(bounds);
		bG.addChild(aLight);
	}
	
	/**
	 * Erstellt den Canvas.
	 * @return Canvas
	 */
	private Canvas3D createCanvas()
	{
		GraphicsConfigTemplate3D template = new GraphicsConfigTemplate3D();
        template.setDoubleBuffer(GraphicsConfigTemplate3D.REQUIRED);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice dev = env.getDefaultScreenDevice();
        Canvas3D canvas = new Canvas3D(dev.getBestConfiguration(template));     
        canvas.setDoubleBufferEnable(true);	

        return canvas;
	}
}


