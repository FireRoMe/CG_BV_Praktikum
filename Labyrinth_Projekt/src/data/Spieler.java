/*
 * Die Spieler Klasse beinhaltet Informationen �ber den Spieler.
 * @author Tom Quinders
 */
package data;

public class Spieler
{
	private static int aktuellerPunktestand;
	//private Inventar inv;
	@SuppressWarnings("unused")
	private String name;
	
	public Spieler()
	{
		
	}
	
	public Spieler(String string)
	{
		name = string;
		aktuellerPunktestand = 0;
		//inv = new Inventar();
	}

	public int getaktuellerPunktestand()
	{
		return aktuellerPunktestand;
	}
	
	public static void punktestandErh�hen(int score)
	{
		aktuellerPunktestand = aktuellerPunktestand + score;
	}
}
