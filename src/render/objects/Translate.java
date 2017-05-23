package render.objects;

import java.util.ArrayList;
import java.util.Locale;

import graph.Vector;
import render.ScadObject;

public class Translate implements ScadObject {

	// the ArrayList of ScadObjects for translating
	private ArrayList<ScadObject> objects;

	// the x, y and z coordinates for the Translation
	private double x, y, z;

	// the layout String for creating the Translate object
	final static String translate = "translate([%1$.2f, %2$.2f, %3$.2f]){\n%4$s}";

	// constructors
	// translating multiple ScadObjects
	/**
	 * Creates a new Translate object for translating multiple ScadObjects.
	 * 
	 * @param object
	 *            the ArrayList of ScadObjects for translating
	 * @param x
	 *            the x coordinate for translating
	 * @param y
	 *            the y coordinate for translating
	 * @param z
	 *            the z coodrinate for translating
	 * @see ScadObject
	 */
	public Translate(ArrayList<ScadObject> object, double x, double y, double z) {
		super();
		this.objects = object;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	// translating a single ScadObject
	/**
	 * Creates a new Translate object used for translating a single ScadObject.
	 * 
	 * @param object
	 *            the ScadObject to translate
	 * @param x
	 *            the x coordinate of the translation
	 * @param y
	 *            the y coordinate of the translation
	 * @param z
	 *            the z coordinate of the translation
	 * @see ScadObject
	 */
	public Translate(ScadObject object, double x, double y, double z) {
		super();
		this.objects = new ArrayList<>();
		this.objects.add(object);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	// translating a single ScadObject using a Vector
	/**
	 * Creates a new Translate object for translating a single ScadObject using
	 * a Vector to determine the x and y coordinates.
	 * 
	 * @param object
	 *            the ScadObject to translate
	 * @param v
	 *            the Vector for determining the x and y coordinate of the
	 *            Translation
	 * @param z
	 *            the z coordinate of the Translation
	 * @see ScadObject, Vector
	 */
	public Translate(ScadObject object, Vector v, double z) {
		super();
		this.objects = new ArrayList<>();
		this.objects.add(object);
		this.x = v.getX();
		this.y = v.getY();
		this.z = z;
	}

	// getter - setter
	// getting the ArrayList of ScadObjects
	/**
	 * Returns the ArrayList of ScadObjects of the Translation.
	 * 
	 * @return the ArrayList ScadObjects to translate
	 */
	public ArrayList<ScadObject> getObjects() {
		return objects;
	}

	// setting the ArrayList of ScadObjects
	/**
	 * Overrides the ArrayList of ScadObjects with a new ArrayList.
	 * 
	 * @param objects
	 *            the new ArrayList of ScadObjects
	 */
	public void setObjects(ArrayList<ScadObject> objects) {
		this.objects = objects;
	}

	// getting the x coordinate
	/**
	 * Returns the x coordinate of the Translation.
	 * 
	 * @return the x coordinate of the Translation
	 */
	public double getX() {
		return x;
	}

	// setting the x coordinate
	/**
	 * Overrides the x coordinate of the Translation with a new x coordinate.
	 * 
	 * @param x
	 *            the new x coordinate
	 */
	public void setX(double x) {
		this.x = x;
	}

	// getting the y coordinate
	/**
	 * Returns the y coordinate of the Translation.
	 * 
	 * @return the y coordinate of the Translation
	 */
	public double getY() {
		return y;
	}

	// setting the y coordinate
	/**
	 * Overrides the y coordinate of the Translation with a new y coordinate.
	 * 
	 * @param y
	 *            the new y coordinate
	 */
	public void setY(double y) {
		this.y = y;
	}

	// getting the z coordinate
	/**
	 * Returns the z coordinate of the Translation.
	 * 
	 * @return the z coordinate of the Translation
	 */
	public double getZ() {
		return z;
	}

	// setting the z coordinate
	/**
	 * Overrrides the z coordinate of the Translation with a new z coordinate.
	 * 
	 * @param z
	 *            the new z coordinate
	 */
	public void setZ(double z) {
		this.z = z;
	}

	// printing the String for creating the Translate object
	/**
	 * Prints the String used for creating the Translate object.
	 */
	@Override
	public String toString() {
		String objectsprint = "";
		// adds all the ScadObjects to the Translation
		for (ScadObject o : objects) {
			objectsprint = objectsprint.concat("\t" + o.toString());
		}
		String s = String.format(Locale.UK, translate, x, y, z, objectsprint);
		return s;
	}

}
