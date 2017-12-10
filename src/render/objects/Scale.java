package render.objects;

import java.util.ArrayList;
import java.util.Locale;

import render.ScadObject;

public class Scale implements ScadObject {
	// arraylist of scadobjects to scale
	private ArrayList<ScadObject> objects;

	// x, y and z value for scaling
	private double x, y, z;

	// layout string for creating the scale object in openscad
	final static String scale = "scale([%1$.3f, %2$.3f, %3$.3f]){\n%4$s}";

	// constructors
	/**
	 * Constructor of the Scale class using an ArrayList of objects and x, y and
	 * z values.
	 * 
	 * @param objects
	 *            the ScadObjects to scale
	 * @param x
	 *            the x value for scaling
	 * @param y
	 *            the y value for scaling
	 * @param z
	 *            the z value for scaling
	 * @see ScadObject
	 */
	public Scale(ArrayList<ScadObject> objects, double x, double y, double z) {
		super();
		this.objects = objects;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Constructor of the Scale class using an object and x, y and z values.
	 * 
	 * @param object
	 *            the ScadObject to scale
	 * @param x
	 *            the x value for scaling
	 * @param y
	 *            the y value for scaling
	 * @param z
	 *            the z value for scaling
	 * @see ScadObject
	 */
	public Scale(ScadObject object, double x, double y, double z) {
		super();
		this.objects = new ArrayList<>();
		this.objects.add(object);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Returns a String of the Scale object used for creating it in OpenSCAD.
	 * 
	 * @return String of Scale
	 */
	@Override
	public String toString() {
		String objectsprint = "";
		// adds all ScadObjects to the Scale
		for (ScadObject o : objects) {
			objectsprint = objectsprint.concat("\t" + o.toString());
		}
		String s = String.format(Locale.UK, scale, x, y, z, objectsprint);
		return s;
	}

}
