package render.objects;

import java.util.ArrayList;
import java.util.Locale;

import render.ScadObject;

public class Scale implements ScadObject {

	// the ArrayList of ScadObjects to scale
	private ArrayList<ScadObject> objects;

	// the x, y and z value for scaling
	private double x, y, z;

	// the layout String for creating the Scale object
	final static String scale = "scale([%1$.3f, %2$.3f, %3$.3f]){\n%4$s}";

	// constructors
	// scaling multiple ScadObjects
	/**
	 * Creates a new Scale object for scaling multiple ScadObjects.
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

	// scaling a single ScadObject
	/**
	 * Creates a new Scale object for scaling a single ScadObject.
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

	// printing the String for creating the Scale object
	/**
	 * Prints a String used for creating the Scale object.
	 */
	@Override
	public String printCommand() {
		String objectsprint = "";
		// adds all ScadObjects to the Scale
		for (ScadObject o : objects) {
			objectsprint = objectsprint.concat("\t" + o.printCommand());
		}
		String s = String.format(Locale.UK, scale, x, y, z, objectsprint);
		return s;
	}

}
