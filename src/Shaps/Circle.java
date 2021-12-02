package Shaps;

import java.awt.Color;

public class Circle {
	
	public static int[] generateCircle(int radius)
	{
		
		int centerX = radius;
		//int centerY = radius;
		int[] data = new int[radius*2 * radius*2];
		
		   for(int y = 0; y <= radius*2-1; y++)
		   {


		     
		     for(int x = 0; x < radius*2; x++)
		     {

		    	 data[x + y * radius*2] = -1;
		    	 
		    	 
		     }

		   }
//		   System.out.println(new Color(255, 255, 255).getRGB());
//	    	 System.out.println(new Color(0, 0, 0).getRGB());
		   for(int y = 0; y <= radius; y++)
		   {

		     int circleY = radius - y;
		     int  circleX = (int)Math.sqrt(radius*radius - circleY*circleY);
		     
		     //System.out.println(circleX +" " +circleY);
		     
		     for(int x = 0; x < circleX; x++)
		     {
		       data[centerX + x + y * radius*2] = -16777216;
		       data[centerX - x + y * radius*2] = -16777216;
		       data[centerX + x + (radius*2 -1- y) * radius*2] = -16777216;
		       data[centerX - x + (radius*2 -1- y) * radius*2] = -16777216;

		     }
		   }
		   //int[] returnData = new int[radius*2*radius*2];

		return data;
	}

}
