/*
 * Die Spiel Klasse Kontrolliert den Ablauf des Spiels.
 * @author
 * @version 0.1
 */
package control;
import data.*;

public class Spiel 
{
	private Spieler currentPlayer;
	private Bild currentImage;
	
	public static void main(String[] args) 
	{
		// TODO: Spiel starten
	}
	
	public Spiel()
	{
		//TODO: Konstruktor
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
		currentImage = ImageLoader.loadImage();
	}
}
