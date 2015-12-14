package data;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Wand
{
	//private ArrayList<int[]> koordinaten = new ArrayList<int[]>();  Was ist das? So funktionieren ArrayLists nicht
	
	private ArrayList<Point> kanten = new ArrayList<Point>();
	private ArrayList<Point> punkte = new ArrayList<Point>();
	int schwarz = 0xFF000000;
	
	private BufferedImage img;
	Point point;
	//private BufferedImage bild;
	
	public Wand(BufferedImage img){
		this.img=img;
		findeKoordinaten();
	}
	
	//bild = new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB);
	

	/*public void addCoordinates(int i, int j)
	{
		int[] x = new int[2];
		x[0] = i;
		x[1] = j;
		koordinaten.add(x);
		
		System.out.println(koordinaten.toString());
	}*/

	public void findeKoordinaten()
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
		System.out.print(kanten);
		System.out.print(punkte);
	}
}
