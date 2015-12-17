/**
 * Die Objekte Klasse ermittelt Koordinaten aus der Bilddatei. Diese werden unter bestimmten Kriterien verschiedenen ArrayLists zugeordnet.
 * @author: Lara Sievers
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
	
	final int schwarz = 0xFF000000;
	final int rot = 0xFFFF0000;
	final int gruen = 0xFF00FF00;
	final int blau = 0xFF0000FF;
	
	private BufferedImage img;
	private static Point playerStart;
	private static Point gameStop;
	
	public Objekte(BufferedImage img)
	{
		this.img = img;

		//medianRot();
		//medianGruen();
		findePunkte();
	}
	
/**
 * Sucht nach Punkten im Bild und fügt sie den jeweiligen ArrayLists hinzu.
 */
	public void findePunkte()
	{
		for (int x=1; x < img.getWidth(); x++)
		{
			for (int y=1; y < img.getHeight(); y++)
			{
				if (img.getRGB(x,y)==schwarz)
				{	
					punkte.add(new Point(x,y));
					/* Algorithmus war vorgesehen, um Wandverbindungen zu erkennen, wurde aus Zeitgründen aufgegeben.
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
					*/
				}
				else if (img.getRGB(x,y)==blau)
				{	
					punkteBlau.add(new Point(x,y));
				}
				else if (img.getRGB(x,y)==gruen)
				{	
					punkteGruen.add(new Point(x,y));
				}
				else if (img.getRGB(x,y)==rot)
				{	
					punkteRot.add(new Point(x,y));
					playerStart = punkteRot.get(0);		
				}
			}
		}
		//System.out.println("kanten:" + kanten);
		//System.out.println("punkte:" + punkte);
	}
	
	/**
	 * Median Methoden für punkteRot und punkteGruen um nur 1 grünen/roten Punkt zu bekommen und somit den Start-/Zielpunkt setzen zu können
	 */
	public static void medianRot()
	{
		int laengeRot = punkteRot.size();
		int indexRot = laengeRot/2;
		playerStart = punkteRot.get(indexRot);		
		//System.out.println(punkteRot.get(indexRot));
	}

	public static void medianGruen()
	{
		int laengeGruen = punkteGruen.size();
		int indexGruen = laengeGruen/2;
		gameStop = punkteGruen.get(indexGruen);
		//System.out.println(punkteGruen.get(indexGruen));
	}
	/**
	 * Getter Methoden
	 */
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
	}	
	
	public static Point getPlayerStart()
	{
		return playerStart;
	}
	
	public static Point getGameStop()
	{
		return gameStop;
	}
}