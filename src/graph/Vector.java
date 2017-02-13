package graph;

import java.awt.geom.Point2D;

public class Vector extends Point2D {
	
	private double x, y;
	
	public Vector(double x, double y) {
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
	
	public Boolean equals (Vector p) {
		if (p.getX() == getX() && p.getY() == getY()) {
			return true;
		} else {
			return false;
		}
	}

}