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
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> refs/remotes/origin/master
	
	public Boolean equals (Vector p) {
		if (p.getX() == getX() && p.getY() == getY()) {
			return true;
		} else {
			return false;
		}
<<<<<<< HEAD
		return Math.toDegrees(angle);
	}
	
=======
>>>>>>> refs/remotes/origin/master

	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}

	public String toString() {
		return "[(" + getP1().getX() + "," + getP1().getY() + "),(" + getP2().getX() + "," + getP2().getY() + ")]";
=======
>>>>>>> refs/remotes/origin/master
	}

}