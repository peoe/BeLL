package graph;

public class Edge {

	// twin, previous and next Edge of the Edge
	private Edge twin, prev, next;
	// first and second Node of the Edge
	private Node n1, n2;
	// adjacent Face of the Edge
	private Face face;

	/**
	 * Constructor of the Edge class.
	 * 
	 * @param node1
	 *            starting Node of the Edge
	 * @param node2
	 *            ending Node of the Edge
	 */
	public Edge(Node node1, Node node2) {
		n1 = node1;
		n2 = node2;
	}

	/**
	 * Returns the twin Edge of the given Edge.
	 * 
	 * @return twin of given Edge
	 */
	public Edge generateTwin() {
		return (new Edge(n2, n1));
	}

	/**
	 * Converts the given Edge to a Vector.
	 * 
	 * @return Vector of given Edge
	 */
	public Vector toVector() {
		return new Vector(n1.getOrigin(), n2.getOrigin());
	}

	/**
	 * Calculates the angle between this and a given Edge in anti-clockwise
	 * matter.
	 * 
	 * @param e
	 *            the second Edge
	 * @return angle between this Edge and e
	 */
	public double angleToEdge(Edge e) {
		return (this.toVector().angletoVector(e.toVector()));
	}

	/**
	 * Returns the Node that both Edges share.
	 * 
	 * @param e
	 *            another Edge
	 * @return common Node or null if there is no common Node
	 */
	public Node getCommonNode(Edge e) {
		if (this.getN1().equals(e.n1) || this.getN1().equals(e.n2)) {
			return this.getN1();
		}
		if (this.getN2().equals(e.n1) || this.getN2().equals(e.n2)) {
			return this.getN2();
		}
		return null;
	}

	// getters and setter
	/**
	 * Returns the twin Edge.
	 * 
	 * @return the twin Edge
	 */
	public Edge getTwin() {
		return twin;
	}

	/**
	 * Sets the twin Edge.
	 * 
	 * @param twin
	 *            the Edge to be set as twin Edge
	 */
	public void setTwin(Edge twin) {
		this.twin = twin;
	}

	/**
	 * Returns the previous Edge.
	 * 
	 * @return the previous Edge
	 */
	public Edge getPrev() {
		return prev;
	}

	/**
	 * Sets the previous Edge.
	 * 
	 * @param prev
	 *            the Edge to be set as previous Edge
	 */
	public void setPrev(Edge prev) {
		this.prev = prev;
	}

	/**
	 * Returns the next Edge.
	 * 
	 * @return the next Edge
	 */
	public Edge getNext() {
		return next;
	}

	/**
	 * Sets the next Edge.
	 * 
	 * @param next
	 *            the Edge to be set as next Edge
	 */
	public void setNext(Edge next) {
		this.next = next;
	}

	/**
	 * Returns the first Node of the Edge.
	 * 
	 * @return the first Node of the Edge
	 */
	public Node getN1() {
		return n1;
	}

	/**
	 * Sets the first Node of the Edge.
	 * 
	 * @param n1
	 *            the Node to be set as first Node
	 */
	public void setN1(Node n1) {
		this.n1 = n1;
	}

	/**
	 * Returns the second Node of the Edge.
	 * 
	 * @return second Node of the Edge
	 */
	public Node getN2() {
		return n2;
	}

	/**
	 * Sets the second Node of the Edge.
	 * 
	 * @param n2
	 *            the Node to be set as second Node
	 */
	public void setN2(Node n2) {
		this.n2 = n2;
	}

	/**
	 * Returns the corresponding Face of the Edge.
	 * 
	 * @return the corresponding Face of the Edge
	 */
	public Face getFace() {
		return face;
	}

	/**
	 * Sets the corresponding Face of the Edge.
	 * 
	 * @param face
	 *            the Face to be set as corresponding Face
	 */
	public void setFace(Face face) {
		this.face = face;
	}

	/**
	 * Returns a String of the Edge.
	 */
	public String toString() {
		return "[(" + n1.getOrigin().getX() + "," + n1.getOrigin().getY() + "),(" + n2.getOrigin().getX() + ","
				+ n2.getOrigin().getY() + ")]";
	}

}
