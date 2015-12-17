package view;

import data.Objekte;
import data.ImageLoader;
import data.ObjectLoader;

import java.awt.Container;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
import javax.media.j3d.Node;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.media.j3d.VirtualUniverse;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

import data.ImageLoader;
import data.Objekte;

@SuppressWarnings("serial")
public class GUI extends JFrame
{
	private VirtualUniverse vU;
	private BranchGroup bG;
	private BranchGroup viewBG;
	private Canvas3D cv3d;
	private ViewPlatform camera;
	private KeyNavigatorBehavior keybhv;

	/* Alte Main Methode zum Testen des GUIs
	public static void main (String[] args)
	{
		@SuppressWarnings("unused")
		GUI gui = new GUI();
	}
	*/
	public GUI()
	{		
		this.setSize(1024, 768);
		
		vU = new VirtualUniverse();
		Locale loc = new Locale(vU);
		
		bG = new BranchGroup();
		viewBG = new BranchGroup();
		viewBG.addChild(setUpCamera());
		cv3d = createCanvas();
		
		
		objectSetup();
		itemSetup();
		planeSetup();
		lightSetup();
		
		viewBG.addChild(keybhv);
		bG.addChild(viewBG);
		
		loc.addBranchGraph(bG);
		
		View view = new View();
		view.setBackClipDistance(2000.0);
		view.setPhysicalBody(new PhysicalBody());
		view.setPhysicalEnvironment(new PhysicalEnvironment());
		view.addCanvas3D(cv3d);
		view.attachViewPlatform(camera);	
		
		this.getContentPane().add(cv3d);
		this.setVisible(true);
	}
	
	private TransformGroup setUpCamera()
	{	
		camera = new ViewPlatform();
		
		float startX = (float) Objekte.getPlayerStart().getX() *5;
		float startY = (float) Objekte.getPlayerStart().getY() *5;

		Vector3f camStart = new Vector3f(startX,7.5f,startY);
		TransformGroup viewTrans = new TransformGroup();
	
		
		viewTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		viewTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		viewTrans.setCapability(TransformGroup.ALLOW_LOCAL_TO_VWORLD_READ);
		
		Transform3D startTrans = new Transform3D();
		startTrans.setTranslation(camStart);
		
		viewTrans.setTransform(new Transform3D(startTrans));
		viewTrans.addChild(camera);
		
		keybhv = new KeyNavigatorBehavior(viewTrans);
		keybhv.setSchedulingBounds(new BoundingSphere(new Point3d(), 500.0));
		
		return viewTrans;
	}

	private void planeSetup()
	{
		Transform3D bodenTrans = new Transform3D();
		TransformGroup bodenTG = new TransformGroup();

		float planeY = (float) ImageLoader.getImageHeight() *10f;
		float planeX = (float) ImageLoader.getImageWidth() *10f;
		
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
		
		bG.addChild(bodenTG);
	}

	private void objectSetup()
	{
		//textures
		
		TextureLoader loader = new TextureLoader("src\\view\\testtext.jpg", "RGB", new Container());
		Texture wallTex = loader.getTexture();
		
		wallTex.setBoundaryModeS(Texture.WRAP);
		wallTex.setBoundaryModeT(Texture.WRAP);
		
		TextureAttributes wallTexAt = new TextureAttributes();
		wallTexAt.setTextureMode(TextureAttributes.REPLACE);
		
		//Wände
		Appearance ap = new Appearance();
		ap.setTexture(wallTex);
		ap.setTextureAttributes(wallTexAt);
		
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		
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
			Vector3f cubePos = new Vector3f(x,10.0f,y);
			
			cubeTrans.setTranslation(cubePos);
			
			cubeTG.setTransform(cubeTrans);
			cubeTG.addChild(cube);
			bG.addChild(cubeTG);
		}
	}
	
	private void itemSetup()
	{
		ArrayList<Point> p = Objekte.getPunkteBlau();
		System.out.println("Angekommen");

		if (p.size() != 0)
		{
			System.out.println("size ist nicht 0");
			for(int i = 0; i < p.size(); i++)
			{
				TransformGroup tg = new TransformGroup();
				tg.addChild(ObjectLoader.getItem(5));
				
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
	
	private void lightSetup()
	{		
		Color3f lightcolor = new Color3f(1f, 1f, 1f);
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		Vector3f light1dir = new Vector3f(4.0f, -7.0f, -12.0f);
		
		DirectionalLight light = new DirectionalLight(lightcolor, light1dir);
		light.setInfluencingBounds(bounds);
		bG.addChild(light);
		
		Color3f aLightColor = new Color3f(1.0f, 1.0f, 1.0f);
		AmbientLight aLight = new AmbientLight(aLightColor);
		aLight.setInfluencingBounds(bounds);
		bG.addChild(aLight);
	}
	
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


