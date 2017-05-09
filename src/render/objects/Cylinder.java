package render.objects;

import java.util.Locale;

import render.ScadObject;

public class Cylinder implements ScadObject {

	// the Cylinders height, bottomRadius and the topRadius
	private double height, radius;

	// the boolean to determine if the Cylinder will be centered
	private boolean center;

	// the layout String for creating the Cylinder
	final static String cylinder = "cylinder(h = %1$.3f, r = %2$.3f, center = %3$s, $fa = %4$d, $fs = 0.01);\n";

	// the layout String for additionally scaling the Cylinder to obtain a
	// higher resolution
	// final static String scale = "scale([%1$.3f, %2$.3f, %3$.3f]){\n%4$s}";

	// constructors
	// without the centered boolean
	/**
	 * Creates a new Cylinder object using the Cylinders height, bottom radius
	 * and top radius.
	 * 
	 * @param height
	 *            the Cylinders height
	 * @param bottomRadius
	 *            the Cylinders bottom radius
	 * @param topRadius
	 *            the Cylinders top radius
	 */
	public Cylinder(double height, double radius) {
		super();
		this.height = height;
		this.radius = radius;
		this.center = false;
	}

	// with the centered boolean
	/**
	 * Creates a new Cylinder object using the Cylinders height, bottom radius
	 * and top radius. It also uses a boolean which determines whether or not
	 * the Cylinder will be centered.
	 * 
	 * @param height
	 *            the Cylinders height
	 * @param bottomRadius
	 *            the Cylinders bottom radius
	 * @param topRadius
	 *            the Cylinders top radius
	 * @param Center
	 *            the boolean for centering the Cylinder
	 */
	public Cylinder(double height, double radius, boolean Center) {
		super();
		this.height = height;
		this.radius = radius;
		this.center = Center;
	}

	// getter - setter
	// getting the Cylinders radius

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	// getting the Cylinders height
	/**
	 * Returns the Cylinders height.
	 * 
	 * @return the Cylinders height
	 */
	public double getHeight() {
		return height;
	}

	// setting the height
	/**
	 * Overrides the Cylinders height.
	 * 
	 * @param bottomRadius
	 *            the new height
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	// Adds margin
	public ScadObject resize(double top, double bot, double side) {

		return (new Translate(new Cylinder(this.getHeight() + top + bot, this.getRadius() + side, true), 0, 0, 0.5 * top - 0.5 * bot));

	}

	// prints the command for creating the Cylinder
	/**
	 * Returns a String which can be used to create the Cylinder.
	 */
	@Override
	public String toString() {
		int precision = 5;
		// by scaling a larger Cylinder you get a higher resolution
		String s = String.format(Locale.UK, cylinder, height, radius, center, precision);
		return s;
	}

}
