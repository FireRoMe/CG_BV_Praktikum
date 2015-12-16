/*
 * Die Spiel Klasse Kontrolliert den Ablauf des Spiels.
 * @author
 * @version 0.1
 */
package control;
import java.awt.image.BufferedImage;

import data.*;
import view.*;

public class Spiel 
{
	private Spieler currentPlayer;
	private BufferedImage currentImage;
	ImageLoader imageLoader;
	
	
	public Spiel(GUI g)
	{
		//ImageLoader load = new ImageLoader();
		
		//load.filter();
		imageLoader=new ImageLoader();
		//Wand w = new Wand(imageLoader.getImage());
		Objekte o = new Objekte(imageLoader.getImage());
		Item i = new Item();
	}
	
	public void texturenLaden()
	{
		//TODO: Texturen Laden. lol
	}
	
	public void spielerLaden()
	{
		currentPlayer = new Spieler(currentImage.getPlayerStartPosition());
	}
	
	public void bildLaden()
	{
		currentImage = imageLoader.getImage();
	}
}
