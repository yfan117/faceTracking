package Beans;

public class Gride {
	
	private int x;
	private int y;
	private int gridex;
	private int grideY;
	private int pixels;
	private double density;
	private int totalPixels;
	
	public Gride(int x, int y, int totalPixels, int pixels)
	{
		this.x = x;
		this.y = y;
		this.gridex = gridex;
		this.grideY = grideY;
		this.pixels = pixels;
		this.totalPixels = totalPixels;
		
		this.density = (double)pixels/(double)(totalPixels);
		
	}
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getGrideX()
	{
		return gridex;
	}
	public int getGrideY()
	{
		return grideY;
	}
	public double getDensity()
	{
		return density;
	}

	@Override
	public String toString() {
		return "Gride [x=" + x + ", y=" + y + ", gridex=" + gridex + ", grideY=" + grideY + ", pixels=" + pixels
				+ ", density=" + density + ", totalPixels=" + totalPixels + "]";
	}

	
	


	
	
	

}
