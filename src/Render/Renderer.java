package Render;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
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
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

import Beans.Gride;
import Controller.ActionHandler;
import Controller.MouseController;
import Shaps.Circle;
import Utilities.SkinColor;

public class Renderer extends JFrame{
	
	public static BufferedImage bufferedImage;
	static BufferedImage processedImage;
	
	public int width;
	public int height;
	
	public int[] imageData;
	public int[] processedImageData;
	
	Display display;
	public JSlider contrastSlider;
	public JLabel contrastLabel;
	
	public JSlider scanSizeSlider;
	public JLabel scanSizeLabel;
	
	public JSlider countSlider;
	public JLabel countLabel;
	
	public int scanSize = 25;
	
	public JButton update;
	
	public JButton toggleImageButton;
	public JButton toggleGrideButton;

	public static boolean toggleImage = false;
	public static boolean toggleGride = false;
	

	public Renderer() 
	{

		
		display = new Display();
		this.add(display, BorderLayout.CENTER);
		
		JPanel controlPanel = new JPanel();
		this.add(controlPanel, BorderLayout.WEST);
//		BoxLayout boxlayout = new BoxLayout(controlPanel, BoxLayout.Y_AXIS);
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		controlPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		controlPanel.setBackground(Color.DARK_GRAY);
		ActionHandler action = new ActionHandler();
		
		toggleImageButton = new JButton("Toggle Image View");
		toggleImageButton.setBackground(Color.DARK_GRAY);
		toggleImageButton.setForeground(Color.white);
		toggleImageButton.addActionListener(action);
		controlPanel.add(toggleImageButton);
		
		toggleGrideButton = new JButton("Toggle Selection");
		toggleGrideButton.setBackground(Color.DARK_GRAY);
		toggleGrideButton.setForeground(Color.white);
		toggleGrideButton.addActionListener(action);
		controlPanel.add(toggleGrideButton);
	
		contrastSlider = new JSlider(0,50);
		contrastSlider.setBackground(Color.DARK_GRAY);
		contrastSlider.setForeground(Color.white);
		contrastSlider.addChangeListener(action);
		controlPanel.add(contrastSlider);
		contrastSlider.setPaintTrack(true);
		contrastSlider.setPaintTicks(true);
		contrastSlider.setPaintLabels(true);
		contrastSlider.setMajorTickSpacing(50);
		contrastSlider.setMinorTickSpacing(5);
		contrastLabel = new JLabel();
		contrastLabel.setText("Contrast Value: " +contrastSlider.getValue());
		contrastLabel.setForeground(Color.white);
		controlPanel.add(contrastLabel);	
		controlPanel.add(new JLabel("---------------------------------------------"));
		
		scanSizeSlider = new JSlider(0,100);
		scanSizeSlider.setBackground(Color.DARK_GRAY);
		scanSizeSlider.setForeground(Color.white);
		scanSizeSlider.addChangeListener(action);
		controlPanel.add(scanSizeSlider);
		scanSizeSlider.setPaintTrack(true);
		scanSizeSlider.setPaintTicks(true);
		scanSizeSlider.setPaintLabels(true);
		scanSizeSlider.setMajorTickSpacing(50);
		scanSizeSlider.setMinorTickSpacing(5);
		scanSizeLabel = new JLabel();
		scanSizeLabel.setText("Scann Size: " +scanSizeSlider.getValue());
		scanSizeLabel.setForeground(Color.white);
		controlPanel.add(scanSizeLabel);	
		controlPanel.add(new JLabel("---------------------------------------------"));
		
		countSlider = new JSlider(0,100);
		countSlider.setBackground(Color.DARK_GRAY);
		countSlider.setForeground(Color.white);
		countSlider.addChangeListener(action);
		controlPanel.add(countSlider);
		countSlider.setPaintTrack(true);
		countSlider.setPaintTicks(true);
		countSlider.setPaintLabels(true);
		countSlider.setMajorTickSpacing(50);
		countSlider.setMinorTickSpacing(5);
		countLabel = new JLabel();
		countLabel.setText("Count Number: " +countSlider.getValue());
		countLabel.setForeground(Color.white);
		controlPanel.add(countLabel);	
		controlPanel.add(new JLabel("---------------------------------------------"));
		
		update = new JButton("Update");
		update.setBackground(Color.DARK_GRAY);
		update.setForeground(Color.white);
		controlPanel.add(update);
		update.addActionListener(action);
			
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem open = new JMenuItem("Open");
		file.add(open);
		open.addActionListener(action);
		menuBar.add(file);
		menuBar.setBackground(Color.LIGHT_GRAY);
		menuBar.setForeground(Color.white);
		
		
		this.setJMenuBar(menuBar);
		
		this.addMouseListener(new MouseController());;
		this.setSize(1920, 1080);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.validate();
	}
	
	public int contrastValue = 25;
	public void update()
	{
		arrayToImage();
		display.update();
	}
	
	static ArrayList<Gride> densityGrides;
	static ArrayList<Gride> colorGride;

	public void setDesnityGrides(ArrayList<Gride> densityGrides)
	{
		this.densityGrides = densityGrides;
	}
	public void setColorGrides(ArrayList<Gride> colorGride)
	{
		this.colorGride = colorGride;
	}

	public void imageToArray()
	{
		System.out.println(bufferedImage.getWidth(null));
		int width = bufferedImage.getWidth(null);
		int height = bufferedImage.getHeight(null);
		
		imageData = new int[width * height];
		processedImageData = new int[width * height];
		
		
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
					
					if(y + 1 < height)
					{
						Color color2 = new Color(imageData[x + (y + 1) * width]);
						int red2 = color2.getRed();
						int green2 = color2.getGreen();
						int blue2 = color2.getBlue();

						if((Math.abs(red - red2)>contrastValue)&&(Math.abs(green - green2)>contrastValue)&&(Math.abs(blue - blue2)>contrastValue))
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
//					newColor = new Color(red, green, blue);
					
//					processedImageData[x + y * width] = newColor.getRGB();
//					processedImage.setRGB(x, y, newColor.getRGB());
				}
				
			}
		}
		
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
					
					if(y + 1 < height)
					{
						Color color2 = new Color(imageData[x + (y + 1) * width]);
						int red2 = color2.getRed();
						int green2 = color2.getGreen();
						int blue2 = color2.getBlue();

						if((Math.abs(red - red2)>contrastValue)&&(Math.abs(green - green2)>contrastValue)&&(Math.abs(blue - blue2)>contrastValue))
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
//					newColor = new Color(red, green, blue);
					
					processedImageData[x + y * width] = newColor.getRGB();
					processedImage.setRGB(x, y, newColor.getRGB());
				}
				
			}
		}
  
	}
	
	

}

class Display extends JPanel{
	

	public void update()
	{
		repaint();
	}
	protected void paintComponent(Graphics g)
	{
		
		Graphics2D g2d = (Graphics2D) g;
		
		//super.paint(g2d); causes crash
		
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(0, 0, 1920, 1080);
		
		if(Renderer.bufferedImage != null)
		{
			if(Renderer.toggleImage == false)
			{
				g2d.drawImage(Renderer.bufferedImage, 0, 0, null);
			}
			else
			{
				g2d.drawImage(Renderer.processedImage, 0, 0, null);
			}
		}
		
		
		if(Renderer.toggleGride == false)
		{
			g2d.setStroke(new BasicStroke(4));
			if(Renderer.densityGrides != null)
			{
				g2d.setColor(Color.green);
				for(Gride element: Renderer.densityGrides)
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
}
