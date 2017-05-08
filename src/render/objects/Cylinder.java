package render.objects;

import java.util.Locale;

import render.ScadObject;

public class Cylinder implements ScadObject {

	// the Cylinders height, bottomRadius and the topRadius
	private double height, bottomRadius, topRadius;

	// the boolean to determine if the Cylinder will be centered
	private boolean center;

	// the layout String for creating the Cylinder
	final static String cylinder = "cylinder(%1$.3f,%2$.3f,%3$.3f, %4$s);\n";

	// the layout String for additionally scaling the Cylinder to obtain a
	// higher resolution
	final static String scale = "scale([%1$.3f, %2$.3f, %3$.3f]){\n%4$s}";

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
	public Cylinder(double height, double bottomRadius, double topRadius) {
		super();
		this.height = height;
		this.bottomRadius = bottomRadius;
		this.topRadius = topRadius;
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
	public Cylinder(double height, double bottomRadius, double topRadius, boolean Center) {
		super();
		this.height = height;
		this.bottomRadius = bottomRadius;
		this.topRadius = topRadius;
		this.center = Center;
	}

	// getter - setter
	// getting the Cylinders bottom radius
	/**
	 * Returns the Cylinders bottom radius.
	 * 
	 * @return the Cylinders bottom radius
	 */
	public double getBottomRadius() {
		return bottomRadius;
	}

	// setting the bottom radius
	/**
	 * Overrides the Cylinders bottom radius.
	 * 
	 * @param bottomRadius
	 *            the new bottom radius
	 */
	public void setBottomRadius(double bottomRadius) {
		this.bottomRadius = bottomRadius;
	}

	// getting the Cylinders top radius
	/**
	 * Returns the Cylinders top radius.
	 * 
	 * @return the Cylinders top radius
	 */
	public double getTopRadius() {
		return topRadius;
	}

	// setting the top radius
	/**
	 * Overrides the Cylinders top radius.
	 * 
	 * @param bottomRadius
	 *            the new top radius
	 */
	public void setTopRadius(double topRadius) {
		this.topRadius = topRadius;
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

	// scalar multiplication of a Cylinder
	/**
	 * Returns a Cylinder which has been created through scalar multiplication
	 * of the old Cylinder with a specified factor.
	 * 
	 * @param a
	 *            the factor used for multiplication
	 * @return the multiplied Cylinder
   */
	public Cylinder multiply(int a) {
		return (new Cylinder(height * a, bottomRadius * a, topRadius * a, center));
	}

	// prints the original instance of the Cylinder
	/**
	 * Prints a String used for creating the Cylinder.
	 * 
	 * @return the String for creating the Cylinder
	 */
	private String cylinderPrintCommand() {
		return (String.format(Locale.UK, cylinder, height, bottomRadius, topRadius, center));
	}

	// prints the command for creating the Cylinder
	/**
	 * Returns a String which can be used to create the Cylinder.
	 */
	@Override
	public String printCommand() {
		int a = 100;
		double a2 = 1.0 / a;
		// by scaling a larger Cylinder you get a higher resolution
		String s = String.format(Locale.UK, scale, a2, a2, a2, this.multiply(a).cylinderPrintCommand());
		return s;
	}

}
