/**
 * ObjectLoader wird benutzt, um .obj Dateien für die Items zu laden.
 * 
 *  @author Tom Quinders
 *  @version 1.0.0
 */
package data;

import java.io.FileNotFoundException;

import javax.media.j3d.BranchGroup;
import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;

public class ObjectLoader
{
	/**
	 * Methode, die aus einer .obj Datei die gewünschten Items lädt
	 * @param ID des Items, das geladen werden soll
	 * @return BranchGroup, die das neu geladene Item enthält
	 */
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
				loadedScene = obj.load("src\\models\\Beutel.obj");
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
				loadedScene = obj.load("src\\models\\Teleporter.obj");
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