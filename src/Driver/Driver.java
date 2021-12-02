package Driver;

import java.io.IOException;

import Render.Renderer;
import Utilities.FindDensity;

public class Driver {
	
	public static void main(String[] args) throws IOException, InterruptedException
	{
		
		Renderer render = new Renderer();
		
		FindDensity d1 = new FindDensity(50, render.processedImageData, render.width, render.height);
		d1.findDensity();
		d1.findTopTen();
		render.setHighestGride(d1.sortedGride);
		while(true)
		{
			Thread.sleep(33);
			render.update();
		}
	}
}
