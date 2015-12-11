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

import javax.imageio.ImageIO;

public class ImageLoader
{
	private BufferedImage b;
	private BufferedImage afterFilter;
	
	private int imageHeight;
	private int imageWidth;
	
	public ImageLoader()
	{
		//TODO: Konstruktor
	}
	
	public void loadImage()
	{
		try 
		{
<<<<<<< HEAD
		    b = ImageIO.read(new File("C:\\Users\\Tom\\Desktop\\HottesBeispiel.jpg"));
=======
		    b = ImageIO.read(new File("C:\\Users\\lara\\Desktop\\bild.jpg"));
>>>>>>> 6385614977be3fe73cdf8346af7e0e0f48d70eb7
		    System.out.println("Bild geladen");
		    System.out.println(b.getHeight());
		    System.out.println(b.getWidth());
		    setImageHeight(b.getHeight());
		    setImageWidth(b.getWidth());
		} catch (IOException e) {
			System.out.println("Fehler");
			}
	}
	
	public void filter()
	{
		afterFilter = new BufferedImage(b.getWidth(), b.getHeight(),BufferedImage.TYPE_INT_RGB);
		for(int i=0; i < b.getWidth(); i++)
		{
			//System.out.println(i);
			for(int j=0; j < b.getHeight(); j++)
			{
				//System.out.print(j);
				int currentRGB = b.getRGB(i, j);
				Color c = new Color(currentRGB);
				
				int red;
				int green;
				int blue;
				
				if(c.getRed() <= 130)
				{
					red = 0;
				}
				else
				{
					red = 255;
				}
				
				if(c.getGreen() <= 130)
				{
					green = 0;
				}
				else
				{
					green = 255;
				}
				
				if(c.getBlue() <= 80)
				{
					blue = 0;
				}
				else
				{
					blue = 255;
				}
				
				Color afterFilterColor = new Color(red,green,blue);
				int newRGB = afterFilterColor.getRGB();
				afterFilter.setRGB(i, j, newRGB);				
			}
			System.out.println("");
		}
		try {
		    // retrieve image
		    File outputfile = new File("C://Users//lara//Desktop//saved.png");
		    ImageIO.write(afterFilter, "png", outputfile);
		} catch (IOException e) {
		   
		}

	}
	
	int[][] checkMatrix;
	
	public void analyzeImage()
	{
	/*	checkMatrix = new int[afterFilter.getWidth()][afterFilter.getHeight()];
		
		for(int i=0; i < afterFilter.getWidth(); i++)
		{
			for(int j=0; j < afterFilter.getHeight(); j++)
			{
				checkMatrix[i][j] = 0;
			}
		}
		
		for(int i=0; i < afterFilter.getWidth(); i++)
		{
			for(int j=0; j < afterFilter.getHeight(); j++)
			{
				if (checkMatrix[i][j] == 0)
				{
					visit(i,j);
				}
			}
		}
	}
	
	public void visit(int i, int j)
	{
		checkMatrix[i][j] = 1;
		
		if (isBlack(i,j))
		{
		
			try
			{
				if(checkMatrix[i][j+1] == 0)
				{
					visit(i, j+1);
				}
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				
			}
		}
		
			*/
		}
	
	public boolean isBlack(int i, int j)
	{
		int x = afterFilter.getRGB(i, j);
		Color c = new Color(x);
		boolean b = false;
		if(c.getBlue() <= 20 && c.getGreen() <= 20 && c.getRed() <= 20)
		{
			b = true;
		}
		return b;
	}
	public boolean isBlue(int i, int j)
	{
		int x = afterFilter.getRGB(i, j);
		Color c = new Color(x);
		boolean b = false;
		if(c.getBlue() >= 245 && c.getGreen() <= 20 && c.getRed() <= 20)
		{
			b = true;
		}
		return b;
	}
	public boolean isGreen(int i, int j)
	{
		int x = afterFilter.getRGB(i, j);
		Color c = new Color(x);
		boolean b = false;
		if(c.getBlue() <= 20 && c.getGreen() >= 245 && c.getRed() <= 20)
		{
			b = true;
		}
		return b;
	}
	public boolean isRed(int i, int j)
	{
		int x = afterFilter.getRGB(i, j);
		Color c = new Color(x);
		boolean b = false;
		if(c.getBlue() <= 20 && c.getGreen() <= 20 && c.getRed() >= 245)
		{
			b = true;
		}
		return b;
	}

	public int getImageHeight()
	{
		return imageHeight;
	}

	public void setImageHeight(int imageHeight)
	{
		this.imageHeight = imageHeight;
	}

	public int getImageWidth()
	{
		return imageWidth;
	}

	public void setImageWidth(int imageWidth)
	{
		this.imageWidth = imageWidth;
	}
}