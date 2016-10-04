package main.geometry;

public class Point {

	private double x;
	private double y;
	
	public Point (double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point (double[] coords) {
		if (coords != null) {
			this.x = coords[0];
			this.y = coords[1];
		}
	}
	
	public Point () {
		this.x = 0;
		this.y = 0;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double[] get() {
		double[] coords = {x, y};
		return coords;
	}
	
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(double[] coords) {
		if (coords != null && coords.length == 2) {
			this.x = coords[0];
			this.y = coords[1];
		}
	}
	
	public String toString() {
		return "[" + String.valueOf(x) + ", " + String.valueOf(y) + "]";
	}
	
}
