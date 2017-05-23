package graph;

import java.util.ArrayList;

public class Node {
	
	//origin(coordinates) of Node
	private Vector origin;
	//incident Edge
	private Edge incidentEdge;

	//constructor out of Vector (incidentEdge will be set after)
	/**
	 * Constructor of Node class
	 * @param v Vector of which the node gets created
	 */
	public Node(Vector v) {
		origin = v;
		incidentEdge = null;
	}

	public Vector getOrigin() {
		return origin;
	}

	public void setOrigin(Vector origin) {
		this.origin = origin;
	}

	public Edge getIncidentEdge() {
		return incidentEdge;
	}

	public void setIncidentEdge(Edge incidentEdge) {
		this.incidentEdge = incidentEdge;
	}
	
	/**
	 * @return String Presentation of Node [X, Y]
	 */
	public String toString() {
		return "(" + origin.getX() + "," + origin.getY() + ")";
	}
	/**
	 * calculates all adjacent outgoing Edges of the Node
	 * @return ArrayList<Edge>
	 */
	public ArrayList<Edge> getAdjacentEdges(){
		ArrayList<Edge> adjacentEdges = new ArrayList<>();
		Edge e = incidentEdge;
		Edge edge = e;
		do{
			adjacentEdges.add(edge);
			edge = edge.getTwin().getNext();
			
		} while( edge != incidentEdge);
		return adjacentEdges;
	}
	
	// checking if two nodes are equal
	/**
	 * Checks if two nodes are equal.
	 * 
	 * @param n
	 *            the node to be compared
	 * @return a boolean stating if the two nodes are equal
	 */
	public Boolean equals(Node n) {
		// just simple comparison
		if (n.getOrigin().getX() == origin.getX() && n.getOrigin().getY() == origin.getY()) 
		{
			return true;
		}
		return false;
	}

}
