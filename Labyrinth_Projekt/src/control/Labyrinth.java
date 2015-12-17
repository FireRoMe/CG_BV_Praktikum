/*
 * Die Labyrinth Klasse enthält die main-Methode
 * @author
 * @version 0.1
 */
package control;

import control.ObjectLoader;
import data.Objekte;
import view.GUI;

public class Labyrinth {

	/** Startet das Spiel.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImageLoader imLoad = new ImageLoader();
		Objekte o = new Objekte(imLoad.getImage());
		GUI gui = new GUI();
		Spiel spiel=new Spiel(gui);
		ObjectLoader ol = new ObjectLoader();
	}

}
