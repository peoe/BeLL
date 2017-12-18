package render;

import java.util.ArrayList;
import java.util.Comparator;

import graph.*;
import render.objects.*;

public class Wall implements ScadObject {
	// edge for the wall
	private Edge e;
	// params
	private Params params;

	/**
	 * Constructor of the Wall class using an Edge and a Params object.
	 * 
	 * @param e
	 *            the Edge of the Wall
	 * @param params
	 *            the Params object of the Wall
	 */
	public Wall(Edge e, Params params) {
		this.e = e;
		this.params = params;
	}

	// getters - setters
	/**
	 * Returns the Edge of the Wall.
	 * 
	 * @return Edge of the Wall
	 */
	public Edge getE() {
		return e;
	}

	/**
	 * Sets the Edge of the Wall.
	 * 
	 * @param e
	 *            the Edge to be set for the Wall
	 */
	public void setE(Edge e) {
		this.e = e;
	}

	/**
	 * Returns the Params object of the Wall.
	 * 
	 * @return Params object of the Wall
	 */
	public Params getParams() {
		return params;
	}

	/**
	 * Sets the params object of the Wall.
	 * 
	 * @param params
	 *            the Params object to be set for the Wall
	 */
	public void setParams(Params params) {
		this.params = params;
	}

	/**
	 * Returns the length of the Wall.
	 * 
	 * @return length as Double value
	 */
	public double getLength() {
		return e.toVector().getLength();
	}
	
	/**
	 * Comparator used for ArrayList sorting
	 * sorts after Wall length
	 */
	public static Comparator<Wall> WallLengthComparator = new Comparator<Wall>() {

		public int compare(Wall w1, Wall w2) {
		   return Double.compare(w1.getLength(), w2.getLength());
	    }};

	/**
	 * Returns a String of the Wall used for creating it in OpenSCAD.
	 * 
	 * @return String of Wall
	 */
	@Override
	public String toString() {
		Vector wallVector = e.toVector();

		Cube rawWallCube = new Cube(wallVector.getLength(), params.getWallWidth(), 1, true);

		double wallAngle = wallVector.angleInDegrees();

		ArrayList<ScadObject> wallDifference = new ArrayList<>();

		wallDifference.add(new Rotate(rawWallCube, wallAngle, 0, 0, 1));
		wallDifference.add(
				new Translate(new CornerCylinder(getE().getN1(), params.getEpsilon(), params).getCornerCylinder(getE()),
						wallVector.multiply(-0.5), -0.5 * params.getHeight()));
		wallDifference.add(
				new Translate(new CornerCylinder(getE().getN2(), params.getEpsilon(), params).getCornerCylinder(getE()),
						wallVector.multiply(0.5), -0.5 * params.getHeight()));

		// difference of objects -> rotate -> scale height -> translate
		return new Translate(new Scale(new Difference(wallDifference), 1.0, 1.0,
				params.getHeight() - params.getBasePlateHeight() - params.getEpsilon()), 0, 0, 0).toString();
	}

}
