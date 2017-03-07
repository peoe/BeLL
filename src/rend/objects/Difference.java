package rend.objects;

import java.util.ArrayList;
import java.util.Locale;

import rend.ScadObject;

public class Difference implements ScadObject {

	// all objects used for creating the Difference
	private ArrayList<ScadObject> objects;

	// the layout String for creating the Difference
	final static String difference = "difference(){\n%s}";

	// constructor using an ArryList of ScadObjects
	/**
	 * Creates a Difference object using an ArrayList of ScadObjects.
	 * 
	 * @param objects
	 *            the ArrayList of ScadObjects
	 * @see ScadObject
	 */
	public Difference(ArrayList<ScadObject> objects) {
		super();
		this.objects = objects;
	}

	// getter - setter
	// getting the objects
	/**
	 * Returns an ArrayList containing all ScadObjects given to the Difference.
	 * 
	 * @return the ArrayList with all ScadObjects
	 */
	public ArrayList<ScadObject> getObjects() {
		return objects;
	}

	// setiing the objects
	/**
	 * Overrides the ArrayList of ScadObjects used by the Difference object.
	 * 
	 * @param objects
	 *            the ArrayList to be used in overriding
	 */
	public void setObjects(ArrayList<ScadObject> objects) {
		this.objects = objects;
	}

	// printing the command to create the Difference
	/**
	 * Prints the String used to create the Difference Object.
	 */
	@Override
	public String printCommand() {
		String objectsprint = "";
		// enlists all objects within the Difference
		// all objects ar ebeing substracted by the first
		for (ScadObject o : objects) {
			objectsprint = objectsprint.concat("\t" + o.printCommand());
		}
		String s = String.format(Locale.UK, difference, objectsprint);
		return s;
	}

}
