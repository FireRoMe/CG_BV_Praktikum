package data;

import java.io.FileNotFoundException;

import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class ObjectLoader
{
	public static BranchGroup getItem(int ID)
	{
		ObjectFile obj = new ObjectFile();
		Scene loadedScene = null;
		BranchGroup loadedObject;
		switch(ID)
		{
		case 0:
			try
			{
				loadedScene = obj.load("src\\models\\Teleporter.obj");
			} catch (FileNotFoundException | IncorrectFormatException
					| ParsingErrorException e)
			{
				e.printStackTrace();
			}
			break;
		case 1:
			try
			{
				loadedScene = obj.load("src\\models\\Kompass.obj");
			} catch (FileNotFoundException | IncorrectFormatException
					| ParsingErrorException e)
			{
				e.printStackTrace();
			}
			break;
		case 2:
			try
			{
				loadedScene = obj.load("src\\models\\Beutel.obj");
			} catch (FileNotFoundException | IncorrectFormatException
					| ParsingErrorException e)
			{
				e.printStackTrace();
			}
			break;
		case 3:
			try
			{
				loadedScene = obj.load("src\\models\\Kelch.obj");
			} catch (FileNotFoundException | IncorrectFormatException
					| ParsingErrorException e)
			{
				e.printStackTrace();
			}
			break;
		case 4:
			try
			{
				loadedScene = obj.load("src\\models\\Schatzkiste.obj");
			} catch (FileNotFoundException | IncorrectFormatException
					| ParsingErrorException e)
			{
				e.printStackTrace();
			}
			break;
		case 5:
			try
			{
				loadedScene = obj.load("src\\models\\Schmuck.obj");
			} catch (FileNotFoundException | IncorrectFormatException
					| ParsingErrorException e)
			{
				e.printStackTrace();
			}
			break;
		}
		loadedObject = loadedScene.getSceneGroup();
		
		return loadedObject;
	}
}