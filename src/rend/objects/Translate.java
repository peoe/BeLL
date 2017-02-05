package rend.objects;

import rend.ScadObject;

public class Translate implements ScadObject{
	
	private String object;
	private double x,y,z;
	
	 

	public Translate(String object, double x, double y, double z) {
		super();
		this.object = object;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	@Override
	public String printcommand() {
		
		return null;
	}
	
	

}
