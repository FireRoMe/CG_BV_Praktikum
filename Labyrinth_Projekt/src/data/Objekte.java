package data;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Objekte 
{

	//ArrayList <Wand> waende = new ArrayList <Wand>();
	
	private ArrayList<Point> kanten = new ArrayList<Point>();
	private ArrayList<Point> punkte = new ArrayList<Point>();
	private ArrayList<Point> punkteRot = new ArrayList<Point>();
	private ArrayList<Point> punkteGruen = new ArrayList<Point>();
	private ArrayList<Point> punkteBlau = new ArrayList<Point>();
	
	int schwarz = 0xFF000000;
	int rot = 0xFFFF0000;
	int gruen = 0xFF00FF00;
	int blau = 0xFF0000FF;
	
	private BufferedImage img;
	Point point;
	
	public Objekte(BufferedImage img){
		this.img=img;
		findeSchwarz();
		findeRot();
		findeGruen();
		findeBlau();
	}
	
	public void findeSchwarz()
	{
		for (int x=1; x < img.getWidth(); x++)
		{
			for (int y=1; y < img.getHeight(); y++)
			{
				if (img.getRGB(x,y)==schwarz)
				{	
					int xLinks=x-1;
					int xRechts=x+1;
					int yOben=y-1;
					int yUnten=y+1;
					//System.out.println(xLinks);
					
					if ((img.getRGB(xLinks,y)==schwarz ^ img.getRGB(xRechts,y)==schwarz)
							&& (img.getRGB(x,yOben)==schwarz ^ img.getRGB(x,yUnten)==schwarz))
					{
						kanten.add(new Point(x,y));
					}
					else if (img.getRGB(xLinks,y)==schwarz ^ img.getRGB(xRechts,y)==schwarz
							^ img.getRGB(x,yOben)==schwarz ^ img.getRGB(x,yUnten)==schwarz)
					{
						kanten.add(new Point(x,y));
					}
					else if (img.getRGB(xLinks,y)!=schwarz && img.getRGB(xRechts,y)!=schwarz
							&& img.getRGB(x,yOben)!=schwarz && img.getRGB(x,yUnten)!=schwarz)
					{
						punkte.add(new Point(x,y));
					}

				}
			}
		}
		System.out.println("kanten:" + kanten);
		System.out.println("punkte:" + punkte);
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
		System.out.println("Rot:" + punkteRot);
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
		System.out.println("Grün:" + punkteGruen);
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
		System.out.println("Blau:" + punkteBlau);
	}
	
	
}