package rend.objects;

import java.util.Locale;

import rend.ScadObject;

public class Cylinder implements ScadObject{
	
	private double height, bottomRadius, topRadius;
	private boolean center;
	
	public Cylinder(double height, double bottomRadius, double topRadius) {
		super();
		this.height = height;
		this.bottomRadius = bottomRadius;
		this.topRadius = topRadius;
		this.center = false;
	}
	
	public Cylinder(double height, double bottomRadius, double topRadius, boolean Center) {
		super();
		this.height = height;
		this.bottomRadius = bottomRadius;
		this.topRadius = topRadius;
		this.center=Center;
	}
	
	public double getBottomRadius() {
		return bottomRadius;
	}

	public void setBottomRadius(double bottomRadius) {
		this.bottomRadius = bottomRadius;
	}

	public double getTopRadius() {
		return topRadius;
	}

	public void setTopRadius(double topRadius) {
		this.topRadius = topRadius;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public Cylinder multiply(int a){
		return (new Cylinder(height*a, bottomRadius*a, topRadius*a, center));
	}
	
	public String Cylinderprintcommand(){
		return (String.format(Locale.UK, cylinder, height, bottomRadius, topRadius, center));
	}

	@Override
	public String printcommand() {
		int a = 100;
		double a2 = 1.0/a;
		String s = String.format(Locale.UK, scale, a2, a2, a2, this.multiply(a).Cylinderprintcommand());
		return s;
	}
	
}
