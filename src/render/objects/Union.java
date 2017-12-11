package render.objects;

import java.util.ArrayList;
import java.util.Locale;

import render.ScadObject;

public class Union implements ScadObject {
	// arraylist of scadobjects for the union
	private ArrayList<ScadObject> objects = new ArrayList<>();

	// layout string for creating the union in openscad
	final static String union = "union(){\n%s}";

	// constructors
	/**
	 * Constructor of the Union class using an ArrayList of ScadObjects.
	 * 
	 * @param objects
	 *            the ArrayList of ScadObjects
	 * @see ScadObject
	 */
	public Union(ArrayList<ScadObject> objects) {
		super();
		this.objects = objects;
	}

	/**
	 * Constructor of the Union class. Creates an empty Union object.
	 * 
	 * @see ScadObject
	 */
	public Union() {
		super();
		this.objects = new ArrayList<>();
	}

	// getter - setter
	/**
	 * Returns the ArrayList of ScadObjects used to create the Union.
	 * 
	 * @return ArrayList of ScadObjects
	 */
	public ArrayList<ScadObject> getObjects() {
		return objects;
	}

	/**
	 * Sets the ArrayList of ScadObjects.
	 * 
	 * @param objects
	 *            the ArrayList of ScadObjects
	 */
	public void setObjects(ArrayList<ScadObject> objects) {
		this.objects = objects;
	}

	/**
	 * Returns a String used for creating the Union object in OpenSCAD.
	 * 
	 * @return String of Union
	 */
	@Override
	public String toString() {
		String objectsprint = "";

		// adds all the ScadObjects to the Union object
		for (ScadObject o : objects) {
			objectsprint = objectsprint.concat("\t" + o.toString());
		}

		return String.format(Locale.UK, union, objectsprint);
	}

}
