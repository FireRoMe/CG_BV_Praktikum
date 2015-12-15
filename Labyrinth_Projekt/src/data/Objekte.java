package data;

<<<<<<< HEAD
import java.util.ArrayList;

public class Objekte {
=======
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
>>>>>>> 0941df79b50098e01097775c4b399f50a4633b76

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
		findeRot();
		findeGruen();
		findeBlau();
	}
	
	public void findeRot()
	{
		for (int x=1; x < img.getWidth(); x++)
		{
			for (int y=1; y < img.getHeight(); y++)
			{
				if (img.getRGB(x,y)==rot)
				{	
						punkteRot.add(new Point(x,y));
				}
			}
		}
		System.out.println(punkteRot);
	}

	public void findeGruen()
	{
		for (int x=1; x < img.getWidth(); x++)
		{
			for (int y=1; y < img.getHeight(); y++)
			{
				if (img.getRGB(x,y)==gruen)
				{	
						punkteGruen.add(new Point(x,y));
				}
			}
		}
		System.out.println(punkteGruen);
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
		System.out.println(punkteBlau);
	}
	
	
}
