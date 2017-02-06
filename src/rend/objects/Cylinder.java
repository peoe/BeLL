package rend.objects;

import java.util.Locale;

import rend.ScadObject;

public class Cylinder implements ScadObject{
	
	private double height, bottomRadius, topRadius;
	
	public Cylinder(double height, double innerRadius, double outerRadius) {
		super();
		this.height = height;
		this.bottomRadius = innerRadius;
		this.topRadius = outerRadius;
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

	@Override
	public String printcommand() {
		return (String.format(Locale.UK, cylinder, height, bottomRadius, topRadius));
	}
	
}
