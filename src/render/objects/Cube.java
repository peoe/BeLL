package render.objects;

import java.util.Locale;

import render.ScadObject;

public class Cube implements ScadObject {

	// x, y and z coordinates of the Cube
	private double x, y, z;

	// boolean if the piece will be centered
	private boolean center;

	// layout String used to generate a Cube in OpenSCAD
	final static String CUBE = "cube([%1$.2f,%2$.2f,%3$.2f], %4$s);\n";

	// constructors
	/**
	 * Constructor of the Cube class using x, y and z coordinate of the Cube and
	 * a Boolean if the Cube will be centered.
	 * 
	 * @param X
	 *            the x coordinate of the Cube
	 * @param Y
	 *            the y coordinate of the Cube
	 * @param Z
	 *            the z coordinate of the Cube
	 * @param Center
	 *            the Boolean for centering the Cube
	 */
	public Cube(double X, double Y, double Z, boolean Center) {
		this.x = X;
		this.y = Y;
		this.z = Z;
		this.center = Center;
	}

	/**
	 * Constructor of the Cube class using x, y and z coordinate of the Cube.
	 * Assumes that the Cube will not be centered.
	 * 
	 * @param X
	 *            the x coordinate of the Cube
	 * @param Y
	 *            the y coordinate of the Cube
	 * @param Z
	 *            the z coordinate of the Cube
	 */
	public Cube(double X, double Y, double Z) {
		this.x = X;
		this.y = Y;
		this.z = Z;
		this.center = false;
	}

	// Add margins to the sides of a cube
	/**
	 * Resizes all faces of cube to specified size.
	 * 
	 * @param xn
	 *            the negative x resizing
	 * @param xp
	 *            the positive x resizing
	 * @param yn
	 *            the negative y resizing
	 * @param yp
	 *            the positive y resizing
	 * @param zn
	 *            the negative z resizing
	 * @param zp
	 *            the positive z resizing
	 * @return ScadObject with applied transformations
	 */
	public ScadObject resize(double xn, double xp, double yn, double yp, double zn, double zp) {
		return (new Translate(new Cube(this.getX() + xn + xp, this.getY() + yn + yp, this.getZ() + zn + zp, true),
				0.5 * xp - 0.5 * xn, 0.5 * yp - 0.5 * yn, 0.5 * zp - 0.5 * zn));
	}

	// getter - setter
	/**
	 * Returns the x coordinate of the Cube.
	 * 
	 * @return x coordinate of the Cube
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the x coordinate for the Cube.
	 * 
	 * @param x
	 *            the x coordinate
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Returns the y coordinate of the Cube.
	 * 
	 * @return y coordinate of the Cube
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the y coordinate for the Cube.
	 * 
	 * @param y
	 *            the y coordinate
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Returns the z coordinate of the Cube.
	 * 
	 * @return z coordinate of the Cube
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Sets the z coordinate for the Cube.
	 * 
	 * @param z
	 *            the z coordinate
	 */
	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * Returns a String of the Cube used for creating it in OpenSCAD.
	 * 
	 * @return String of the Cube
	 */
	@Override
	public String toString() {
		// Locale.UK is required to obtain the '.' as the decimal indicator
		return (String.format(Locale.UK, CUBE, x, y, z, center));
	}

}
