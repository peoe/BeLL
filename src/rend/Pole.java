package rend;

import java.util.ArrayList;

import graph.*;

public class Pole {
	

	
	
	private Point p;
	private ArrayList<Point> Corners;
	private double height;
	
	public Pole(Point P, ArrayList<Point> C,double H){
		p=P;
		Corners=C;
		height=H;
	}

	public Point getP() {
		return p;
	}

	public void setP(Point p) {
		this.p = p;
	}

	public ArrayList<Point> getCorners() {
		return Corners;
	}

	public void setCorners(ArrayList<Point> corners) {
		Corners = corners;
	}
	
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String renderPole(){
		
		final int radius = 3;
		
		String finalstring = "";
		finalstring=finalstring.concat("//Generated pole point " + getP());
		finalstring=finalstring.concat(String.format(addcyl, getHeight(),(double)radius));
		
		
		return finalstring;
	}
	
	

}
