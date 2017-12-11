package render;

import java.util.ArrayList;

import graph.*;
import render.objects.*;

public class Wall implements ScadObject {

	// the Line object for the Wall to be based upon
	private Edge e;
	//Parameters
	private Params params;
	// constructor
	/**
	 * Creates a new Wall object based upon the given Line object.
	 * 
	 * @param w
	 *            the given Line object
	 */
	public Wall(Edge e, Params params) {
		this.e = e;
		this.params = params;
	}

	public Edge getE() {
		return e;
	}

	public void setE(Edge e) {
		this.e = e;
	}

	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}
	
	public double getLength(){
		return e.toVector().getLength();
	}

	// printing the command for creating the Wall object
	/**
	 * Prints a String which can be used to create the Wall object.
	 */
	@Override
	public String toString() {
		Vector wallVector = e.toVector();

		Cube rawWallCube = new Cube(wallVector.getLength(), params.getWallWidth(), 1, true);

		double wallAngle = wallVector.angleInDegrees();

		ArrayList<ScadObject> wallDifference = new ArrayList<>();
		wallDifference.add(new Rotate(rawWallCube, wallAngle, 0, 0, 1));

		wallDifference.add(new Translate(new CornerCylinder(getE().getN1(), params.getEpsilon(), params).getCornerCylinder(getE()), wallVector.multiply(-0.5),
				-0.5 * params.getHeight()));
		wallDifference.add(new Translate(new CornerCylinder(getE().getN2(), params.getEpsilon(), params).getCornerCylinder(getE()), wallVector.multiply(0.5),
				-0.5 * params.getHeight()));
		// Difference of pre-defined objects -> rotate to wall position -> Scale
		// it to height -> Translate it to wall position -> output toString()
		return new Translate(
				new Scale(new Difference(wallDifference), 1.0, 1.0, params.getHeight() - params.getBasePlateHeight() - params.getEpsilon()),
				0, 0,
				//wallVector.multiply(0.0).add(getE().getN1().getOrigin()),
				0).toString();

	}

}
