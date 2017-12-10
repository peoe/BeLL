package render.objects;

import java.util.ArrayList;
import java.util.Locale;

import render.ScadObject;

public class Difference implements ScadObject {
	// arraylist of objects used for the Difference
	private ArrayList<ScadObject> objects;

	// layout String for creating the Difference in openscad
	final static String difference = "difference(){\n%s}";

	// constructors
	/**
	 * Constructor of the Difference class using an ArrayList of objects.
	 * 
	 * @param objects
	 *            the ArrayList of ScadObjects
	 * @see ScadObject
	 */
	public Difference(ArrayList<ScadObject> objects) {
		super();
		this.objects = objects;
	}

	/**
	 * Constructor of the Difference class. Creates an empty Difference.
	 * 
	 * @see ScadObject
	 */
	public Difference() {
		super();
		this.objects = new ArrayList<>();
	}

	// getter - setter
	/**
	 * Returns an ArrayList containing all ScadObjects of the Difference.
	 * 
	 * @return ArrayList of all ScadObjects
	 */
	public ArrayList<ScadObject> getObjects() {
		return objects;
	}

	/**
	 * Sets the ArrayList of ScadObjects used by the Difference object.
	 * 
	 * @param objects
	 *            the ArrayList to be used for the Difference
	 */
	public void setObjects(ArrayList<ScadObject> objects) {
		this.objects = objects;
	}

	/**
	 * Returns a String of the Difference used for creating it in OpenSCAD.
	 * 
	 * @return String of the difference
	 */
	@Override
	public String toString() {
		String objectsprint = "";
		// enlists all objects within the Difference
		// all objects are being subtracted from the first object
		for (ScadObject o : objects) {
			objectsprint = objectsprint.concat("\t" + o.toString());
		}
		return String.format(Locale.UK, difference, objectsprint);
	}

}
