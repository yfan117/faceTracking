package Beans;

public class Gride {
	
	private int x;
	private int y;
	private int size;
	private int pixels;
	private double density;
	private int totalPixels;
	
	public Gride(int x, int y, int size, int totalPixels, int pixels)
	{
		this.x = x;
		this.y = y;
		this.size = size;
		this.pixels = pixels;
		this.totalPixels = totalPixels;
		
		this.density = (double)pixels/(double)(totalPixels);
		
	}
	
	public int getX()
	{
		return x;
	}
	public int getSize()
	{
		return size;
	}
	public int getY()
	{
		return y;
	}

	public double getDensity()
	{
		return density;
	}

	@Override
	public String toString() {
		return "Gride [x=" + x + ", y=" + y + ", pixels=" + pixels
				+ ", density=" + density + ", totalPixels=" + totalPixels + "]";
	}

	
	


	
	
	

}
