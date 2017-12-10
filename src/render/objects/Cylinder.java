package render.objects;

import java.util.Locale;

import render.ScadObject;

public class Cylinder implements ScadObject {

	// height, bottomRadius and topRadius of the Cylinder
	private double height, radius;

	// boolean if the Cylinder will be centered
	private boolean center;

	// layout String for creating the Cylinder in OpenSCAD
	final static String cylinder = "cylinder(h = %1$.3f, r = %2$.3f, center = %3$s, $fa = %4$d, $fs = 0.01);\n";

	// constructors
	/**
	 * Constructor of the Cylinder class using height and radius of the
	 * Cylinder. Assumes that the Cylinder will not be centered.
	 * 
	 * @param height
	 *            the Cylinders height
	 * @param radius
	 *            the Cylinders radius
	 */
	public Cylinder(double height, double radius) {
		super();
		this.height = height;
		this.radius = radius;
		this.center = false;
	}

	/**
	 * Constructor of the Cylinder class using height and radius of the Cylinder
	 * and a Boolean for centering the Cylinder.
	 * 
	 * @param height
	 *            the Cylinders height
	 * @param radius
	 *            the Cylinders radius
	 * @param Center
	 *            the boolean for centering the Cylinder
	 */
	public Cylinder(double height, double radius, boolean Center) {
		super();
		this.height = height;
		this.radius = radius;
		this.center = Center;
	}

	/**
	 * Resizes the Cylinder by specified amount.
	 * 
	 * @param top
	 *            the amount for top resizing
	 * @param bot
	 *            the amount for bottom resizing
	 * @param side
	 *            the amount for side resizing
	 * @return ScadObject with applied transformations
	 */
	public ScadObject resize(double top, double bot, double side) {
		return (new Translate(new Cylinder(this.getHeight() + top + bot, this.getRadius() + side, true), 0, 0,
				0.5 * top - 0.5 * bot));
	}

	// getter - setter
	/**
	 * Returns the radius of the Cylinder.
	 * 
	 * @return Cylinders radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Sets the radius of the Cylinder.
	 * 
	 * @param radius
	 *            the radius to be set for the Cylinder
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * Returns the Cylinders height.
	 * 
	 * @return Cylinders height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Overrides the Cylinders height.
	 * 
	 * @param height
	 *            the height to be set for the Cylinder
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Returns a String of the Cylinder used for creating it in OpenSCAD.
	 * 
	 * @return String of the Cylinder
	 */
	@Override
	public String toString() {
		int precision = 5;
		// by scaling a larger Cylinder you get a higher resolution
		return String.format(Locale.UK, cylinder, height, radius, center, precision);
	}

}
