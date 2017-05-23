package render.objects;

import java.util.ArrayList;
import java.util.Locale;

import graph.*;
import render.ScadObject;

public class Polygon implements ScadObject {

	// the ArrayList of Points used by the Polygon
	private ArrayList<Vector> points;
	
	//incident Edge
	private Edge incidentEdge;

	// delta value for offset used in basePlate calculation
	private double delta;

	// the layout String for creating the Polygon
	final static String polygon = "polygon(%1s,10);\n";

	// the layout String for extruding the Polygon
	final static String linear_extrude = "linear_extrude(%1$.2f, center = true){\n%2$s}";

	// scad string for the delta offset
	final static String OFFSET = "offset(delta = %1$.3f){%2$s}";

	// constructor using ArrayList of points
	/**
	 * Creates a new Polygon object using an ArrayList of position vectors.
	 * 
	 * @param points
	 *            the ArrayList of position vectors
	 */
	public Polygon(ArrayList<Vector> points, double delta) {
		this.points = points;
		this.delta = delta;
	}

	/**
	 * Constructor of Polygon class using a DCEL Edge
	 * @param e DCEL Edge
	 */
	public Polygon(Edge e) {
		incidentEdge = e;
		points = new ArrayList<>();
		ArrayList<Node> nodes = e.getFace().getNodes();
		for (Node n : nodes) {
			getPoints().add(n.getOrigin());
		}
		this.delta = 0.0;
	}

	/**
	 * Constructor of Polygon class using a DCEL Edge and a delta Offset
	 * @param e DCEL Edge
	 * @param delta Offset
	 */
	public Polygon(Edge e, double delta) {
		incidentEdge = e;
		points = new ArrayList<>();
		ArrayList<Node> nodes = e.getFace().getNodes();
		for (Node n : nodes) {
			getPoints().add(n.getOrigin());
		}
		this.delta = delta;
	}

	// getter - setter
	// getting the ArrayList of points
	/**
	 * Returns the ArrayList of position vectors.
	 * 
	 * @return the ArrayList of vectors
	 */
	public ArrayList<Vector> getPoints() {
		return points;
	}

	// setting the ArrayList of points
	/**
	 * Overrides the ArrayList of position vectors with a new ArrayList.
	 * 
	 * @param points
	 *            the new ArrayList of position vectors
	 */
	public void setPoints(ArrayList<Vector> points) {
		this.points = points;
	}

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

	public Edge getIncidentEdge() {
		return incidentEdge;
	}

	public void setIncidentEdge(Edge incidentEdge) {
		this.incidentEdge = incidentEdge;
	}

	// printing the String to create the Polygon
	/**
	 * Prints a String used for creating the Polygon.
	 */
	@Override
	public String toString() {
		String s = "[";
		for (Vector p : points) {
			s = s.concat(p.toScadString() + ", ");
		}
		s = s.substring(0, s.length() - 2);
		s = s.concat("]");
		//polygon string
		s = String.format(Locale.UK, polygon, s);
		//offset string
		if(getDelta() != 0.0){
		s = String.format(Locale.UK, OFFSET ,getDelta(), s);
		}
		//extrude string
		return (String.format(Locale.UK, linear_extrude, 1.0, s));
	}

}
