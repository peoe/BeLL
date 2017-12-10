package render.objects;

import java.util.ArrayList;
import java.util.Locale;

import graph.*;
import render.ScadObject;

public class Polygon implements ScadObject {
	// arraylist of points used by the polygon
	private ArrayList<Vector> points;

	// incident Edge
	private Edge incidentEdge;

	// delta value for offset used in baseplate calculation
	private double delta;

	// layout string for creating the polygon in openscad
	final static String polygon = "polygon(%1s,10);\n";

	// layout string for extruding the polygon in openscad
	final static String linear_extrude = "linear_extrude(%1$.2f, center = true){\n%2$s}";

	// layout string for the delta offset in openscad
	final static String OFFSET = "offset(delta = %1$.3f){%2$s}";

	// constructors
	/**
	 * Constructor of the Polygon class using an ArrayList of points and an
	 * offset value.
	 * 
	 * @param points
	 *            the ArrayList of position Vectors
	 * @param delta
	 *            the offset value
	 */
	public Polygon(ArrayList<Vector> points, double delta) {
		this.points = points;
		this.delta = delta;
	}

	/**
	 * Constructor of the Polygon class using an Edge.
	 * 
	 * @param e
	 *            the Edge
	 */
	public Polygon(Edge e) {
		incidentEdge = e;
		points = new ArrayList<>();

		// create polygon from face
		ArrayList<Node> nodes = e.getFace().getNodes();
		for (Node n : nodes) {
			getPoints().add(n.getOrigin());
		}

		this.delta = 0.0;
	}

	/**
	 * Constructor of the Polygon class using an Edge and an offset value.
	 * 
	 * @param e
	 *            the Edge
	 * @param delta
	 *            the offset value
	 */
	public Polygon(Edge e, double delta) {
		incidentEdge = e;
		points = new ArrayList<>();

		// create polygon from face
		ArrayList<Node> nodes = e.getFace().getNodes();
		for (Node n : nodes) {
			getPoints().add(n.getOrigin());
		}

		this.delta = delta;
	}

	// getter - setter
	/**
	 * Returns the ArrayList of position Vectors.
	 * 
	 * @return ArrayList of Vectors
	 */
	public ArrayList<Vector> getPoints() {
		return points;
	}

	/**
	 * Sets the ArrayList of position Vectors of the Polygon.
	 * 
	 * @param points
	 *            the ArrayList of position Vectors
	 */
	public void setPoints(ArrayList<Vector> points) {
		this.points = points;
	}

	/**
	 * returns the offset value.
	 * 
	 * @return offset value
	 */
	public double getDelta() {
		return delta;
	}

	/**
	 * Sets the offset value.
	 * 
	 * @param delta
	 *            the offset value
	 */
	public void setDelta(double delta) {
		this.delta = delta;
	}

	/**
	 * Returns the incident Edge of the Polygon.
	 * 
	 * @return incident Edge
	 */
	public Edge getIncidentEdge() {
		return incidentEdge;
	}

	/**
	 * Sets the incident Edge of the Polygon.
	 * 
	 * @param incidentEdge
	 *            the incident Edge
	 */
	public void setIncidentEdge(Edge incidentEdge) {
		this.incidentEdge = incidentEdge;
	}

	/**
	 * Returns a String used for creating the Polygon in OpenSCAD.
	 * 
	 * @return String of Polygon
	 */
	@Override
	public String toString() {
		String s = "[";

		for (Vector p : points) {
			s = s.concat(p.toScadString() + ", ");
		}

		s = s.substring(0, s.length() - 2);
		s = s.concat("]");

		// polygon string
		s = String.format(Locale.UK, polygon, s);

		// offset string
		if (getDelta() != 0.0) {
			s = String.format(Locale.UK, OFFSET, getDelta(), s);
		}

		// extrude string
		return (String.format(Locale.UK, linear_extrude, 1.0, s));
	}

}
