package render;

import java.util.ArrayList;

import graph.*;
import render.objects.Cube;
import render.objects.Difference;
import render.objects.Rotate;
import render.objects.Scale;
import render.objects.Translate;

public class CornerCylinder implements ScadObject {
	// epsilon value of the corner cylinder
	private double epsilon;

	// node of the cylinder
	private Node n;

	// params object
	private Params params;

	/**
	 * Constructor of the CornerCylinder class using a Node, an epsilon value
	 * and a Params object.
	 * 
	 * @param n
	 *            the Node of the CornerCylinder
	 * @param epsilon
	 *            the epsilon value of the CornerCylinder
	 * @param params
	 *            the Params object of the CornerCylinder
	 */
	public CornerCylinder(Node n, double epsilon, Params params) {
		this.epsilon = epsilon;
		this.n = n;
		this.params = params;
	}

	/**
	 * Returns the rotated Cube used for the Difference in the CornerCylinder.
	 * 
	 * @param angle
	 *            the angle of the minus tile
	 * @return ScadObject of the minus tile
	 */
	private ScadObject getMinusTileCorner(double angle) {
		double cornerRadius = params.getCornerRadius();
		double wallWidth = params.getWallWidth() / 2 - 2 * getEpsilon();
    
		Cube tileCube = new Cube(cornerRadius, wallWidth, 4, true);

		Translate tTileCube = new Translate(tileCube, cornerRadius + getEpsilon(), 0, 0);

		return (new Rotate(tTileCube, angle, 0, 0, 1));
	}

	/**
	 * Returns the Cylinder of the Node with Differences for all Walls.
	 * 
	 * @return ScadObject of the CornerCylinder
	 */
	public ScadObject getCornerCylinder() {
		ArrayList<ScadObject> cornerBaseDifference = new ArrayList<>();
		ArrayList<Edge> cornerEdges = n.getAdjacentEdges();

		// adds bottom circular corner base tile used for the difference
		cornerBaseDifference.add(new CornerBaseTile(1, getEpsilon(), params));

		for (Edge cornerEdge : cornerEdges) {
			cornerBaseDifference.add(getMinusTileCorner(cornerEdge.toVector().angleInDegrees()));
		}

		// scale to fit wall height
		// translate to move the object to the right position
		return new Translate(
				new Scale(new Difference(cornerBaseDifference), 1, 1, params.getHeight() - params.getBasePlateHeight()),
				0, 0, params.getBasePlateHeight() + 0.5 * (params.getHeight() - params.getBasePlateHeight()));
	}

	/**
	 * Returns the cylinder of the Node with a Difference for a specific Wall.
	 * 
	 * @return ScadObject of CornerCylinder
	 */
	public ScadObject getCornerCylinder(Edge e) {
		Difference cornerBaseDifference = new Difference();

		// adds bottom circular corner base tile used for the difference
		cornerBaseDifference.getObjects().add(new CornerBaseTile(1, getEpsilon(), params));

		// edge must start in n
		if (e.getN1() != getN()) {
			e = e.getTwin();
		}

		// adds differences
		cornerBaseDifference.getObjects().add(getMinusTileCorner(e.toVector().angleInDegrees()));

		// scale to fit wall height
		// translate to move the object to the right position
		return new Translate(new Scale(cornerBaseDifference, 1, 1, params.getHeight() - params.getBasePlateHeight()), 0,
				0, params.getBasePlateHeight() + 0.5 * (params.getHeight() - params.getBasePlateHeight()));
	}

	// getters - setters
	/**
	 * Returns the epsilon value of the CornerCylinder.
	 * 
	 * @return epsilon as Double value
	 */
	public double getEpsilon() {
		return epsilon;
	}

	/**
	 * Sets the epsilon value of the CornerCylinder.
	 * 
	 * @param epsilon
	 *            the value to be set for the CornerCylinder
	 */
	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

	/**
	 * Returns the Node of the CornerCylinder.
	 * 
	 * @return Node of CornerCylinder
	 */
	public Node getN() {
		return n;
	}

	/**
	 * Sets the Node of the CornerCylinder.
	 * 
	 * @param n
	 *            the Node to be set for the CornerCylinder
	 */
	public void setN(Node n) {
		this.n = n;
	}

	/**
	 * Returns the Params object of the CornerCylinder.
	 * 
	 * @return Params object
	 */
	public Params getParams() {
		return params;
	}

	/**
	 * Sets the Params object of the CornerCylinder.
	 * 
	 * @param params
	 *            the Params object to be set for the CornerCylinder
	 */
	public void setParams(Params params) {
		this.params = params;
	}

	/**
	 * Returns a String if the CornerCylinder used for creating it in OpenSCAD.
	 * 
	 * @return String of CornerCylinder
	 */
	@Override
	public String toString() {
		return getCornerCylinder().toString();
	}

}
