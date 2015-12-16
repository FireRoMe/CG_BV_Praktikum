/*
 * Items.
 * @author
 * @version 0.1
 */

package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class Item{

	private static Set funktionsItems = new HashSet(); // Gedanke dahinter: Wir haben 5 Items die einmalig seinen können und 5 die auch doppelt vorkommen können. Ein HashSet erlaubt keine doppelten einträge. Wenn jemand ne bessere Idee hat darf er sich gerne melden 
	static ArrayList<Integer> punkteItems = new ArrayList<Integer>(); // hier kommen die Zufallswerte von 5-9 rein, da diese auch doppelt sein können

	int id;
 	private int score;
	
	public int getId(){
		return id;
	}
	public static void generiereItem()
	{
		int zahl;
		
		for (int i=0; i <= itemPosition.size(); i++) //die ArrayList kommt morgen. Soll 1 Punkt aus jeder "Gruppe" von blauen Punkten enthalten
		{
		zahl = (int) (Math.random() * 9);
		
		if (zahl <= 5)
			funktionsItems.add (zahl);

		else 
			punkteItems.add (zahl);
		}
	}
}
