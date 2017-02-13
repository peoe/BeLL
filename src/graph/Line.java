package graph;

public class Line {

	private Vector p1, p2;

	public Line(Vector p1, Vector p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public Line (Vector p) {
		this.p1 = new Vector(0,0);
		this.p2 = p;
	}
	
	public Line (double dx, double dy) {
		this.p1 = new Vector(0,0);
		this.p2 = new Vector(dx,dy);
	}
	
	public void add(Line v) {
		setP2(new Vector(getDifferenceX() + v.getDifferenceX(), getDifferenceY() + v.getDifferenceY()));
	}
	
	public Line multiply(double f) {
		return (new Line(getP1(),(new Vector(getP1().getX() + f * getDifferenceX(), getP1().getY() + f * getDifferenceY()))));
	}

	public double getDifferenceX() {
		return getP2().getX() - getP1().getX();
	}

	public double getDifferenceY() {
		return getP2().getY() - getP1().getY();
	}

	public static Line add(Line v1, Line v2) {
		v1.add(v2);
		return v1;
	}

	public boolean isSimilar(Line v) {
		if (getP1() == v.getP1() || getP1() == v.getP2() || getP2() == v.getP1() || getP2() == v.getP2()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean contains(Vector p) {
		return (getP1() == p || getP2() == p ? true : false);
	}

	public Line getComplementaryVector() {
		return new Line(this.getP2(), this.getP1());
	}

	public boolean equals(Line v) {
		boolean result = false;

		if (v.getP1() == getP1() && v.getP2() == getP2()) {
			result = true;
		}

		return result;
	}
	
	public double getLength(){
		return (Math.sqrt(Math.pow(getDifferenceX(),2)+Math.pow(getDifferenceY(), 2)));
	}
	
	public Line changeLength(double len){
		return (this.multiply(len/this.getLength()));
	}
	
	/**
	 * 
	 * @param v2
	 *            Vector starting at end point of Vector which called the
	 *            function
	 * @return angle between the two vector positive value >=0 and <360
	 */
	public double angleTo(Line v2) {		
		if (!this.p2.equals(v2.p1)) {
			System.out.println("Error at angleto function: vec not starting at this.p2");
			return 1337.0;
		}

		Line v1 = this.getComplementaryVector();
		double angle = Math.atan2(v2.getDifferenceY(), v2.getDifferenceX())
				- Math.atan2(v1.getDifferenceY(), v1.getDifferenceX());

		if (angle <= 0) {
			angle = angle + 2 * Math.PI;
		}
		
		return angle;
	}
	
	public double angle(){ //--> Position Vector = Ortsvektor
		double angle = Math.atan2(this.getDifferenceY(), this.getDifferenceX());
		return Math.toDegrees(angle);
	}
  
	public double angletoPV(Line v2){
		Line v1 = this;
		double angle = Math.atan2(v2.getDifferenceY(), v2.getDifferenceX())-Math.atan2(v1.getDifferenceY(), v1.getDifferenceX());
		if(angle<=0){
			angle=angle+2*Math.PI;
		}
		return Math.toDegrees(angle);
	}

	public Vector getP1() {
		return p1;
	}

	public void setP1(Vector p1) {
		this.p1 = p1;
	}

	public Vector getP2() {
		return p2;
	}

	public void setP2(Vector p2) {
		this.p2 = p2;
	}

	public String toString() {
		return "[(" + getP1().getX() + "," + getP1().getY() + "),(" + getP2().getX() + "," + getP2().getY() + ")]";
	}

}