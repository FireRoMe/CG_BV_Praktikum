/*
 * Das Fenster beinhaltet das GUI.
 * @author
 * @version 0.1
 */
package view;

import javax.swing.JFrame;

public class Fenster
{
	private int groesseX;
	private int groesseY;

	public Fenster()
	{
		//TODO: Konstruktor 
		JFrame frm = new JFrame();
		frm.setTitle("Labyrinth");

		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frm.setSize(300,200);
		frm.setLocation(50,50);
		frm.setVisible(true);
	}		
	
}