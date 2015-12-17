/**
 * Die Labyrinth Klasse enthält die main-Methode
 * @author Tom Quinders
 */
package control;

import data.ImageLoader;
import data.Objekte;
import view.GUI;

public class Labyrinth 
{
	/** Die Labyrinth Klasse Startet das Programm.
	 */
	public static void main(String[] args) 
	{
		/** Initialisiert den ImageLoader, der das Bild einlädt und verarbeitet
		 */
		ImageLoader imLoad = new ImageLoader();
		/** Initialisiert die Objekt, die aus dem ImageLoader gelesen werden
		 */
		@SuppressWarnings("unused")
		Objekte o = new Objekte(imLoad.getImage());
		/** Initialisiert das GUI
		 */
		@SuppressWarnings("unused")
		GUI gui = new GUI();
		/** Initialisiert das Spiel und übergibt ihm das GUI
		 */
	}
}
