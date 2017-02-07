package rend;

import java.util.ArrayList;
import java.util.Arrays;

import graph.*;
import rend.objects.*;

public class Corner implements ScadObject{
	
	private Point p;
	private ArrayList<Point> Corners;
	private double height;
	private Difference MinusTile;
	
	
	
	public Corner(Point P, ArrayList<Point> C, double H){
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

//	public void generateMinusTile(){
//		Cube c1 = new Cube(20,10,1, true);
//		Cube c2 = new Cube(10,9.5,1, true);
//		Translate tc2 = new Translate(Cube c2 = new Cube(10,9.5,1, true), 10, 0, 0)
//		ArrayList<ScadObject> temp = new ArrayList<>();
//		temp.add(c1);
//		temp.add(tc2);
//		MinusTile = new Difference(temp);
//	}
//	
//	

	@Override
	public String printcommand() {
		Wall w;
		Translate t;
		Rotate r;
		ArrayList<ScadObject> DifferenceAL = new ArrayList<>();
		Vector dummy = new Vector(1,0);
		DifferenceAL.add(Base);
		for (int i=0;i<Corners.size();i++){
			DifferenceAL.add(new Wall(new Vector(p, Corners.get(i))));
			DifferenceAL.add(new Rotate(MinusTileCorner,dummy.angleToPV(new Vector(p, Corners.get(i))), 0, 0, 1));
		}
		Difference finalDifference = new Difference(DifferenceAL);
		
		return finalDifference.printcommand();
	}
	
	

}
