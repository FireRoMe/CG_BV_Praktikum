/*
 * Die Objekte Klasse ermittelt Koordinaten aus der Bilddatei. Diese werden unter bestimmten Kriterien verschiedenen ArrayLists zugeordnet.
 * @author: Lara Sievers
 * @version 0.9
 */
package data;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Objekte 
{

	//ArrayList <Wand> waende = new ArrayList <Wand>();
	
	private static ArrayList<Point> kanten = new ArrayList<Point>();
	private static ArrayList<Point> punkte = new ArrayList<Point>();
	private static ArrayList<Point> punkteRot = new ArrayList<Point>();
	private static ArrayList<Point> punkteGruen = new ArrayList<Point>();
	private static ArrayList<Point> punkteBlau = new ArrayList<Point>();
	
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
		medianRot();
		medianGruen();
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
	
	//Median-Methoden für punkteRot und punkteGruen um nur 1 grünen/roten Punkt zu bekommen und somit den Start-/Zielpunkt setzen zu können
	public static void medianRot()
	{
		int laengeRot = punkteRot.size();
		int indexRot = laengeRot/2;
		System.out.println(punkteRot.get(indexRot));
	}
	public static void medianGruen()
	{
		int laengeGruen = punkteGruen.size();
		int indexGruen = laengeGruen/2;
		System.out.println(punkteGruen.get(indexGruen));
	}
	/*Getter für die ArrayLists. Denke die könnten wir irgendwann brauchen...
	public static ArrayList<Point> getKanten()
	{
		return kanten;
	}
	
	public static ArrayList<Point> getPunkte()
	{
		return punkte;
	}
	
	public static ArrayList<Point> getPunkteRot()
	{
		return punkteRot;
	}
	
	public static ArrayList<Point> getPunkteGruen()
	{
		return punkteGruen;
	}
	
	public static ArrayList<Point> getPunkteBlau()
	{
		return punkteBlau;
	}	*/
}