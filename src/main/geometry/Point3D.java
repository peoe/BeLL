package main.geometry;

public class Point3D {
	
	private double x;
	private double y;
	private double z;

	public Point3D (double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point3D (double[] coords) {
		if (coords != null) {
			this.x = coords[0];
			this.y = coords[1];
			this.z = coords[2];
		}
	}
	
	public Point3D (Point p, double z) {
		this.x = p.getX();
		this.y = p.getY();
		this.z = z;
	}
	
	public Point3D () {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public double getX () {
		return x;
	}
	
	public double getY () {
		return y;
	}
	
	public double getZ () {
		return z;
	}
	
	public double[] get() {
		double[] coords = {x, y, z};
		return coords;
	}
	
	public void set(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void set(double[] coords) {
		if (coords != null && coords.length == 3) {
			this.x = coords[0];
			this.y = coords[1];
			this.z = coords[2];
		}
	}
	
	public String toString() {
		return "[" + String.valueOf(x) + ", " + String.valueOf(y) + ", " + String.valueOf(z) + "]";
	}
	
}
