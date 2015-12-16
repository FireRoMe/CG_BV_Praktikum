package control;

import data.ObjectLoader;
import data.Objekte;
import view.GUI;

public class Labyrinth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImageLoader imLoad =new ImageLoader();
		Objekte o = new Objekte(imLoad.getImage());
		GUI gui = new GUI();
		Spiel spiel=new Spiel(gui);
		//new ObjectLoader();
	}

}
