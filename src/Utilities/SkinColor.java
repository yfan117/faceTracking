package Utilities;

import java.awt.Color;
import java.util.ArrayList;

import Beans.Gride;

public class SkinColor {
	
	static ArrayList<Color> skinColors = new ArrayList<>();
	public static ArrayList<Gride> colorGride = new ArrayList<>();
	
	public static void enableColors()
	{
		skinColors.add(new Color(141, 85, 36));
		skinColors.add(new Color(198, 134, 66));
		skinColors.add(new Color(224, 172, 105));
		skinColors.add(new Color(241, 194, 125));
		skinColors.add(new Color(255, 219, 172));
		skinColors.add(new Color(86, 51, 43));
		skinColors.add(new Color(189, 156, 147));
		
//		skinColors.add(new Color(45, 34, 30));
//		skinColors.add(new Color(60, 46, 40));
//		skinColors.add(new Color(75, 57, 50));
//		skinColors.add(new Color(90, 69, 60));
//		skinColors.add(new Color(105, 80, 70));
//		skinColors.add(new Color(120, 92, 80));
//		skinColors.add(new Color(135, 103, 90));
//		skinColors.add(new Color(150, 114, 100));
//		skinColors.add(new Color(165, 126, 110));
//		skinColors.add(new Color(180, 138, 120));
//		skinColors.add(new Color(195, 149, 130));
//		skinColors.add(new Color(210, 161, 140));
//		skinColors.add(new Color(225, 172, 150));
//		skinColors.add(new Color(240, 184, 160));
//		skinColors.add(new Color(255, 195, 170));
//		skinColors.add(new Color(255, 206, 180));
	}
	
	public static void hasColor(int grideSize, int[] data, int dataWidth, int dataHeight)
	{
		colorGride = new ArrayList<>();
		for(int x = 0; x < dataWidth; x+=grideSize/2)
		{
			for(int y = 0; y < dataHeight; y+=grideSize/2)
			{
				
				int temp = 25;

				
				for(Color element: skinColors)
				{
					int red = element.getRed();
					int green = element.getGreen();
					int blue = element.getBlue();
					
				
					double similarPixelCount = 0;
					double totalPixelCount = 0;
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
								Color dataColor = new Color(data[a + b * dataWidth]);
								int red2 = dataColor.getRed();
								int green2 = dataColor.getGreen();
								int blue2 = dataColor.getBlue();

								if((Math.abs(red - red2)<temp)&&(Math.abs(green - green2)<temp)&&(Math.abs(blue - blue2)<temp))
								{
									//System.out.println("color found");
									similarPixelCount++;
								}
								else
								{
									//System.out.println("color not found");	
								}
								totalPixelCount++;
							}
							catch(Exception e)
							{
								
							}
		
						}
					}
					
					if(similarPixelCount / totalPixelCount > 0.7)
					{
						System.out.println("color found");
						colorGride.add(new Gride(x, y, grideSize, (int)totalPixelCount, (int)similarPixelCount));
					}
						
						
				}
				
				
			}
		}
		System.out.println("color done scanning\n");

	}

}
