/*
 * Die Spieler Klasse beinhaltet Informationen über den Spieler.
 * @author
 * @version 0.1
 */
package data;

public class Spieler
{
	private Position currentPosition;
	private int aktuellerPunktestand;
	private Inventar inv;
	
	public Spieler(Position p)
	{
		currentPosition = p;
		aktuellerPunktestand = 0;
		inv = new Inventar();
	}
	
	public int getaktuellerPunktestand()
	{
		return aktuellerPunktestand;
	}
	
	public static void punktestandErhöhen(int score)
	{
		this.aktuellerPunktestand = aktuellerPunktestand + score;
	}
}
