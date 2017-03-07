package rend.objects;

import java.util.Locale;

import rend.ScadObject;

public class Cube implements ScadObject{

	private double x, y, z;
	private boolean center;
	
	final static String cube = "cube([%1$.2f,%2$.2f,%3$.2f], %4$s);\n";
	
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

	public Cube(double X, double Y, double Z, boolean Center){
		this.x=X;
		this.y=Y;
		this.z=Z;
		this.center=Center;
	}
	
	public Cube(double X, double Y, double Z){
		this.x=X;
		this.y=Y;
		this.z=Z;
		this.center=false;
	}
	
	@Override
	public String printCommand() {
		return (String.format(Locale.UK, cube, x, y, z, center)); //Locale.UK ben�tigt f�r die Punktdarstellung
	}

	
	
}
