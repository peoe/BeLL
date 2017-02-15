package rend.objects;

import rend.ScadObject;

import java.util.ArrayList;
import java.util.Locale;

import graph.*;

public class Polygon implements ScadObject {

	private ArrayList<Point> Points;
	private double height;
	
	public Polygon(Face f){
		Points = f.getPoints();
		height = 1;
		
	}
	
	public ArrayList<Point> getPoints() {
		return Points;
	}

	public void setPoints(ArrayList<Point> points) {
		Points = points;
	}
	

	@Override
	public String printCommand() {
		//transform paths to indices
		ArrayList<Integer> paths = new ArrayList<>();
		for (int i=0;i<Points.size();i++){
			paths.add(i);
		}
		
		
		
		
		String s = String.format(Locale.UK, polygon, Points.toString());
		
		return (String.format(Locale.UK, linear_extrude, height,s));
	}

}
