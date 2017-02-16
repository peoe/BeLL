package rend.objects;

import rend.ScadObject;

import java.util.ArrayList;
import java.util.Locale;

import graph.*;

public class Polygon implements ScadObject {

<<<<<<< HEAD
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
=======
	private ArrayList<Vector> Points;
	private ArrayList<Vector> Paths;
	private double height;
	
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
>>>>>>> refs/remotes/origin/master
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
