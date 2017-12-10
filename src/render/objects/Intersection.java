package render.objects;

import java.util.ArrayList;
import java.util.Locale;

import render.ScadObject;

public class Intersection implements ScadObject {
	// arraylist containing all scadobjects
	private ArrayList<ScadObject> objects;

	// layout atring for creating intersection in openscad
	final static String intersection = "intersection(){\n%s}";

	// constructors
	/**
	 * Constructor of the Intersection class using an ArrayList of objects.
	 * 
	 * @param objects
	 *            the ArrayList of ScadObjects
	 * @see ScadObject
	 */
	public Intersection(ArrayList<ScadObject> objects) {
		super();
		this.objects = objects;
	}

	/**
	 * Constructor of the Intersection class. Creates an empty Intersection.
	 * 
	 * @see ScadObject
	 */
	public Intersection() {
		super();
		this.objects = new ArrayList<>();
	}

	// getter - setter
	/**
	 * Returns an ArrayList of ScadObjects used by the Intersection object.
	 * 
	 * @return ArrayList of ScadObjects
	 */
	public ArrayList<ScadObject> getObjects() {
		return objects;
	}

	/**
	 * Sets the ArrayList of ScadObjects used for the Intersection.
	 * 
	 * @param objects
	 *            the ArrayList of ScadObjects
	 */
	public void setObjects(ArrayList<ScadObject> objects) {
		this.objects = objects;
	}

	/**
	 * Returns a String of the Intersection used for creating it in OpenSCAD.
	 * 
	 * @return String of the Intersection
	 */
	@Override
	public String toString() {
		String objectsprint = "";
		// enlists all ScadObjects from the ArrayList
		// Intersects all objects from the first
		for (ScadObject o : objects) {
			objectsprint = objectsprint.concat("\t" + o.toString());
		}
		return String.format(Locale.UK, intersection, objectsprint);
	}

}
