package graph;

public class Vector {
	
	private double x, y;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector(Vector s, Vector e){
		this.x = e.getX()-s.getX();
		this.y = e.getY()-s.getY();
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString () {
		return "(" + getX() + "," + getY() + ")";
	}
	
	public Boolean equals (Vector p) {
		if (p.getX() == getX() && p.getY() == getY()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Vector changeLength(double len){
		return (this.multiply(len/this.getLength()));
	}
	
	public double getLength(){
		return (Math.sqrt(Math.pow(x,2)+Math.pow(y, 2)));
	}
	
	public Vector multiply(double f) {
		return (new Vector(f * x, f * y));
	}
	
	public Vector add(Vector v) {
		return(new Vector(x+v.getX(),y+v.getY()));
	}
	
	public double angle(){ //normal atan2
		double angle = Math.atan2(this.y, this.x);
		return angle;
	}
	
	public double angleD(){ //normal atan2
		double angle = Math.atan2(this.y, this.x);
		return Math.toDegrees(angle);
	}
	
	public double angletoVector(Vector v2){
		double angle = v2.angle()-this.angle();
		if(angle<=0){
			angle=angle+2*Math.PI;
		}
		return angle;
	}
	
	public double angletoVectorD(Vector v2){
		double angle = v2.angle()-this.angle();
		if(angle<=0){
			angle=angle+2*Math.PI;
		}
		return Math.toDegrees(angle);
	}
	
	public double bisectorOfAngleTo(Vector v2){
			return (this.angleD()+0.5*this.angletoVectorD(v2));
		
//		if (this.angle()>v2.angle()){
//			return (v2.angleD()+0.5*v2.angletoVectorD(this));
//		}
		
	}
	
	

	
	

}