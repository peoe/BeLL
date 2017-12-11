package graph;

import io.DXFReader;

public class Vector {

	// x and y coordinates
	private double x, y;

	// constructors
	/**
	 * Constructor of the Vector class using differences of the x and y
	 * coordinates.
	 * 
	 * @param dx
	 *            the difference of the x coordinates
	 * @param dy
	 *            the difference of the y coordinates
	 */
	public Vector(double dx, double dy) {
		this.x = dx;
		this.y = dy;
	}

	/**
	 * Constructor of the Vector class using a start and an end point.
	 * 
	 * @param s
	 *            the starting point of the Vector
	 * @param e
	 *            the ending point of the Vector
	 */
	public Vector(Vector s, Vector e) {
		this.x = e.getX() - s.getX();
		this.y = e.getY() - s.getY();
	}

	/**
	 * Returns a Line to the point specified by the Vector.
	 * 
	 * @return a Line to the point of the Vector
	 */
	public Line toLine() {
		return (new Line(new Vector(0, 0), this));
	}

	/**
	 * Changes the length of the Vector to the specified length.
	 * 
	 * @param len
	 *            the new length of the Vector
	 * @return changed Vector
	 */
	public Vector changeLength(double len) {
		return (this.multiply(len / this.getLength()));
	}

	/**
	 * Multiplies the Vector using scalar multiplication and a factor.
	 * 
	 * @param f
	 *            the factor
	 * @return multiplied Vector
	 */
	public Vector multiply(double f) {
		return (new Vector(f * x, f * y));
	}

	/**
	 * Adds another Vector to the Vector.
	 * 
	 * @param v
	 *            the other Vector
	 * @return changed Vector
	 */
	public Vector add(Vector v) {
		return (new Vector(x + v.getX(), y + v.getY()));
	}

	/**
	 * Calculates the angle of the Vector in radian measure using Math.atan2().
	 * 
	 * @return angle of the Vector
	 * @see Math
	 */
	public double angle() {
		double angle = Math.atan2(this.y, this.x);

		return angle;
	}

	/**
	 * Calculates the angle of the Vector in degrees using Math.atan2().
	 * 
	 * @return angle of the Vector in degrees
	 * @see Math
	 */
	public double angleInDegrees() {
		return Math.toDegrees(angle());
	}

	/**
	 * Calculates the angle of two Vectors in radian measure.
	 * 
	 * @param v
	 *            the other Vector
	 * @return angle between both Vectors anti-clockwise
	 */
	public double angletoVector(Vector v) {
		double angle = v.angle() - this.angle();

		if (angle <= 0) {
			angle = angle + 2 * Math.PI;
		}

		return angle;
	}

	/**
	 * Calculates the angle of two Vectors in degrees.
	 * 
	 * @param v
	 *            the other Vector
	 * @return angle between both Vectors anti-clockwise in degrees
	 */
	public double angletoVectorD(Vector v2) {
		return Math.toDegrees(this.angletoVector(v2));
	}

	/**
	 * Returns the bisector of the angle between two Vectors.
	 * 
	 * @param v
	 *            the other Vector
	 * @return bisector of the angle between both Vectors
	 */
	public double bisectorOfAngleTo(Vector v) {
		return (this.angleInDegrees() + 0.5 * this.angletoVectorD(v));
	}

	/**
	 * Checks if two Vectors are equal.
	 * 
	 * @param v
	 *            the other Vector
	 * @return true if both Vectors are equal
	 */
	public Boolean equals(Vector v) {
		if ((this.getX() == v.getX()) && (this.getY() == v.getY())) {
			return true;
		}

		return false;
	}

	/**
	 * Returns a rotated Vector. The Vector is rotated by the specified angle.
	 * 
	 * @param phi
	 *            the angle the Vector is being rotated
	 * @return rotated Vector
	 */
	public Vector rotate(double phi) {
		return new Vector(DXFReader.round2(getX() * Math.cos(phi) - getY() * Math.sin(phi)),
				DXFReader.round2(getX() * Math.sin(phi) + getY() * Math.cos(phi)));
	}

	/**
	 * Returns a String describing the Vector in OpenSCAD.
	 * 
	 * @return String of Vector
	 */
	public String toScadString() {
		return "[" + getX() + "," + getY() + "]";
	}

	// getter - setter
	/**
	 * Returns the length of the Vector.
	 * 
	 * @return length of the Vector
	 */
	public double getLength() {
		return (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
	}

	/**
	 * Returns the difference of the x coordinates.
	 * 
	 * @return difference of the x coordinates
	 */
	public double getX() {
		return x;
	}

	/**
	 * Returns the difference of the y coordinates.
	 * 
	 * @return the difference of the y coordinates
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the coordinates of the Vector.
	 * 
	 * @param x
	 *            the new x coordinate of the Vector
	 * @param y
	 *            the new y coordinate of the Vector
	 */
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns a String of the Vector.
	 * 
	 * @return String of the Vector
	 */
	public String toString() {
		return "(" + getX() + "," + getY() + ")";
	}

}
