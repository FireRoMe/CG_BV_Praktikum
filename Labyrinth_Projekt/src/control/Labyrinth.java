/*
 * Die Labyrinth Klasse enth�lt die main-Methode
 * @author
 * @version 0.1
 */
package control;

import data.ImageLoader;

import data.Objekte;
import view.GUI;

public class Labyrinth {

	/** Startet das Spiel.
	 */
	public static void main(String[] args) {

		//Initialisiert den ImageLoader
		ImageLoader imLoad = new ImageLoader();
		//Initialisiert die Objekt, die aus dem ImageLoader gelesen werden
		Objekte o = new Objekte(imLoad.getImage());
		//Initialisiert das GUI
		GUI gui = new GUI();
		//Initialisiert das Spiel und �bergibt ihm das GUI
		Spiel spiel=new Spiel(gui);
	}

}
