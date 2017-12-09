package graph;

public class Line {

	// first and second point of the Line
	private Vector p1, p2;

	// constructors
	/**
	 * Constructor of the Line class using two points.
	 * 
	 * @param p1 the Vector pointing to the first point
	 * @param p2 the Vector pointing to the second point
	 */
	public Line(Vector p1, Vector p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	/**
	 * Constructor of the Line class using a position Vector.
	 * @param p the Vector pointing to the second point
	 */
	public Line(Vector p) {
		this.p1 = new Vector(0, 0);
		this.p2 = p;
	}

	/**
	 * Constructor of Line class using x and y coordinate changes.
	 * @param dx
	 *            the change of the x coordinate
	 * @param dy
	 *            the change of the y coordinate
	 */
	public Line(double dx, double dy) {
		this.p1 = new Vector(0, 0);
		this.p2 = new Vector(dx, dy);
	}

	/**
	 * Checks if two Lines contain the same points.
	 * @param l the other Line
	 * @return true if both Lines are contain the same points
	 */
	public boolean isSimilar(Line l) {
		if (getP1() == l.getP1() || getP1() == l.getP2() || getP2() == l.getP1() || getP2() == l.getP2()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if a Line contains a certain point.
	 * 
	 * @param p the Vector pointing to the point
	 * @return true if the Line contains the point
	 */
	public boolean contains(Vector p) {
		return (getP1() == p || getP2() == p ? true : false);
	}

	/**
	 * Returns a Vector equal to the Line.
	 * @return Vector equal to the Line
	 */
	public Vector toVector() {
		return (new Vector(this.getDifferenceX(), this.getDifferenceY()));
	}

	// getters and setters
	/**
	 * Returns the complementary Line by switching around x and y coordinate change.
	 * @return the inverted Line
	 */
	public Line getComplementaryLine() {
		return new Line(this.getP2(), this.getP1());
	}

	/**
	 * Returns the Lines first point.
	 * @return Vector to the first point
	 */
	public Vector getP1() {
		return p1;
	}

	/**
	 * Sets the first point of the Line.
	 * @param p1 the Vector to be set as first point
	 */
	public void setP1(Vector p1) {
		this.p1 = p1;
	}

	/**
	 * Returns the Lines second point.
	 * @return Vector to the second point
	 */
	public Vector getP2() {
		return p2;
	}

	/**
	 * Sets the second point of the Line.
	 * @param p2 the Vector to be set as second point
	 */
	public void setP2(Vector p2) {
		this.p2 = p2;
	}

	/**
	 * Returns the difference of both points x coordinates.
	 * @return the difference of both x coordinates
	 */
	public double getDifferenceX() {
		return getP2().getX() - getP1().getX();
	}

	/**
	 * Returns the difference of both points y coordinates.
	 * @return the difference of both y coordinates
	 */
	public double getDifferenceY() {
		return getP2().getY() - getP1().getY();
	}

	/**
	 * Returns a String of the Line.
	 */
	public String toString() {
		return "[(" + getP1().getX() + "," + getP1().getY() + "),(" + getP2().getX() + "," + getP2().getY() + ")]";
	}

}