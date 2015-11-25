/*
 * Inventar als ArrayList von Items
 * @author
 * @version 0.1
 */

package data;

import java.util.ArrayList;

public class Inventar
{
	private ArrayList<Item> inventory = new ArrayList<Item>();
	
	public void addItem(Item i)
	{
		inventory.add(i);
	}

}

