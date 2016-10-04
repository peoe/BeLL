package main.geometry;

public class Line {
	
	private Point p1;
	private Point p2;

	public Line (Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Line (double x1, double y1, double x2, double y2) {
		this.p1 = new Point(x1, y1);
		this.p2 = new Point(x2, y2);
	}
	
	/**
	 * 
	 * @param points Array of Doubles [[x1, y1], [x2, y2]]
	 */
	public Line (double[][] points) {
		if (points != null && points.length == 2 && points[0].length == 2 && points[1].length == 2) {
			this.p1 = new Point(points[0][0], points[0][1]);
			this.p2 = new Point(points[1][0], points[1][1]);
		}
	}
	
	public void set (Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public void set (double x1, double y1, double x2, double y2) {
		this.p1 = new Point(x1, y1);
		this.p2 = new Point(x2, y2);
	}
	
	/**
	 * 
	 * @param points Array of Doubles [[x1, y1], [x2, y2]]
	 */
	public void set (double[][] points) {
		if (points != null && points.length == 2 && points[0].length == 2 && points[1].length == 2) {
			this.p1 = new Point(points[0][0], points[0][1]);
			this.p2 = new Point(points[1][0], points[1][1]);
		}
	}
	
	/**
	 * 
	 * @return Array of Doubles [[x1, y1], [x2, y2]]
	 */
	public double[][] get () {
		double[][] points = {{p1.getX(), p1.getY()}, {p2.getX(), p2.getY()}};
		return points;
	}
	
	public String toString() {
		return "[" + p1.toString() + ", " + p2.toString() + "]";
	}
	
}
