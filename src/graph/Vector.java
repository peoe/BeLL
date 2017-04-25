package graph;

public class Vector {

	// x and y coordinates
	private double x, y;

	// constructors
	// using two doubles
	/**
	 * Creates a new Vector object using two doubles to describe the change of
	 * the x and the y coordinates.
	 * 
	 * @param x
	 *            the change of the x coordinate
	 * @param y
	 *            the change of the y coordinate
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// using two points
	/**
	 * Creates a new Vector object using two points to calculate the changes of
	 * the coordinates.
	 * 
	 * @param s
	 *            the starting point of the Vector
	 * @param e
	 *            the ending point of the Vector
	 */
	public Vector(Vector s, Vector e) {
		this.x = e.getX() - s.getX();
		this.y = e.getY() - s.getY();
	}

	// getter - setter
	// getting x value
	/**
	 * Returns the change of the x coordinate by the Vector.
	 * 
	 * @return the change of the x coordinate
	 */
	public double getX() {
		return x;
	}

	// getting y value
	/**
	 * Returns the change of the y coordinate by the Vector.
	 * 
	 * @return the change of the y coordinate
	 */
	public double getY() {
		return y;
	}

	// setting the location of the point
	/**
	 * Overrides the current position Vector of the point.
	 * 
	 * @param x
	 *            the new x coordinate
	 * @param y
	 *            the new y coordinate
	 */
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// printing the position vector
	/**
	 * Returns a String describing the Vector.
	 */
	public String toString() {
		return "(" + getX() + "," + getY() + ")";
	}

	/**
	 * Converts a vector to a line
	 * @return line  with P1 = (0|0) and P2 = Vector
	 */
	public Line toLine(){
		return(new Line(new Vector(0,0), this));
	}
  
	

	// changing the length of the Vector
	/**
	 * Changes the length of the Vector to the specified length.
	 * 
	 * @param len
	 *            the new length of the Vector
	 * @return the changed Vector
	 */
	public Vector changeLength(double len) {
		return (this.multiply(len / this.getLength()));
	}

	// calculating the length of the Vector
	/**
	 * Returns the cuurent length of the Vector.
	 * 
	 * @return the length of the Vector
	 */
	public double getLength() {
		return (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
	}

	// multipies a vector using scalar mulitplication
	/**
	 * Multiplies the vector using scalar mulitpilcation and a given factor.
	 * 
	 * @param f
	 *            the factor used for multiplication
	 * @return the multiplied Vector
	 */
	public Vector multiply(double f) {
		return (new Vector(f * x, f * y));
	}

	// adds another Vector to the old Vector
	/**
	 * Returns a new Vector describing the addition of the old Vector and the
	 * specified Vector.
	 * 
	 * @param v
	 *            the specified Vector
	 * @return the added Vector
	 */
	public Vector add(Vector v) {
		return (new Vector(x + v.getX(), y + v.getY()));
	}

	// calculates the angle of the Vector using atan2 in radian measure
	/**
	 * Calculates the angle of the Vector in radian measure using Math.atan2().
	 * 
	 * @return the angle of the Vector in radian measure
	 * @see Math
	 */
	public double angle() { // normal atan2
		double angle = Math.atan2(this.y, this.x);
		return angle;
	}

	// calculates the angle of the Vector using atan2 in degree measure
	/**
	 * Calculates the angle of the Vector in degree measure using Math.atan2().
	 * 
	 * @return the angle of the Vector in degree measure
	 * @see Math
	 */
	public double angleD() { // normal atan2
		double angle = Math.atan2(this.y, this.x);
		return Math.toDegrees(angle);
	}

	// calculates the angle to another Vector in radian measure
	/**
	 * Calculates the angle of the Vector to another Vector anticlockwise in
	 * radian measure.
	 * 
	 * @param v
	 *            the other Vector for calculating the angle in radian measure
	 * @return the angle of the old Vector to the given Vector anticlockwise
	 */
	public double angletoVector(Vector v) {
		double angle = v.angle() - this.angle();
		if (angle <= 0) {
			angle = angle + 2 * Math.PI;
		}
		return angle;
	}

	// calculates the angle to another Vector in degree measure
	/**
	 * Calculates the angle of the Vector to another Vector anticlockwise in
	 * degree measure.
	 * 
	 * @param v
	 *            the other Vector for calculating the angle in degree measure
	 * @return the angle of the old Vector to the given Vector anticlockwise
	 */
	public double angletoVectorD(Vector v2) {
		double angle = v2.angle() - this.angle();
		if (angle <= 0) {
			angle = angle + 2 * Math.PI;
		}
		return Math.toDegrees(angle);
	}

	// calculates the bisector of the angle
	/**
	 * Returns the bisector of the angle between two Vectors.
	 * 
	 * @param v
	 *            the other Vector
	 * @return the bisector of the angle
	 */
	public double bisectorOfAngleTo(Vector v) {
		return (this.angleD() + 0.5 * this.angletoVectorD(v));
	}

}