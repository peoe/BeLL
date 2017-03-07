package rend.objects;

import java.util.ArrayList;
import java.util.Locale;

import rend.ScadObject;

public class Rotate implements ScadObject {

	// ArrayList of ScadObjects to rotate
	private ArrayList<ScadObject> objects;

	// the angle in degree measure used for rotating
	private double a;

	// the coordinates for rotating in 3D-space
	private int x, y, z;

	// the layout String for creating a Rotation
	final static String rotate = "rotate(%1$.2f,[%2$d,%3$d,%4$d]){\n%5$s}";

	// constructors
	// rotating multiple objects
	/**
	 * Creates a Rotate object which rotates multiple ScadObjects.
	 * 
	 * @param objects
	 *            the ArrayList of ScadObjects
	 * @param a
	 *            the angle in degree measure used for rotating
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

	// rotating a single ScadObject
	/**
	 * Creates a new Rotate object for rotating a single ScadObject.
	 * 
	 * @param object
	 *            the ScadObject to rotate
	 * @param a
	 *            the angle in degree measure for rotating
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

	// printing the String for creating the Rotate object
	/**
	 * Prints the String used for creating the Rotate object.
	 */
	@Override
	public String printCommand() {
		String objectsprint = "";
		// rotate every ScadObject in the ArrayList
		for (ScadObject o : objects) {
			objectsprint = objectsprint.concat("\t" + o.printCommand());
		}
		String s = String.format(Locale.UK, rotate, a, x, y, z, objectsprint);
		return s;
	}

}
