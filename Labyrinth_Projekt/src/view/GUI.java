package view;

import java.awt.Container;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
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
		
		lightSetup();
		objectSetup();
		
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
		Vector3f camStart = new Vector3f(0.0f,0.0f,2.0f);
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

	private void objectSetup()
	{
		//textures
		
		TextureLoader loader = new TextureLoader("C:\\Users\\Tom\\eclipse\\CGBV_LAB\\Labyrinth_Projekt\\src\\view\\testtext.jpg", "RGB", new Container());
		Texture wallTex = loader.getTexture();
		
		wallTex.setBoundaryModeS(Texture.WRAP);
		wallTex.setBoundaryModeT(Texture.WRAP);
		
		TextureAttributes wallTexAt = new TextureAttributes();
		wallTexAt.setTextureMode(TextureAttributes.REPLACE);
		
		//cube
		Transform3D cubeTrans = new Transform3D();
		TransformGroup cubeTG = new TransformGroup();

		Appearance ap = new Appearance();
		ap.setTexture(wallTex);
		ap.setTextureAttributes(wallTexAt);
		
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		
		Box cube = new Box(0.5f, 2.5f, 0.5f, primflags, ap);
		Vector3f cubePos = new Vector3f(0.5f, 0.1f, -0.5f);
		
		cubeTrans.setTranslation(cubePos);
		
		cubeTG.setTransform(cubeTrans);
		cubeTG.addChild(cube);
		
		bG.addChild(cubeTG);
		
	}
	
	private void lightSetup()
	{		
		Color3f lightcolor = new Color3f(1.8f, 1.8f, 0.5f);
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


