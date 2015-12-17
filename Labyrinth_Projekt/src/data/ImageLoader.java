/**
 * Die ImageLoader Klasse ermöglicht es, Bilder zu laden und zu verarbeiten.
 * @author Jaqueline Timmermann
 */
package data;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader
{
	/** Speichert das erste eingelesene Bild. */
	private BufferedImage b;
	/** Das Bild nach der Filterung. */
	private BufferedImage afterFilter;
	/** Das Bild nachdem der Rand drum herum gesetzt wurde. */
	private BufferedImage rand;
	/** Pfad des Bildes als String */
	private String path;
	
	/** Bildhoehe. */
	private static int imageHeight;
	/** Bildbreite. */
	private static int imageWidth;
	
	/** Pruefmatrix.
	 * Nicht mehr enthalten, da nicht mehr benötigt. */
	//private int[][] checkMatrix;
	
	/** Erstellt einen Imageloader und führt die Methoden loadImage, filter und border auf ein Bild
	 * aus dem übergebenen Pfad aus.
	 */
	public ImageLoader(String s)
	{
		path = s;
		
		loadImage();
		filter();
		border();
	}
	
	/** Getter fuer ein Bild.
	 * @return Gibt das Bild nach der Filterung mit Rand zurueck.
	 */
	public BufferedImage getImage(){
		return rand;
	}

	/** Getter und Setter für Höhe und Breite des Bildes
	 */
	public static int getImageHeight()
	{
		return imageHeight;
	}

	public void setImageHeight(int imageHeight)
	{
		ImageLoader.imageHeight = imageHeight;
	}

	public static int getImageWidth()
	{
		return imageWidth;
	}

	public void setImageWidth(int imageWidth)
	{
		ImageLoader.imageWidth = imageWidth;
	}

	/** Lade ein Bild. Das Bild ist momentan noch vorgegeben.
	 */
	public void loadImage()
	{
		try 
		{
			b = ImageIO.read(new File(path));
	
		    //System.out.println("Bild geladen");
			//System.out.println(b.getHeight());
		    //System.out.println(b.getWidth());
		    setImageHeight(b.getHeight());
		    setImageWidth(b.getWidth());
		} 
		catch (IOException e) 
		{
			System.out.println("Lesefehler");
			e.printStackTrace();
		}	
	}

	/** Setzt Farben im eingelesenen Bild auf 'reine' Farbwerte.
	 */
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
			//System.out.println("");
		}
		try {
		    /**Speichert das gefilterte Bild ab
		     */
		    File outputfile = new File("src//control//afterFilter.png");
		    ImageIO.write(afterFilter, "png", outputfile);
		} catch (IOException e) {
		   e.printStackTrace();
		}
	}
	
	/** Setze einen 1 Pixel grossen weissen Rand um das Bild.
	 * Quelle: https://community.oracle.com/thread/1264581?start=0&tstart=0
	 */
	public void border()
	{
		rand = new BufferedImage(afterFilter.getWidth()+2, afterFilter.getHeight()+2, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = rand.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0,0,rand.getWidth(),rand.getHeight());
		
		g.drawImage(afterFilter,1,1,null);
		
		try {
		    File outputfile = new File("src//control//afterRand.jpg");
		    ImageIO.write(rand, "png", outputfile);
		} catch (IOException e) {
		   e.printStackTrace();
		}
	}
	
	/** Erst angedacht zum Analysieren des Bildes, ist jedoch durch eine Funktion in Objekte ersetzt worden.
	 */
	/*public void analyzeImage()
	{
		checkMatrix = new int[afterFilter.getWidth()][afterFilter.getHeight()];
		
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
	}
	*/
	
	/** Ueberprueft die Farben auf schwarz,blau,gruen und rot.
	 * Wird nicht mehr genutzt.
	 * @param i Breite des Bildes.
	 * @param j Hoehe des Bildes.
	 * @return true wenn das Pixel die gesuchte Farbe hat, sonst false.
	 */
	/* private boolean isBlack(int i, int j)
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
	private boolean isBlue(int i, int j)
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
	private boolean isGreen(int i, int j)
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
	private boolean isRed(int i, int j)
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
	*/
}