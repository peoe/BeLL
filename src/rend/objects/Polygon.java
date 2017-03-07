package rend.objects;

import rend.ScadObject;

import java.util.ArrayList;
import java.util.Locale;

import graph.*;

public class Polygon implements ScadObject {

	// the ArrayList of Points used by the Polygon
	private ArrayList<Vector> points;

	// the height of the Polygon
	private double height;

	// the layout String for creating the Polygon
	final static String polygon = "polygon(%1s,10);\n";

	// the layout String for extruding the Polygon
	final static String linear_extrude = "linear_extrude(%1$.2f){\n%s}";

	// constructor using ArrayList of points
	/**
	 * Creates a new Polygon object using an ArrayList of position vectors.
	 * 
	 * @param points
	 *            the ArrayList of position vectors
	 */
	public Polygon(ArrayList<Vector> points) {
		this.points = points;
	}

	// getter - setter
	// getting the ArrayList of points
	/**
	 * Returns the ArrayList of position vectors.
	 * 
	 * @return the ArrayList of vectors
	 */
	public ArrayList<Vector> getPoints() {
		return points;
	}

	// setting the ArrayList of points
	/**
	 * Overrides the ArrayList of position vectors with a new ArrayList.
	 * 
	 * @param points
	 *            the new ArrayList of position vectors
	 */
	public void setPoints(ArrayList<Vector> points) {
		this.points = points;
	}

	// printing the String to create the Polygon
	/**
	 * Prints a String used for creating the Polygon.
	 */
	@Override
	public String printCommand() {
		String s = String.format(Locale.UK, polygon, points.toString());

		return (String.format(Locale.UK, linear_extrude, height, s));
	}

}
