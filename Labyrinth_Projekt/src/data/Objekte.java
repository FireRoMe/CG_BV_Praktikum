package data;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Objekte 
{

	//ArrayList <Wand> waende = new ArrayList <Wand>();
	
	private ArrayList<Point> punkteRot = new ArrayList<Point>();
	private ArrayList<Point> punkteGruen = new ArrayList<Point>();
	private ArrayList<Point> punkteBlau = new ArrayList<Point>();
	
	int rot = 0xFFFF0000;
	int gruen = 0xFF00FF00;
	int blau = 0xFF0000FF;
	
	private BufferedImage img;
	Point point;
	
	public Objekte(BufferedImage img){
		this.img=img;
		findeBlau();
	}

	public void findeBlau()
	{
		for (int x=1; x < img.getWidth(); x++)
		{
			for (int y=1; y < img.getHeight(); y++)
			{
				if (img.getRGB(x,y)==blau)
				{	
						punkteBlau.add(new Point(x,y));
				}
			}
		}
		System.out.print(punkteBlau);
	}
}