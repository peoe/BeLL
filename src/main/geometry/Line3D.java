package main.geometry;

public class Line3D {
	
	private Point3D p1;
	private Point3D p2;

	public Line3D (Point3D p1, Point3D p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Line3D (double x1, double y1, double z1, double x2, double y2, double z2) {
		this.p1 = new Point3D(x1, y1, z1);
		this.p2 = new Point3D(x2, y2, z2);
	}
	
	/**
	 * 
	 * @param points Array of Doubles [[x1, y1, z1], [x2, y2, z2]]
	 */
	public Line3D (double[][] points) {
		if (points != null && points.length == 2 && points[0].length == 3 && points[1].length == 3) {
			this.p1 = new Point3D(points[0][0], points[0][1], points[0][2]);
			this.p2 = new Point3D(points[1][0], points[1][1], points[1][2]);
		}
	}
	
	public void set (Point3D p1, Point3D p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public void set (double x1, double y1, double z1, double x2, double y2, double z2) {
		this.p1 = new Point3D(x1, y1, z1);
		this.p2 = new Point3D(x2, y2, z2);
	}
	
	/**
	 * 
	 * @param points Array of Doubles [[x1, y1], [x2, y2]]
	 */
	public void set (double[][] points) {
		if (points != null && points.length == 2 && points[0].length == 3 && points[1].length == 3) {
			this.p1 = new Point3D(points[0][0], points[0][1], points[0][2]);
			this.p2 = new Point3D(points[1][0], points[1][1], points[1][2]);
		}
	}
	
	/**
	 * 
	 * @return Array of Doubles [[x1, y1, z1], [x2, y2, z2]]
	 */
	public double[][] get () {
		double[][] points = {{p1.getX(), p1.getY(), p1.getZ()}, {p2.getX(), p2.getY(), p2.getZ()}};
		return points;
	}
	
	public String toString() {
		return "[" + p1.toString() + ", " + p2.toString() + "]";
	}
	
}
