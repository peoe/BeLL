package render.objects;

import java.util.ArrayList;
import java.util.Locale;

import render.ScadObject;

public class Intersection implements ScadObject {

	// ArrayList containing all ScadObjects
	private ArrayList<ScadObject> objects;

	// layout String for creating Intersections
	final static String intersection = "intersection(){\n%s}";

	// constructor using an ArrayList of ScadObjects
	/**
	 * Creates a new Intersection object using an ArrayList of ScadObjects.
	 * 
	 * @param objects
	 *            the ArrayList of ScadObjects
	 * @see ScadObject
	 */
	public Intersection(ArrayList<ScadObject> objects) {
		super();
		this.objects = objects;
	}

	// getter - setter
	// getting the ScadObjects
	/**
	 * Returns an ArrayList of ScadObjects used by the Intersection object.
	 * 
	 * @return the ArrayList of ScadObjects
	 */
	public ArrayList<ScadObject> getObjects() {
		return objects;
	}

	// setting the ScadObjects
	/**
	 * Overrides the ArrayList of ScadObjects with a new ArrayList.
	 * 
	 * @param objects
	 *            the new ArrayList of ScadObjects
	 */
	public void setObjects(ArrayList<ScadObject> objects) {
		this.objects = objects;
	}

	// printing the command for creating the Intersection
	/**
	 * Prints a String used for creating the Intersection.
	 */
	@Override
	public String printCommand() {
		String objectsprint = "";
		// enlists all ScadObjects from the ArrayList
		// Intersects all objects from the first
		for (ScadObject o : objects) {
			objectsprint = objectsprint.concat("\t" + o.printCommand());
		}
		String s = String.format(Locale.UK, intersection, objectsprint);
		return s;
	}

}
