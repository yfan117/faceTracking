package Utilities;

import java.util.ArrayList;


import Beans.Gride;

public class FindDensity {
	
	public static ArrayList<Gride> gride;

	public static int count = 50;

	public static void findDensity(int grideSize, int[] data, int dataWidth, int dataHeight)
	{
		gride = new ArrayList<>();
		for(int x = 0; x < dataWidth; x+=grideSize/2)
		{
			for(int y = 0; y < dataHeight; y+=grideSize/2)
			{
				//System.out.println("scanning: " +x +" " +y);
				int darkPixelCount = 0;
				int totalPixelCount = 0;
				int a =x;
				int b =y;
				int xLimit = x + grideSize;
				int yLimit = y + grideSize;
				
				
				
				for(a = x; a < xLimit; a++)
				{
					for(b = y; b < yLimit; b++)
					{
						//System.out.println(b +" " +yLimit);
						try
						{
							if(data[a + b * dataWidth] != -1)
							{
								darkPixelCount++;
							}
							totalPixelCount++;
						}
						catch(Exception e)
						{
							
						}
						
						
						
					}
				}
				gride.add(new Gride(x, y, grideSize, totalPixelCount, darkPixelCount));
			}
			
		}
		System.out.println("contrast done scanning\n");
	}
	
	public Gride hghestGride;
	public static ArrayList<Gride> sortedGride;
	public static void findTopCount()
	{
		sortedGride = new ArrayList<>();

		for(int a = 0; a < count; a++)
		{
			int i = 0;
			double highestDensity = gride.get(0).getDensity();
			int highest = 0;
			for(i = 0; i < gride.size()-1; i++)
			{
				if((gride.get(i).getDensity() > highestDensity))
				{
		
					highestDensity = gride.get(i).getDensity();
					highest = i;
				}
			}
			//System.out.println(highest);
			gride.remove(highest);
			sortedGride.add(gride.get(highest));
		}
		

//		System.out.println(sortedGride);
//		System.out.println(i);
//		System.out.println("highest density: " +gride.get(highest).toString());
//		hghestGride = gride.get(highest);
		
	}

}
