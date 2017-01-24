package rend.objects;

import rend.ScadObject;

import java.util.ArrayList;
import java.util.Locale;

import graph.*;

public class Polygon implements ScadObject {

	private ArrayList<Point> Points;
	private ArrayList<Point> Paths;
	private double height;
	
	public ArrayList<Point> getPoints() {
		return Points;
	}

	public ArrayList<Point> getPaths() {
		return Paths;
	}

	public void setPaths(ArrayList<Point> paths) {
		Paths = paths;
	}

	public void setPoints(ArrayList<Point> points) {
		Points = points;
	}
	

	@Override
	public String printcommand() {
		String s = String.format(Locale.UK, polygon, Points.toString(),Paths.toString());
		
		return (String.format(Locale.UK, linear_extrude, height,s));
	}

}
