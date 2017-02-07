package graph;

public class Vector {
	
	private Point p1, p2;
	
	public Vector (Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Vector (Point p) {
		this.p1 = new Point(0,0);
		this.p2 = p;
	}
	
	public Vector (double dx, double dy) {
		this.p1 = new Point(0,0);
		this.p2 = new Point(dx,dy);
	}
	
	public void add(Vector v) {
		setP2(new Point(getDifferenceX() + v.getDifferenceX(), getDifferenceY() + v.getDifferenceY()));
	}
	
	public Vector multiply(double f) {
		return (new Vector(getP1(),(new Point(getP1().getX() + f * getDifferenceX(), getP1().getY() + f * getDifferenceY()))));
	}
	
	public double getDifferenceX() {
		return getP2().getX() - getP1().getX();
	}
	
	public double getDifferenceY() {
		return getP2().getY() - getP1().getY();
	}
	
	public static Vector add(Vector v1, Vector v2) {
		v1.add(v2);
		return v1;
	}
	
	public boolean isSimilar(Vector v) {
		if (getP1() == v.getP1() || getP1() == v.getP2() || getP2() == v.getP1() || getP2() == v.getP2()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean contains(Point p) {
		return (getP1() == p || getP2() == p ? true : false);
	}
	
	public Vector getComplementaryVector () {
		return new Vector(this.getP2(), this.getP1());
	}
	
	public boolean equals(Vector v) {
		boolean result = false;
		
		if (v.getP1() == getP1() && v.getP2() == getP2()) {
			result = true;
		}
		
		return result;
	}
	
	public double getLength(){
		return (Math.sqrt(Math.pow(getDifferenceX(),2)+Math.pow(getDifferenceY(), 2)));
	}
	
	public Vector changeLength(double len){
		return (this.multiply(len/this.getLength()));
	}
	/**
	 * 
	 * @param v2 
	 * Vector starting at end point of Vector which called the function
	 * @return
	 * angle between the two vector 
	 * positive value >=0 and <360
	 */
	public double angleTo(Vector v2){
		if (this.p2!=v2.p1){
			System.out.println("Error at angleto function: Condition not fulfilled");
			return 1337.0;
		}
		Vector v1 = this.getComplementaryVector();
		double angle = Math.atan2(v2.getDifferenceY(), v2.getDifferenceX())-Math.atan2(v1.getDifferenceY(), v1.getDifferenceX());
		if(angle<=0){
			angle=angle+2*Math.PI;
		}
		return angle;
	}
	
	public double angleToPV(Vector v2){ //--> Position Vector = Ortsvektor
		//Vector v1 = this.getComplementaryVector();
		Vector v1 = this;
		double angle = Math.atan2(v2.getDifferenceY(), v2.getDifferenceX())-Math.atan2(v1.getDifferenceY(), v1.getDifferenceX());
		if(angle<=0){
			angle=angle+2*Math.PI;
		}
		return angle;
	}
	

	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}
	
	public String toString() {
		return "[(" + getP1().getX() + "," + getP1().getY() + "),(" + getP2().getX() + "," + getP2().getY() + ")]";
	}

}