package render.objects;

import java.util.ArrayList;
import java.util.Locale;

import render.ScadObject;

public class Union implements ScadObject {

	// ArrayList of ScadObjects for the Union
	private ArrayList<ScadObject> objects;

	// the layout String for creating the Union
	final static String union = "union(){\n%s}";

	// constructor using an ArrayList
	/**
	 * Creates a new Union object using an ArrayList of ScadObjects.
	 * 
	 * @param objects
	 *            the ArrayList of ScadObjects
	 * @see ScadObject
	 */
	public Union(ArrayList<ScadObject> objects) {
		super();
		this.objects = objects;
	}

	// getter - setter
	// getting the ArrayList of ScadObjects
	/**
	 * Returns the ArrayList of ScadObjects used to create the Union.
	 * 
	 * @return the ArrayList of ScadObjects
	 */
	public ArrayList<ScadObject> getObjects() {
		return objects;
	}

	// setting the ArrayList
	/**
	 * Overrides the ArrayList of ScadObjects with a new ArrayList.
	 * 
	 * @param objects
	 *            the new ArrayList of ScadObjects
	 */
	public void setObjects(ArrayList<ScadObject> objects) {
		this.objects = objects;
	}

	// printing the String for creating the Union object
	/**
	 * Prints a String used for creating the Union object.
	 */
	@Override
	public String toString() {
		String objectsprint = "";
		// adds all the ScadObjects to the Union object
		for (ScadObject o : objects) {
			objectsprint = objectsprint.concat("\t" + o.toString());
		}
		String s = String.format(Locale.UK, union, objectsprint);
		return s;
	}

}
