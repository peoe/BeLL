package rend.objects;

import rend.ScadObject;

import java.util.ArrayList;
import java.util.Locale;

import graph.*;

public class Polygon implements ScadObject {

	private ArrayList<Vector> Points;
	private ArrayList<Vector> Paths;
	private double height;
	
	final static String polygon = "polygon(%1s,%2s,10);\n";
	final static String linear_extrude = "linear_extrude(%1$.2f){\n%s}";
	
	public ArrayList<Vector> getPoints() {
		return Points;
	}

	public ArrayList<Vector> getPaths() {
		return Paths;
	}

	public void setPaths(ArrayList<Vector> paths) {
		Paths = paths;
	}

	public void setPoints(ArrayList<Vector> points) {
		Points = points;
	}
	

	@Override
	public String printCommand() {
		String s = String.format(Locale.UK, polygon, Points.toString(),Paths.toString());
		
		return (String.format(Locale.UK, linear_extrude, height,s));
	}

}
