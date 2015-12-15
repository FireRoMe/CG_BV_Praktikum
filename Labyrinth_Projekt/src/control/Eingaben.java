package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Eingaben implements KeyListener
{
	//Variablen fuer Kontrolle
	
	private boolean rechts = false;
	private boolean links = false;
	private boolean oben = false;
	private boolean unten = false;
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			oben = true;
			break;
		case KeyEvent.VK_DOWN:
			unten = true;
			break;
		case KeyEvent.VK_LEFT:
			links = true;
			break;
		case KeyEvent.VK_RIGHT:
			rechts = true;
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			oben = false;
			break;
		case KeyEvent.VK_DOWN:
			unten = false;
			break;
		case KeyEvent.VK_LEFT:
			links = false;
			break;
		case KeyEvent.VK_RIGHT:
			rechts = false;
			break;	
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{
		//Nichts tun		
	}

	
}
