/*
 * Die Spieler Klasse beinhaltet Informationen über den Spieler.
 * @author
 * @version 0.1
 */
package data;

public class Spieler
{
	private Position currentPosition;
	private int currentScore;
	private Inventar inv;
	
	public Spieler(Position p)
	{
		currentPosition = p;
		currentScore = 0;
		inv = new Inventar();
	}
}
