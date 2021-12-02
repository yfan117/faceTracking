package Render;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Beans.Gride;
import Controller.MouseController;
import Shaps.Circle;

public class Renderer extends JFrame{
	
	static BufferedImage bufferedImage;
	static BufferedImage processedImage;
	
	public int width;
	public int height;
	
	public int[] imageData;
	public int[] processedImageData;
	
	Display display;
	
	public Renderer() 
	{
		try {
			bufferedImage = ImageIO.read(new File("resource/yangmi.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		width = bufferedImage.getWidth(null);
		height = bufferedImage.getHeight(null);
		imageToArray();
		arrayToImage();
		
		display = new Display();
		
		
		this.add(display);
		this.addMouseListener(new MouseController());;
		this.setSize(bufferedImage.getWidth(null), bufferedImage.getHeight(null));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.validate();
	}
	
	int temp = 0;
	public void update()
	{
//		if(temp > 255)
//		{
//			temp = 1;
//		}
//		else
//		{
//			temp += 10;
//		}
		//System.out.println("painting");

		arrayToImage();
		//circleImage();
		display.update();
	}
	
	static ArrayList<Gride> sortedGride;
	static ArrayList<Gride> colorGride;
	public void setGrides(ArrayList<Gride> gride, ArrayList<Gride> colorGride)
	{
		sortedGride = gride;
		this.colorGride = colorGride;
	}
	
	public void circleImage()
	{
		imageData = Circle.generateCircle(50);
	}
	
	public void imageToArray()
	{
		System.out.println(bufferedImage.getWidth(null));
		int width = bufferedImage.getWidth(null);
		int height = bufferedImage.getHeight(null);
		
		imageData = new int[width * height];
		processedImageData = new int[width * height];
		System.out.println(temp);
		
		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{

				
				imageData[x + y * width] = bufferedImage.getRGB(x, y);
			}
		}
		
		
	}
	
//	public static void displayRGB(int x, int y)
//	{
//		int width = bufferedImage.getWidth(null);
//		int height = bufferedImage.getHeight(null);
//		
//		Color color = new Color(processedImageData[x + y * width]);
//		System.out.println("RGB: " +color.getRGB());
//		System.out.println("red : "+color.getRed());
//		System.out.println("green : "+color.getGreen());
//		System.out.println("blue : "+color.getBlue());
////		
////		Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue());
////		System.out.println(color.getRGB() + " " +color2.getRGB());
//	}
	
	public void arrayToImage()
	{

		processedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//        WritableRaster raster = (WritableRaster) printImage.getData();
//        
//        raster.setPixels(0, 0, width, height, imageData);
		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{
				//if(imageData[x + y * width] != -1)
				{
					Color color = new Color(imageData[x + y * width]);
					int red = color.getRed();
					int green = color.getGreen();
					int blue = color.getBlue();
					
					Color newColor;
//					System.out.println(temp);
					temp = 2;
					if(y + 1 < height)
					{
						Color color2 = new Color(imageData[x + (y + 1) * width]);
						int red2 = color2.getRed();
						int green2 = color2.getGreen();
						int blue2 = color2.getBlue();

						if((Math.abs(red - red2)>temp)||(Math.abs(green - green2)>temp)||(Math.abs(blue - blue2)>temp))
						{
							newColor = new Color(-16777216);
						}
//						if(Math.abs((red + green + blue) -  (red2 + green2 + blue2))> 765/temp)
//						{
//							red = 0;green = 0;blue = 0;
//							newColor = new Color(-16777216);
//							
//						}
						else
						{
			
							newColor = new Color(-1);
						}
					}
					else
					{
		
						newColor = new Color(-1);
					}
					
					
//					if(red + green + blue > temp)
//					{
//						red = 255;green = 255;blue = 255;
//						newColor = new Color(-1);
//					}
//					else
//					{
//						red = 0;green = 0;blue = 0;
//						newColor = new Color(-16777216);
//					}
					
//					if(red > 255/3){red = 255;green = 255;blue = 255;}
//					else{red = 0;green = 0;blue = 0;}
//					
//					if(green > 255/3){red = 255;green = 255;blue = 255;}
//					else{red = 0;green = 0;blue = 0;}
//					
//					if(blue > 255/3){red = 255;green = 255;blue = 255;}
//					else{red = 0;green = 0;blue = 0;}
					processedImageData[x + y * width] = newColor.getRGB();
					processedImage.setRGB(x, y, newColor.getRGB());
				}
				
			}
		}
  
	}
	
	

}

class Display extends JPanel{
	

	public Display()
	{
		
	}
	
	public void update()
	{
		repaint();
	}
	protected void paintComponent(Graphics g)
	{

		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(Renderer.bufferedImage, 0, 0, null);
		
		g2d.setStroke(new BasicStroke(4));
		if(Renderer.sortedGride != null)
		{
			g2d.setColor(Color.green);
			for(Gride element: Renderer.sortedGride)
			{

				g2d.drawRect(element.getX(),
							   element.getY(),
							   element.getSize(),
							   element.getSize());
			}
		}
		
		if(Renderer.colorGride != null)
		{
			g2d.setColor(Color.blue);
			for(Gride element: Renderer.colorGride)
			{
				g2d.drawRect(element.getX(),
							   element.getY(),
							   element.getSize(),
							   element.getSize());
			}
		}
		
		

	}
}
