package control;

import data.ObjectLoader;
import view.GUI;

public class Labyrinth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUI gui=new GUI();
		Spiel spiel=new Spiel(gui);
		new ObjectLoader();
	}

}
