package render;

import java.util.ArrayList;
import java.util.Comparator;

import graph.*;
import render.objects.*;

public class Corner implements ScadObject {
	private Node n;
	// distance between objects
	private double Epsilon;
	// scadobject
	private ScadObject object;
	// params
	private Params params;
	// width
	private double width;

	// constructors
	/**
	 * Constructor of the Corner class using a Node, an epsilon value and a
	 * Params object.
	 * 
	 * @param n
	 *            the Node of the Corner
	 * @param epsilon
	 *            the epsilon value of the Corner
	 * @param params
	 *            the Params object of the Corner
	 */
	public Corner(Node n, double epsilon, Params params) {
		this.n = n;
		Epsilon = epsilon;
		this.params = params;
		this.object = getCorner();
	}

	/**
	 * Constructor of the Corner class using a Node and a Params object.
	 * 
	 * @param n
	 *            the Node of the Corner
	 * @param params
	 *            the Params object of the Corner
	 */
	public Corner(Node n, Params params) {
		this.n = n;
		Epsilon = 0.0;
		this.params = params;
		this.object = getCorner();
	}

	/**
	 * Returns the whole Corner object.
	 * 
	 * @return ScadObject of the Corner
	 */
	public ScadObject getCorner() {
		ArrayList<ScadObject> cornerObjects = new ArrayList<>();

		double maxD = 0.0;

		for (Edge e : getN().getAdjacentEdges()) {
			cornerObjects.add(new CornerPin(e, getEpsilon(), params));

			if (((CornerPin) cornerObjects.get(cornerObjects.size() - 1)).calculateD() > maxD) {
				maxD = ((CornerPin) cornerObjects.get(cornerObjects.size() - 1)).calculateD();
			}
		}

		setWidth(2 * maxD + 6 * params.getPinPRadius() + 4 * params.getEpsilon());

		cornerObjects.add(new CornerCylinder(getN(), getEpsilon(), params));
		return new Union(cornerObjects);
	}

	// getters - setters
	/**
	 * Returns the Node of the Corner.
	 * 
	 * @return Node of Corner
	 */
	public Node getN() {
		return n;
	}

	/**
	 * Sets the Node of the Corner.
	 * 
	 * @param n
	 *            the Node to be set for the Corner
	 */
	public void setN(Node n) {
		this.n = n;
	}

	/**
	 * Returns the epsilon value of the Corner.
	 * 
	 * @return epsilon as Double value
	 */
	public double getEpsilon() {
		return Epsilon;
	}

	/**
	 * Sets the epsilon value of the Corner.
	 * 
	 * @param epsilon
	 *            the value to be set for the Corner
	 */
	public void setEpsilon(double epsilon) {
		Epsilon = epsilon;
	}

	/**
	 * Returns the Params object of the Corner.
	 * 
	 * @return Params object of Corner
	 */
	public Params getParams() {
		return params;
	}

	/**
	 * Sets the Params object of the Corner
	 * 
	 * @param params
	 *            the Params object to be set for the Corner
	 */
	public void setParams(Params params) {
		this.params = params;
	}

	/**
	 * Returns the ScadObject of the Corner.
	 * 
	 * @return ScadObject of Corner
	 */
	public ScadObject getObject() {
		return object;
	}

	/**
	 * Sets the ScadObject of the Corner.
	 * 
	 * @param object
	 *            the ScadObject to be set for the Corner
	 */
	public void setObject(ScadObject object) {
		this.object = object;
	}

	/**
	 * Returns the width of the Corner.
	 * 
	 * @return width as Double value
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Sets the width of the Corner
	 * 
	 * @param width
	 *            the value to be set for the Corner
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * Comparator used for ArrayList sorting
	 * sorts after Corner width
	 */
	public static Comparator<Corner> CornerWidthComparator = new Comparator<Corner>() {

		public int compare(Corner c1, Corner c2) {
		   return Double.compare(c1.getWidth(), c2.getWidth());
	    }};
	
	/**
	 * Returns a String of the Corner used for creating it in OpenSCAD.
	 * 
	 * @return String of Corner
	 */
	@Override
	public String toString() {
		return getObject().toString();
	}

}
