package graph;

import java.awt.geom.Point2D;

public class Point extends Point2D {
	
	private double x, y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString () {
		return "(" + getX() + "," + getY() + ")";
	}
	
	public Boolean equals (Point p) {
		if (p.getX() == getX() && p.getY() == getY()) {
			return true;
		} else {
			return false;
		}
	}

}