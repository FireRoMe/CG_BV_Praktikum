/**
 * Die Spiel Klasse Kontrolliert den Ablauf des Spiels.
 * @author Jaqueline Timmermann
 */
package control;
import data.*;

public class Spiel 
{
	@SuppressWarnings("unused")
	private Spieler currentPlayer;
	
	public Spiel()
	{
		spielerLaden();
	}
	
	/*
	 * Erstellt einen neuen Spieler.
	 */
	public void spielerLaden()
	{
		currentPlayer = new Spieler("Spieler 1");
	}
}
