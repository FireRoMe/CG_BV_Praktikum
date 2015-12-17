/*
 * Items.
 * @author
 * @version 0.1
 */

package data;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Item{

	private static Set funktionsItems = new HashSet(); // Gedanke dahinter: Wir haben 5 Items die einmalig seinen können und 5 die auch doppelt vorkommen können. Ein HashSet erlaubt keine doppelten einträge. Wenn jemand ne bessere Idee hat darf er sich gerne melden 
	static ArrayList<Integer> punkteItems = new ArrayList<Integer>(); // hier kommen die Zufallswerte von 5-9 rein, da diese auch doppelt sein können

	public Item ()
	{
		generiereItem();
	}
	
	int id;
 	private int score;
	
	public int getId()
	{
		return id;
	}
	
	public static void generiereItem()
	{
		int randomID;
		int anzahl = Objekte.getPunkteBlau().size();
		
		for (int i=0; i <= anzahl; i++) //die ArrayList kommt morgen. Soll 1 Punkt aus jeder "Gruppe" von blauen Punkten enthalten
		{
			randomID = (int) (Math.random() * 6);
		
		if (randomID < 2)
			funktionsItems.add (randomID);

		else 
			punkteItems.add (randomID);
		}
		System.out.println("FI:" + funktionsItems + "PI:" + punkteItems);
		//platziere funktionsItems und punkteItems auf den in ItemPositionen angegebenen Koordinaten
	}
}
