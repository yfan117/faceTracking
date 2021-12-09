package Driver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Beans.Gride;
import Render.Renderer;
import Utilities.CrossExamin;
import Utilities.FindDensity;
import Utilities.SkinColor;

public class Driver {
	public static Renderer render;
	public static boolean changeMade = false;
	public static void main(String[] args) throws IOException, InterruptedException
	{
		
		render = new Renderer();
				

	}
	
	public static void newImageSelected(String URL)
	{
		System.out.println(URL);
		try {
			render.bufferedImage = ImageIO.read(new File(URL));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render.width = render.bufferedImage.getWidth(null);
		render.height = render.bufferedImage.getHeight(null);
		render.imageToArray();
		render.arrayToImage();
		
		FindDensity.findDensity(render.scanSize, render.processedImageData, render.width, render.height);
		
		FindDensity.findTopCount();
		
		SkinColor.enableColors();
		SkinColor.hasColor(render.scanSize, render.imageData, render.width, render.height);
		
		CrossExamin.examin(300, FindDensity.sortedGride, SkinColor.colorGride, render.width, render.height);
		
		render.setDesnityGrides(FindDensity.sortedGride);
		render.setColorGrides(SkinColor.colorGride);
		
		update();
	}
	
	public static void update()
	{
		FindDensity.findDensity(render.scanSize, render.processedImageData, render.width, render.height);
		FindDensity.findTopCount();
		SkinColor.hasColor(render.scanSize, render.imageData, render.width, render.height);
		CrossExamin.examin(300, FindDensity.sortedGride, SkinColor.colorGride, render.width, render.height);
		render.setDesnityGrides(FindDensity.sortedGride);
		render.setColorGrides(SkinColor.colorGride);
		render.update();
	}
}
