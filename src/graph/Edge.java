package graph;

public class Edge {

	// define the next, prev and twin edge
	private Edge twin, prev, next;
	// defines both nodes of edge
	private Node n1, n2;
	// defines adjacent face of the adge
	private Face face;

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
	 * 
	 * @return generates twin of edge
	 */
	public Edge generateTwin() {
		return (new Edge(n2, n1));
	}
	
	/**
	 * 
	 * @return vector between both nodes of the edge
	 */
	public Vector toVector() {
		return new Vector(n1.getOrigin(), n2.getOrigin());
	}
	
	/**
	 * 
	 * @param e
	 * @return angle between this edge and e 
	 */
	public double angleToEdge(Edge e){
		return(this.toVector().angletoVector(e.toVector()));
	}

	// calculates the angle to another Line
	/**
	 * Returns the angle to another Line in an anticlockwise manner.
	 * 
	 * @param e
	 *            the line to calculate the angle to
	 * @return the angle to the given line anticlockwise
	 */
	public double angleTo(Edge e, Boolean Mode) {
		// checking if the second Line is starting at the ending point of the
		// first Line
		if (!this.n2.equals(e.n1)) {
			System.out.println("Error at angleto function: vec not starting at this.p2");
			return 1337.0;
		}

		// MAGIC, never touch a running system
		double angle = e.toVector().angle() - this.generateTwin().toVector().angle();
		if (Mode) {
			if (angle <= 0) {
				angle = angle + 2 * Math.PI;
			}
		} else {
			if (angle < 0) {
				angle = angle + 2 * Math.PI;
			}
		}

		return angle;
	}
	
	public Node getCommonNode(Edge e){
		if(this.getN1().equals(e.n1) || this.getN1().equals(e.n2)){
			return this.getN1();
		}
		if(this.getN2().equals(e.n1) || this.getN2().equals(e.n2)){
			return this.getN2();
		}
		return null;
	}
	//returns vector coordinates of n1 and n2
	public String toString() {
		return "[(" + n1.getOrigin().getX() + "," + n1.getOrigin().getY() + "),(" + n2.getOrigin().getX() + ","
				+ n2.getOrigin().getY() + /*"[prev: " + prev.getN1() + ", " + prev.getN2() + ", next: " + next.getN1() + ", " + next.getN2() + ")]"*/
				")]";
	}
	
	

}
