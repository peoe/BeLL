package graph;

public class Line {

	// the two position vectors used to describe the line
	private Vector p1, p2;

	// Constructors
	// using two position vectors
	/**
	 * Creates a new Line object using two position vectors of the starting
	 * point P1 and the ending point P2.
	 * 
	 * @param p1
	 *            the starting point of the Line
	 * @param p2
	 *            the ending point of the Line
	 */
	public Line(Vector p1, Vector p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	// using only one position vector
	/**
	 * Creates a new Line object using only the ending point. The starting point
	 * will be assumed to be in (0, 0).
	 * 
	 * @param p
	 *            the ending point of the Line
	 */
	public Line(Vector p) {
		this.p1 = new Vector(0, 0);
		this.p2 = p;
	}

	// using differences in x and y coordinates
	/**
	 * Creates a new Line object by taking the changes of the x and the y
	 * coordinates to calculate a new ending point. The starting point will be
	 * assumed to be in (0, 0).
	 * 
	 * @param dx
	 *            the change of the x coordinate
	 * @param dy
	 *            the change of the y coordinate
	 */
	public Line(double dx, double dy) {
		this.p1 = new Vector(0, 0);
		this.p2 = new Vector(dx, dy);
	}

	// calculating the change of the x coordinate
	/**
	 * Returns the difference of the first points x coordinate to the second
	 * points x coordinate.
	 * 
	 * @return the difference of the x coordinates from both points
	 */
	public double getDifferenceX() {
		return getP2().getX() - getP1().getX();
	}

	// calculating the change of the y coordinate
	/**
	 * Returns the difference of the first points y coordinate to the second
	 * points y coordinate.
	 * 
	 * @return the difference of the y coordinates from both points
	 */
	public double getDifferenceY() {
		return getP2().getY() - getP1().getY();
	}

	// checks if two lines contain the same points (not necessarily in the same
	// order)
	/**
	 * Checks if two Lines conatin the same points. The order of the points is
	 * neglected.
	 * 
	 * @param l
	 *            the second line to compare to the first
	 * @return a boolean stating if the two lines are similar
	 */
	public boolean isSimilar(Line l) {
		if (getP1() == l.getP1() || getP1() == l.getP2() || getP2() == l.getP1() || getP2() == l.getP2()) {
			return true;
		} else {
			return false;
		}
	}

	// checks if a line contains a certain point
	/**
	 * Checks if a Line contains a certain point.
	 * 
	 * @param p
	 *            the point to be checked
	 * @return a boolean stating if the Line contains the point
	 */
	public boolean contains(Vector p) {
		return (getP1() == p || getP2() == p ? true : false);
	}

	// generates the inverted Line
	/**
	 * Generates a Line where the starting and the ending point have been
	 * switched around.
	 * 
	 * @return a inverted Line
	 */
	public Line getComplementaryLine() {
		return new Line(this.getP2(), this.getP1());
	}

	// returns the first point of the Line
	/**
	 * Returns the position vector of the Lines starting point.
	 * 
	 * @return the starting point of the Line
	 */
	public Vector getP1() {
		return p1;
	}

	// overrides the starting point of the Line
	/**
	 * Overrides the starting point of the Line.
	 * 
	 * @param p1
	 *            the new starting point
	 */
	public void setP1(Vector p1) {
		this.p1 = p1;
	}

	// returns the second point of the Line
	/**
	 * Returns the position vector of the Lines ending point.
	 * 
	 * @return the ending point of the line
	 */
	public Vector getP2() {
		return p2;
	}

	// overrides the edning point of the Line
	/**
	 * Overrides the ending point of the Line.
	 * 
	 * @param p2
	 *            the new ending point
	 */
	public void setP2(Vector p2) {
		this.p2 = p2;
	}

	// printing the Line
	/**
	 * Returns a String displaying the Line.
	 */
	public String toString() {
		return "[(" + getP1().getX() + "," + getP1().getY() + "),(" + getP2().getX() + "," + getP2().getY() + ")]";
	}

	// creates a vector based on the differences of the coordinates
	/**
	 * Returns a vector based upon the Line. The vector points from the starting
	 * point of the Line to the ending point.
	 * 
	 * @return a vector based upon the Line
	 */
	public Vector toVector() {
		return (new Vector(this.getDifferenceX(), this.getDifferenceY()));
	}

}