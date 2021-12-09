package Utilities;

import java.awt.Color;
import java.util.ArrayList;

import Beans.Gride;

public class CrossExamin {

	public static ArrayList<Gride> finalGride;
	static int[] contrast;
	static int[] color;

	public static void examin(int grideSize, ArrayList<Gride> contrastData, ArrayList<Gride> colorData, int dataWidth, int dataHeight)
	{
		contrast = new int[dataWidth * dataHeight];
		for(Gride element: contrastData)
		{
			for(int x = element.getX(); x < element.getX() + element.getSize(); x++)
			{
				for(int y = element.getY(); y < element.getY() + element.getSize(); y++)
				{
					try
					{
						contrast[x + y * dataWidth] = 1;
					}catch(Exception e)
					{
						
					}
					
				}
			}
		}
		
		color = new int[dataWidth * dataHeight];
		for(Gride element: colorData)
		{
			for(int x = element.getX(); x < element.getX() + element.getSize(); x++)
			{
				for(int y = element.getY(); y < element.getY() + element.getSize(); y++)
				{
					try
					{
						color[x + y * dataWidth] = 1;
					}catch(Exception e)
					{
						
					}
					
				}
			}
		}
	
		finalGride = new ArrayList<>();
		for(int x = 0; x < dataWidth; x+=grideSize/2)
		{
			for(int y = 0; y < dataHeight; y+=grideSize/2)
			{
				
				double contrastCount = 0;
				double colorCount = 0;
				double totalCount = 0;
				for(int a = x; a < x + grideSize/2; a++)
				{
					for(int b = y; b < y + grideSize/2; b++)
					{
						try
						{
							if(contrast[a + b * dataWidth] == 1)
							{
								contrastCount++;
							}
							if(color[a + b * dataWidth] == 1)
							{
								colorCount++;
							}
							totalCount++;
						}catch(Exception e)
						{
							
						}
					}
				}
				
				double contrastDensity = contrastCount/(totalCount/2);
				double colorCountDensity = colorCount/(totalCount/2);
				
				if((contrastDensity > 0.2)&&(colorCountDensity > 0.2))
				{
					if(Math.abs(contrastDensity - colorCountDensity)<0.5)
					{
						finalGride.add(new Gride(x, y, grideSize, -1, -1));
					}
				}
				

			}

		}
		System.out.println("Cross examination finished\n");

	}
}
