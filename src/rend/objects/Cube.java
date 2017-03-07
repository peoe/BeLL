package rend.objects;

import java.util.Locale;

import rend.ScadObject;

public class Cube implements ScadObject {

	// x, y and z coordinates of the Cube
	private double x, y, z;

	// boolean to determine if the piece will be centered
	private boolean center;

	// the layout for the String used to generate a Cube
	final static String cube = "cube([%1$.2f,%2$.2f,%3$.2f], %4$s);\n";

	// getter - setter
	// getting the x coordinate
	/**
	 * Returns the x coordinate of the Cube.
	 * 
	 * @return the x coordinate
	 */
	public double getX() {
		return x;
	}

	// overriding the x coordinate
	/**
	 * Overrides the current x coordinate with a new one.
	 * 
	 * @param x
	 *            the new x corrdinate
	 */
	public void setX(double x) {
		this.x = x;
	}

	// getting the y coordinate
	/**
	 * Returns the y coordinate of the Cube.
	 * 
	 * @return the y coordinate
	 */
	public double getY() {
		return y;
	}

	// overriding the y coordinate
	/**
	 * Overrides the current y coordinate with a new one.
	 * 
	 * @param y
	 *            the new y corrdinate
	 */
	public void setY(double y) {
		this.y = y;
	}

	// getting the z coordinate
	/**
	 * Returns the z coordinate of the Cube.
	 * 
	 * @return the z coordinate
	 */
	public double getZ() {
		return z;
	}

	// overriding the z coordinate
	/**
	 * Overrides the current z coordinate with a new one.
	 * 
	 * @param z
	 *            the new z corrdinate
	 */
	public void setZ(double z) {
		this.z = z;
	}

	// constructors
	// using three doubles and a boolean
	/**
	 * Creates a new Cube object using Cubes the x, y and z coordinates and a
	 * boolean to determine if it will be centered.
	 * 
	 * @param X
	 *            the x coordinate
	 * @param Y
	 *            the y coordinate
	 * @param Z
	 *            the z coordinate
	 * @param Center
	 *            the boolean for centering
	 */
	public Cube(double X, double Y, double Z, boolean Center) {
		this.x = X;
		this.y = Y;
		this.z = Z;
		this.center = Center;
	}

	// using only three doubles
	/**
	 * Creates a new Cube object using the Cubes x, y and z coordinate.
	 * 
	 * @param X
	 *            the x coordinate
	 * @param Y
	 *            the y coordinate
	 * @param Z
	 *            the z coordinate
	 */
	public Cube(double X, double Y, double Z) {
		this.x = X;
		this.y = Y;
		this.z = Z;
		this.center = false;
	}

	// printing the command for creating the cube
	/**
	 * Returns a String which can be used to create the Cube.
	 */
	@Override
	public String printCommand() {
		// Locale.UK is required to obtain the '.' as the decimal indicator
		return (String.format(Locale.UK, cube, x, y, z, center));
	}

}
