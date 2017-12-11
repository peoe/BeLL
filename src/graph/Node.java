package graph;

import java.util.ArrayList;

public class Node {

	// origin (coordinates) of Node
	private Vector origin;
	// incident Edge
	private Edge incidentEdge;

	/**
	 * Constructor of the Node class.
	 * 
	 * @param v
	 *            the Vector of the Nodes point
	 */
	public Node(Vector v) {
		origin = v;
		incidentEdge = null;
	}

	/**
	 * Checks if two Nodes are equal.
	 * 
	 * @param n
	 *            the Node to be compared
	 * @return true if both Nodes are equal
	 */
	public Boolean equals(Node n) {
		if (n.getOrigin().getX() == origin.getX() && n.getOrigin().getY() == origin.getY()) {
			return true;
		}

		return false;
	}

	// getters - setters
	/**
	 * Returns the origin of the Node.
	 * 
	 * @return Vector pointing to the origin
	 */
	public Vector getOrigin() {
		return origin;
	}

	/**
	 * Sets the origin of the Node.
	 * 
	 * @param origin
	 *            the Vector pointing to the origin
	 */
	public void setOrigin(Vector origin) {
		this.origin = origin;
	}

	/**
	 * Returns the incident Edge of the Node.
	 * 
	 * @return incident Edge
	 */
	public Edge getIncidentEdge() {
		return incidentEdge;
	}

	/**
	 * Sets the incident Edge of the Node.
	 * 
	 * @param incidentEdge
	 *            the Edge to be set as incident Edge
	 */
	public void setIncidentEdge(Edge incidentEdge) {
		this.incidentEdge = incidentEdge;
	}

	/**
	 * Returns all adjacent Edges pointing away from the Node.
	 * 
	 * @return ArrayList of adjacent Edges
	 */
	public ArrayList<Edge> getAdjacentEdges() {
		ArrayList<Edge> adjacentEdges = new ArrayList<>();

		Edge edge = getIncidentEdge();

		// add all adjacent edges
		do {
			adjacentEdges.add(edge);
			edge = edge.getTwin().getNext();
		} while (edge != getIncidentEdge());

		return adjacentEdges;
	}

	/**
	 * Returns a String of the Node.
	 * 
	 * @return String of the Node
	 */
	public String toString() {
		return "(" + origin.getX() + "," + origin.getY() + ")";
	}

}
