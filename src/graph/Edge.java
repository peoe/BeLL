package graph;

public class Edge {

	// define the twin, prev and next edge
	private Edge twin, prev, next;
	// define both nodes of edge
	private Node n1, n2;
	// define adjacent face of the adge
	private Face face;
	
	/**
	 * Constructor of the Edge class.
	 * @param node1 starting node of an edge
	 * @param node2 ending node of an edge
	 */
	public Edge(Node node1, Node node2) {
		n1 = node1;
		n2 = node2;
	}
	
	//getters and setter
	public Edge getTwin() {
		return twin;
	}

	public void setTwin(Edge twin) {
		this.twin = twin;
	}

	public Edge getPrev() {
		return prev;
	}

	public void setPrev(Edge prev) {
		this.prev = prev;
	}

	public Edge getNext() {
		return next;
	}

	public void setNext(Edge next) {
		this.next = next;
	}

	public Node getN1() {
		return n1;
	}

	public void setN1(Node n1) {
		this.n1 = n1;
	}

	public Node getN2() {
		return n2;
	}

	public void setN2(Node n2) {
		this.n2 = n2;
	}

	public Face getFace() {
		return face;
	}

	public void setFace(Face face) {
		this.face = face;
	}
	
	/**
	 * Returns the resulting twin edge of the given edge.
	 * @return twin of given edge
	 */
	public Edge generateTwin() {
		return (new Edge(n2, n1));
	}
	
	/**
	 * Converts the given edge to a vector.
	 * @return vector of given edge
	 */
	public Vector toVector() {
		return new Vector(n1.getOrigin(), n2.getOrigin());
	}
	
	/**
	 * Calculates the angle between this and a given edge in anti-clockwise matter.
	 * @param e second edge which creates the angle between this edge and e
	 * @return angle between this edge and e 
	 */
	public double angleToEdge(Edge e){
		return(this.toVector().angletoVector(e.toVector()));
	}

	/**
	 * Returns the node that both edges share.
	 * @param e a different edge
	 * @return common node or null if there is no common node
	 */
	public Node getCommonNode(Edge e){
		if(this.getN1().equals(e.n1) || this.getN1().equals(e.n2)){
			return this.getN1();
		}
		if(this.getN2().equals(e.n1) || this.getN2().equals(e.n2)){
			return this.getN2();
		}
		return null;
	}
	
	/**
	 * Returns a string of the edge.
	 */
	public String toString() {
		return "[(" + n1.getOrigin().getX() + "," + n1.getOrigin().getY() + "),(" + n2.getOrigin().getX() + ","
				+ n2.getOrigin().getY() + ")]";
	}

}
