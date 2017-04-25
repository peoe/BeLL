package rend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.SynchronousQueue;

import graph.*;
import rend.objects.*;

public class Corner implements ScadObject {

	private Vector p;
	private ArrayList<Vector> Corners;
	private Face infFace;

	public Corner(Vector P, ArrayList<Vector> C, double H) {
		p = P;
		Corners = C;
	}

	public Corner(Vector P, Graph f) {
		p = P;
		Corners = new ArrayList<>();
		ArrayList<Line> vtemp = f.getEdgesPointingAway(P);
		System.out.println("\nvtemp:\n" + vtemp + "\n");
		for (int i = 0; i < vtemp.size(); i++) {
			Corners.add(vtemp.get(i).getP2());
			// System.out.println("\nCorner added:\n" + Corners.get(i) + "\n");
		}
		infFace = f.getInfiniteFace();
	}

	public Vector getP() {
		return p;
	}

	public void setP(Vector p) {
		this.p = p;
	}

	public ArrayList<Vector> getCorners() {
		return Corners;
	}

	public void setCorners(ArrayList<Vector> corners) {
		Corners = corners;
	}

	public Vector getClosestVector(Vector v) {
		System.out.println("Original point" + v);
		v = new Vector(p, v);
		System.out.println("Vector out of point" + v);
		System.out.println(Corners);

		// if (!Corners.contains(v)){
		// System.out.println("Corner not contained");
		// return null;
		// }
		ArrayList<Vector> vs = new ArrayList<>();

		for (Vector vecs : Corners) {
			if (!vecs.equals(v)) {
				vs.add(new Vector(p, vecs));
			}
		}

		ArrayList<Double> doubles = new ArrayList<>();

		for (Vector vec : vs) {
			// doubles.add(new Double(vs.get(i).angle(),5));
			doubles.add(v.angletoVector(vec));
		}
		Vector min = vs.get((doubles.indexOf(Collections.min(doubles))));

		return (min.add(p));

	}

	// public void generateMinusTile(){
	// Cube c1 = new Cube(20,10,1, true);
	// Cube c2 = new Cube(10,9.5,1, true);
	// Translate tc2 = new Translate(Cube c2 = new Cube(10,9.5,1, true), 10, 0,
	// 0)
	// ArrayList<ScadObject> temp = new ArrayList<>();
	// temp.add(c1);
	// temp.add(tc2);
	// MinusTile = new Difference(temp);
	// }
	//
	//
	// final Translate PinPositive = new Translate(new Union(new
	// ArrayList<>(Arrays.asList(new Cube(3,1.5,1, true),new Translate(), 5, 0,
	// 0)))), -3.4375, 0, 0);
	
	private static ScadObject getBaseTile(double height, boolean center){
		return(new Cylinder(height,ParameterController.getCornerRadius(), ParameterController.getCornerRadius(), center));
	}
	
	private static ScadObject getBaseTileLow(double l){
		return(new Cylinder(ParameterController.getBasePlatePinCircleHeight(), ParameterController.getCornerRadius()+l, ParameterController.getCornerRadius()+l, false));
	}

	private static double calculateD(double a) {
		return ((ParameterController.getPinDistance() + ParameterController.getPinNRadius()+ParameterController.getWallwidth()) / Math.sin(a / 2)
				- ParameterController.getPinPRadius()-ParameterController.getCornerRadius());
	}

	private static ScadObject getPinPositive(double a) {
		// Cylinder c1 = new Cylinder(1, ParameterController.getPinPRadius(),
		// ParameterController.getPinPRadius(), true);
		// Translate tc1 = new Translate(c1, )
		double d = calculateD(a);
		if (d < (2 * ParameterController.getPinPRadius() + ParameterController.getPinMinLength())) {
			d = 2 * ParameterController.getPinPRadius() + ParameterController.getPinMinLength();
		}
		Cube c1 = new Cube(d, ParameterController.getPinPWidth(), ParameterController.getBasePlateHeight()-ParameterController.getBasePlatePinCircleHeight(), true);
		Cylinder cyl1 = new Cylinder(ParameterController.getBasePlateHeight()-ParameterController.getBasePlatePinCircleHeight(), ParameterController.getPinPRadius(), ParameterController.getPinPRadius(), true);
		Translate tcyl1 = new Translate(cyl1, (0.5 * d + ParameterController.getPinPRadius()), 0, 0);

		Cube fill = new Cube(1, ParameterController.getPinPWidth(), ParameterController.getBasePlateHeight()-ParameterController.getBasePlatePinCircleHeight(), true);
		Translate tfill = new Translate(fill, d * 0.5, 0, 0);
		Union pinUnion = new Union(new ArrayList<>(Arrays.asList(c1, tcyl1, tfill)));
		Translate result = new Translate(pinUnion, -(ParameterController.getPinPRadius()), 0, 0.5*(ParameterController.getBasePlateHeight()-ParameterController.getBasePlatePinCircleHeight()));

		return result;
	}

	private static ScadObject getMinusTileCorner() {// r=10 w=3 e=0.25
		double r = ParameterController.getCornerRadius();
		double w = ParameterController.getWallwidth() / 2;
		double e = ParameterController.getE();
		Cube c1 = new Cube(r, w, 1, true);
		Cube c2 = new Cube(r, w - 2 * e, 1, true);
		Translate tc1 = new Translate(c1, r, 0, 0);
		Translate tc2 = new Translate(c2, r + e, 0, 0);
		ArrayList<ScadObject> temp = new ArrayList<>();
		temp.add(tc1);
		temp.add(tc2);

		return (new Difference(temp));
	}

	@Override
	public String printCommand() {
		// First part Wall fitting segment
		ArrayList<ScadObject> DifferenceAL = new ArrayList<>();
		Vector v;
		DifferenceAL.add(getBaseTile(1,true));
		
		for (int i = 0; i < Corners.size(); i++) {
			v = new Vector(p, Corners.get(i));
			// System.out.println("Vector to Line: " + v.toLine());
			 System.out.println(new Wall(v.toLine()).getPresentationWall().printCommand());
			DifferenceAL.add(new Wall(v.toLine()));
			DifferenceAL.add(new Rotate(getMinusTileCorner(), v.angleD(), 0, 0, 1));
			// System.out.println("\nangle to: " + Corners.get(i) + " is " +
			// v.angleD() + "\n" );
		}
		
		ScadObject finalDifference = new Translate(new Scale(new Difference(DifferenceAL), 1, 1, ParameterController.getHeight()-ParameterController.getBasePlateHeight()), 0, 0, 0.5*(ParameterController.getHeight()-ParameterController.getBasePlateHeight()));
		// Second part base plate fitting segment
		Vector v2;
		Translate ttemp;
		Rotate rttemp;
		ArrayList<ScadObject> UnionAL = new ArrayList<>();
		ArrayList<Double> maxPinLength = new ArrayList<>();
	
		for (Vector vec : Corners) {
			v2 = this.getClosestVector(vec);
			
			if(!(infFace.contains(vec) && infFace.contains(v2) && infFace.contains(p))){
			ttemp = new Translate(getPinPositive(vec.angletoVector(v2)), ParameterController.getCornerRadius()
					+ 0.5 * calculateD(vec.angletoVector(v2)) + ParameterController.getPinPRadius(), 0, 0);
			
			maxPinLength.add(ParameterController.getPinPRadius()*2 + calculateD(vec.angletoVector(v2)));
			rttemp = new Rotate(ttemp, vec.bisectorOfAngleTo(v2), 0, 0, 1);
			UnionAL.add(rttemp);
			}
		}
		
		UnionAL.add(getBaseTile(ParameterController.getBasePlateHeight(),false));
		UnionAL.add(getBaseTileLow(Collections.max(maxPinLength)));

		Union Unionbottom = new Union(UnionAL);
		System.out.println(Unionbottom.printCommand());
		//Translate sd = new Translate(new Scale(finalDifference, 1, 1,ParameterController.getHeight()-ParameterController.getBasePlateHeight()),0,0,ParameterController.getHeight()/2);

		Union finalUnion = new Union(new ArrayList<ScadObject>(Arrays.asList(Unionbottom, finalDifference)));
		Translate result = new Translate(finalUnion, 0, 0, 0);

		return result.printCommand();
		// return null;
	}

}
