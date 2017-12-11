package render.objects;

import java.util.ArrayList;
import java.util.Locale;

import graph.Vector;
import render.ScadObject;

public class Translate implements ScadObject {
	// arraylist of scadobjects for translating
	private ArrayList<ScadObject> objects;

	// x, y and z coordinates for the translation
	private double x, y, z;

	// layout string for creating the translate object in openscad
	final static String translate = "translate([%1$.2f, %2$.2f, %3$.2f]){\n%4$s}";

	// constructors
	/**
	 * Constructor of the Translate class using an ArrayList of objects and x, y
	 * and z coordinates.
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

	/**
	 * Constructor of the Translate class using an object and x, y and z
	 * coordinates.
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

	/**
	 * Constructor of the Translate class using an object, a Vector and a z
	 * coordinate.
	 * 
	 * @param object
	 *            the ScadObject to translate
	 * @param v
	 *            the Vector for of the x and y coordinate
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
	/**
	 * Returns the ArrayList of ScadObjects of the Translation.
	 * 
	 * @return ArrayList of objects to translate
	 */
	public ArrayList<ScadObject> getObjects() {
		return objects;
	}

	/**
	 * Sets the ArrayList of ScadObjects of the Translation.
	 * 
	 * @param objects
	 *            the ArrayList of objects of the Translation
	 */
	public void setObjects(ArrayList<ScadObject> objects) {
		this.objects = objects;
	}

	/**
	 * Returns the x coordinate of the Translation.
	 * 
	 * @return x coordinate of the Translation
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the x coordinate of the Translation.
	 * 
	 * @param x
	 *            the x coordinate
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Returns the y coordinate of the Translation.
	 * 
	 * @return y coordinate of the Translation
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the y coordinate of the Translation.
	 * 
	 * @param y
	 *            the new y coordinate
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Returns the z coordinate of the Translation.
	 * 
	 * @return z coordinate of the Translation
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Sets the z coordinate of the Translation.
	 * 
	 * @param z
	 *            the new z coordinate
	 */
	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * Returns a String of the Translate object used for creating it in
	 * OpenSCAD.
	 * 
	 * @return String of Translate
	 */
	@Override
	public String toString() {
		String objectsprint = "";

		// adds all the ScadObjects to the Translation
		for (ScadObject o : objects) {
			objectsprint = objectsprint.concat("\t" + o.toString());
		}

		return String.format(Locale.UK, translate, x, y, z, objectsprint);
	}

}
