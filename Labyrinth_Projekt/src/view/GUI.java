/*
 * Die GUI Klasse beinhaltet die graphische Anzeige.
 * @author
 * @version 0.1
 */
package view;

import java.awt.GraphicsConfiguration;

import javax.media.j3d.*;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.SimpleUniverse;
public class GUI extends JFrame
{
	private Appearance wandAp;
	private Appearance bodenAp;
	private int imgH;
	private int imgW;
	
	public GUI()
	{
		imgH = 200;
		imgW = 200;
		
		FensterSetup();
		AppearanceSetup();
		
		GraphicsConfiguration konfig = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas3d = new Canvas3D (konfig);
        
        
        SimpleUniverse universe = new SimpleUniverse(canvas3d);
        universe.getViewingPlatform().setNominalViewingTransform();
        
        Box wandTest = new Box(0.1f, 0.1f, 0.1f, wandAp); 
       
        Transform3D transform3d = new Transform3D();
        
        TransformGroup transW = new TransformGroup(transform3d);
        
        transW.addChild(wandTest);
        
        BranchGroup branchgroup = new BranchGroup();
        branchgroup.addChild(transW);
        
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
	     
	        
	     transP.addChild(plane);
	        
	     BranchGroup branchgroup = new BranchGroup();
	     branchgroup.addChild(transP);
	     
	     return branchgroup;
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
