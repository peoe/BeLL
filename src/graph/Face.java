package graph;

import java.util.ArrayList;

public class Face {

	// a list of all edge vectors
	private ArrayList<Line> edges;

	// constructor
	/**
	 * Creates a new instance of the Face class.
	 */
	public Face() {
		setEdges(new ArrayList<>());
	}

	// get - set
	// getEdges
	/**
	 * Returns the ArrayList containing all of the edges.
	 * 
	 * @return ArrayList of all edges
	 */
	public ArrayList<Line> getEdges() {
		return edges;
	}

	// setEdges
	/**
	 * Overrides the already existent ArrayList with a new one.
	 * 
	 * @param edges
	 *            the ArrayList used to override
	 */
	public void setEdges(ArrayList<Line> edges) {
		this.edges = edges;
	}

	// printing all edges
	/**
	 * Prints out a String containing all edges from within the Face.
	 */
	public String toString() {
		String s = "[";
		// loop thrpugh all edges and add them to the existing String
		for (int i = 0; i < getEdges().size(); i++) {
			s = s.concat(getEdges().get(i).toString());
			if (i != getEdges().size() - 1) {
				s = s.concat(",");
			}
		}
		return s.concat("]");
	}

	// gets a list of points within the Face
	/**
	 * Function that returns all points a Face contains.
	 * 
	 * @return all points contained in a face
	 */
	public ArrayList<Vector> getPoints() {
		ArrayList<Vector> p = new ArrayList<>();

		// adding all points
		for (int i = 0; i < getEdges().size(); i++) {
			p.add(getEdges().get(i).getP1());
			p.add(getEdges().get(i).getP2());
		}

		// p.clone instead of p to avoid using pointers
		@SuppressWarnings("unchecked")
		ArrayList<Vector> pts = (ArrayList<Vector>) p.clone();

		// removing of duplicates
		for (Vector pt : pts) {
			if (p.indexOf(pt) != p.lastIndexOf(pt)) {
				p.remove(p.lastIndexOf(pt));
			}
		}

		return p;
	}

	// calculate the area of a face using all points
	/**
	 * Returns the area of a face using shoelace formula (Gausssche
	 * Trapezformel).
	 * 
	 * @return area of specific face
	 */
	public Double getArea() {
		ArrayList<Vector> points = getPoints();

		Double area = 0.0;

		// stop if the face has to few points (e.g.: <= 2)
		if (points.get(0) == null || points.size() < 3) {
			return 0.0;
		}

		// this is where the magic happens
		for (int i = 0; i < points.size(); i++) {
			area += (points.get(i).getY() + points.get((i + 1) % points.size()).getY())
					* (points.get(i).getX() - points.get((i + 1) % points.size()).getX());
		}

		return Math.abs(area / 2.0);
	}

	// create an identical copy of a Face
	/**
	 * Creates an identical copy of the Face without using a Pointer.
	 */
	public Face clone() {
		Face nf = new Face();

		// clone all the edges
		for (int i = 0; i < getEdges().size(); i++) {
			nf.getEdges().add(getEdges().get(i));
		}

		return nf;
	}

}