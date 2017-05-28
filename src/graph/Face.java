package graph;

import java.util.ArrayList;

public class Face {

	// incident edge of face
	private Edge incidentEdge;

	//getter and setter
	public Edge getIncidentEdge() {
		return incidentEdge;
	}

	public void setIncidentEdge(Edge incidentEdge) {
		this.incidentEdge = incidentEdge;
	}

	// constructor
	/**
	 * Creates a new instance of the Face class.
	 */
	public Face(Edge e) {
		incidentEdge = e;
	}

	// get - set
	// getEdges
	/**
	 * Returns the ArrayList containing all of the edges.
	 * 
	 * @return ArrayList of all edges
	 */
	public ArrayList<Edge> getEdges() {
		ArrayList<Edge> returnEdges = new ArrayList<>();
		Edge startingEdge = this.getIncidentEdge();
		Edge iterationEdge = startingEdge;
		do {
			returnEdges.add(iterationEdge);
			iterationEdge = iterationEdge.getNext();
		} while (startingEdge != iterationEdge);
		return returnEdges;

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
	/**
	 * Returns the area of a face using shoelace formula (Gausssche
	 * Trapezformel).
	 * 
	 * @return area of specific face
	 */
	public Double getArea() {

		Double area = 0.0;

		// stop if the face has to few points (e.g.: <= 2)
		// if (points.get(0) == null || points.size() < 3) {
		// return 0.0;
		// }

		// this is where the magic happens
		for (int i = 0; i < getEdges().size(); i++) {
			area += (getEdges().get(i).getN1().getOrigin().getY()
					+ getEdges().get(i).getNext().getN1().getOrigin().getY())
					* (getEdges().get(i).getN1().getOrigin().getX()
							- getEdges().get(i).getNext().getN1().getOrigin().getX());
		}

		return area / 2.0;
	}
	/**
	 * Returns all nodes of face
	 * @return all nodes of face
	 */
	public ArrayList<Node> getNodes() {
		ArrayList<Node> returnNodes = new ArrayList<>();
		ArrayList<Edge> faceEdges = getEdges();
		for (Edge e : faceEdges) {
			returnNodes.add(e.getN1());
		}
		return returnNodes;
	}
}
