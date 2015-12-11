package data;

import java.util.ArrayList;

public class Wand
{
	private ArrayList<int[]> koordinaten = new ArrayList<int[]>();
	
	public void addCoordinates(int i, int j)
	{
		int[] x = new int[2];
		x[0] = i;
		x[1] = j;
		koordinaten.add(x);
	}
}
