package rend.objects;

import java.util.Locale;

import rend.ScadObject;

public class Cube implements ScadObject{

	private double x, y, z;
	
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

	public Cube(double X, double Y, double Z){
		this.x=X;
		this.y=Y;
		this.z=Z;
	}
	@Override
	public String printcommand() {
		return (String.format(Locale.UK, cube, x, y, z)); //Locale.UK benötigt für die Punktdarstellung
	}
	
}