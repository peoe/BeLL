package render.objects;

import java.util.ArrayList;
import java.util.Locale;

import render.ScadObject;

public class Rotate implements ScadObject {
	// arraylist of scadobjects to rotate
	private ArrayList<ScadObject> objects;

	// angle in degrees used for rotating
	private double a;

	// coordinates for rotating in 3D-space
	private int x, y, z;

	// layout string for creating a rotation in openscad
	final static String rotate = "rotate(%1$.2f,[%2$d,%3$d,%4$d]){\n%5$s}";

	// constructors
	/**
	 * Constructor of the Rotate class using an ArrayList of objects, an angle
	 * and x, y and z coordinates.
	 * 
	 * @param objects
	 *            the ArrayList of ScadObjects
	 * @param a
	 *            the angle in degrees
	 * @param x
	 *            the x coordinate for rotating
	 * @param y
	 *            the y coordinate for rotating
	 * @param z
	 *            the z coordinate for rotating
	 * @see ScadObject
	 */
	public Rotate(ArrayList<ScadObject> objects, double a, int x, int y, int z) {
		super();
		this.objects = objects;
		this.a = a;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Constructor of the Rotate class using an object, an angle and x, y and z
	 * coordinates.
	 * 
	 * @param object
	 *            the ScadObject to rotate
	 * @param a
	 *            the angle in degrees
	 * @param x
	 *            the x coordinate for rotating
	 * @param y
	 *            the y coordinate for rotating
	 * @param z
	 *            the z coordinate for rotating
	 * @see ScadObject
	 */
	public Rotate(ScadObject object, double a, int x, int y, int z) {
		super();
		// add the single object to the ArrayList
		this.objects = new ArrayList<>();
		objects.add(object);
		this.a = a;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Returns a String of Rotate used for creating it in OpenSCAD.
	 * 
	 * @return String of Rotate
	 */
	@Override
	public String toString() {
		String objectsprint = "";

		// rotate every ScadObject in the ArrayList
		for (ScadObject o : objects) {
			objectsprint = objectsprint.concat("\t" + o.toString());
		}

		return String.format(Locale.UK, rotate, a, x, y, z, objectsprint);
	}

}
