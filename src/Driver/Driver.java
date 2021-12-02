package Driver;

import java.io.IOException;
import java.util.ArrayList;

import Beans.Gride;
import Render.Renderer;
import Utilities.FindDensity;
import Utilities.SkinColor;

public class Driver {
	
	public static void main(String[] args) throws IOException, InterruptedException
	{
		
		Renderer render = new Renderer();
		
		FindDensity d1 = new FindDensity(25, render.processedImageData, render.width, render.height);
		d1.findDensity();
		d1.findTopTen();
		
		ArrayList<Gride> grides = d1.sortedGride;
		
		SkinColor.enableColors();
		SkinColor.hasColor(25, render.imageData, render.width, render.height);
		
		render.setGrides(grides, SkinColor.colorGride);
		
		while(true)
		{
			Thread.sleep(33);
			render.update();
		}
	}
}
