/*
 * Die ImageLoader Klasse ermöglicht es, Bilder zu laden und zu verarbeiten.
 * @author
 * @version 0.1
 */

package control;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import data.*;

public class ImageLoader
{
	private BufferedImage b;
	private BufferedImage afterFilter;
	
	public ImageLoader()
	{
		//TODO: Konstruktor
	}
	
	public void loadImage()
	{
		try 
		{
		    b = ImageIO.read(new File("C:\\Users\\Tom\\Desktop\\HottesBeispiel.jpg"));
		    System.out.println("Bild geladen");
		} catch (IOException e) {
			System.out.println("Fehler");
			}
	}
	
	public void filter()
	{
		for(int i=0; i < b.getHeight(); i++)
		{
			for(int j=0; j < b.getWidth(); j++)
			{
				int currentRGB = b.getRGB(i, j);
				Color c = new Color(currentRGB);
				
				if (c.getGreen() < 130)
				{
					
				}
				else
				
				if (c.getRed() < 130)
				{
					
				}
				else 
					
				if (c.getBlue() < 130)
				{
					
				}
				else 
					
			}
			System.out.println("");
		}
	}
	
	
	public void analyzeImage()
	{
		//TODO: Bild analysieren und Daten speichern
		for(int i=0; i < b.getHeight(); i++)
		{
			for(int j=0; j < b.getWidth(); j++)
			{
				int currentRGB = b.getRGB(i, j);
				Color c = new Color(currentRGB); 
				if(isWhite(c))
				{
					System.out.print("W");
				}
				else if (isBlack(c))
				{
					System.out.print("S");
				}
			}
			System.out.println("");
		}
	}

	public 
	
	
	public boolean isWhite(Color c)
	{
		boolean b = false;
		if(c.getBlue() >= 200 && c.getGreen() >= 200 && c.getRed() >= 200)
		{
			b = true;
		}
		return b;
	}
	
	public boolean isBlack(Color c)
	{
		boolean b = false;
		if(c.getBlue() <= 50 && c.getGreen() <= 50 && c.getRed() <= 50)
		{
			b = true;
		}
		return b;
	}
}