/**
 * Eine veraltete Version der GUI Klasse, die nicht funktioniert hatte.
 */

package view;
/*
import java.awt.GraphicsConfiguration;

import javax.media.j3d.*;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;
public class altesGUI extends JFrame
{
	private Appearance wandAp;
	private Appearance bodenAp;
	private int imgH;
	private int imgW;
	private SimpleUniverse universe;
	
	public altesGUI()
	{
		imgH = 200;
		imgW = 200;
		
		FensterSetup();
		AppearanceSetup();
		
		
		GraphicsConfiguration konfig = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas3d = new Canvas3D (konfig);
        
        universe = new SimpleUniverse(canvas3d);
        universe.getViewingPlatform().setNominalViewingTransform();
        
        Box wandTest = new Box(0.1f, 0.1f, 0.1f, wandAp); 
       
        Transform3D transform3d = new Transform3D();
        
        TransformGroup transW = new TransformGroup(transform3d);
        
        transW.addChild(wandTest);
        
        BranchGroup branchgroup = new BranchGroup();
        branchgroup.addChild(transW);
        
        universe.addBranchGraph(viewSetup());
        universe.addBranchGraph(planeSetup());
        universe.addBranchGraph(branchgroup);
        getContentPane().add(canvas3d);
        setVisible(true);
        
	}
	
	private BranchGroup planeSetup()
	{
		 Box plane= new Box(imgH, 0, imgW, bodenAp); 
		 Transform3D trans = new Transform3D();
		 
		 Vector3f vector = new Vector3f(-.2f,.1f , -.4f);
		 trans.setTranslation(vector);
		 
	     TransformGroup transP = new TransformGroup(trans);
	   //erlaubt es die Transformation des Objekts zu überschreiben
	     transP.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	        
	     transP.addChild(plane);
	        
	     BranchGroup branchgroup = new BranchGroup();
	     branchgroup.addChild(transP);
	     
	     return branchgroup;
	}
	
	private BranchGroup viewSetup()
	{
		BranchGroup viewBranch = new BranchGroup();
	    Transform3D viewTrans = new Transform3D();
	    
	    viewTrans.set(new Vector3f(0.0f, 0.0f, 10.0f));
	    
	    TransformGroup viewTransGroup = new TransformGroup(viewTrans);
	    
	    viewTransGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    viewTransGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    
	    ViewingPlatform viewPlat = universe.getViewingPlatform();
	    
	    
	    
	    viewTransGroup.addChild(viewPlat);
	    
	    BoundingSphere movingBounds = new BoundingSphere(new Point3d(0.0, 0.0,
	            0.0), 100.0);
	    BoundingLeaf boundLeaf = new BoundingLeaf(movingBounds);
	    
	    
	    KeyNavigatorBehavior keyNav = new KeyNavigatorBehavior(viewTransGroup);
	    keyNav.setSchedulingBounds(movingBounds);

	    viewBranch.addChild(keyNav);
	    
	    
	    return viewBranch;
	    
	}
	private void AppearanceSetup()	
	{
		wandAp = new Appearance();
		Color3f c = new Color3f(0.0f, 0.0f, 0.1f);
		ColoringAttributes ca = new ColoringAttributes(c, ColoringAttributes.NICEST);
		wandAp.setColoringAttributes(ca);
		
		bodenAp = new Appearance();
		Color3f w = new Color3f(0.1f, 0.0f, 0.1f);
		ColoringAttributes caii = new ColoringAttributes(w, ColoringAttributes.NICEST);
		bodenAp.setColoringAttributes(caii);
	}
	
	private void FensterSetup()
	{
		setTitle("Labyrinth");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(1200,800);
		setLocation(50,50);
	}
}
 */