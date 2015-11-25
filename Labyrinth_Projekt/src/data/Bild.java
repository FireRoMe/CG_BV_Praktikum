/*
 * In der Bild Klasse kann ein Bild mit den Daten aus dem Bild gespeichert werden.
 * @author
 * @version 0.1
 */
package data;

public class Bild
{
	private Position playerStartPosition;
	
	public Bild()
	{
		//TODO: Konstruktor
	}

	public Position getPlayerStartPosition()
	{
		return playerStartPosition;
	}

	public void setPlayerStartPosition(Position playerStartPosition)
	{
		this.playerStartPosition = playerStartPosition;
	}
}
