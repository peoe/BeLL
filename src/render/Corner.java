package render;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.SynchronousQueue;

import graph.*;
import render.objects.*;

public class Corner implements ScadObject {

	private Node n;

	public Corner(Node N) {
		n = N;
	}

	/*public Corner(Vector P, Graph f) {
		n = P;
		Corners = new ArrayList<>();
		ArrayList<Line> vtemp = f.getEdgesPointingAway(P);
		System.out.println("\nvtemp:\n" + vtemp + "\n");
		for (int i = 0; i < vtemp.size(); i++) {
			Corners.add(vtemp.get(i).getP2());
			// System.out.println("\nCorner added:\n" + Corners.get(i) + "\n");
		}
		infFace = f.getInfiniteFace();
	}*/

	/*public ArrayList<Vector> getCorners() {
		return Corners;
	}

	public void setCorners(ArrayList<Vector> corners) {
		Corners = corners;
	} */

	public Vector getClosestVector(Vector v) {
		System.out.println("Original point" + v);
		v = new Vector(n, v);
		System.out.println("Vector out of point" + v);
		System.out.println(Corners);

		// if (!Corners.contains(v)){
		// System.out.println("Corner not contained");
		// return null;
		// }
		ArrayList<Vector> vs = new ArrayList<>();

		for (Vector vecs : Corners) {
			if (!vecs.equals(v)) {
				vs.add(new Vector(n, vecs));
			}
		}

		ArrayList<Double> doubles = new ArrayList<>();

		for (Vector vec : vs) {
			// doubles.add(new Double(vs.get(i).angle(),5));
			doubles.add(v.angletoVector(vec));
		}
		Vector min = vs.get((doubles.indexOf(Collections.min(doubles))));

		return (min.add(n));

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
		double h = l;
		return(new Cylinder(ParameterController.getBasePlatePinCircleHeight(), h, h, false));
	}

	private static double calculateD(double a) {
		return (((ParameterController.getPinDistance() + ParameterController.getPinNRadius()+0.5*ParameterController.getWallwidth()) / Math.sin(a / 2))
				- ParameterController.getPinPRadius() + ParameterController.getCornerRadius());
	}

	private static ScadObject getPinPositive(double a) {
		// Cylinder c1 = new Cylinder(1, ParameterController.getPinPRadius(),
		// ParameterController.getPinPRadius(), true);
		// Translate tc1 = new Translate(c1, )
		double d = calculateD(a);
		double min = ParameterController.getPinMinLength() + ParameterController.getCornerRadius();
		if (d < min) {
			d = min;
		}
		Cube baseCube = new Cube(d, ParameterController.getPinPWidth(), ParameterController.getBasePlateHeight()-ParameterController.getBasePlatePinCircleHeight(), true);
		Cylinder pinCylinder = new Cylinder(ParameterController.getBasePlateHeight()-ParameterController.getBasePlatePinCircleHeight(), ParameterController.getPinPRadius(), ParameterController.getPinPRadius(), true);
		Translate tPinCylinder = new Translate(pinCylinder, (0.5 * d + ParameterController.getPinPRadius()), 0, 0);

		Cube fillCube = new Cube(1, ParameterController.getPinPWidth(), ParameterController.getBasePlateHeight()-ParameterController.getBasePlatePinCircleHeight(), true);
		Translate tFillCube = new Translate(fillCube, d * 0.5, 0, 0);
		Union pinUnion = new Union(new ArrayList<>(Arrays.asList(baseCube, tPinCylinder, tFillCube)));
		Translate result = new Translate(pinUnion, 0.5*d, 0, 0.5*(ParameterController.getBasePlateHeight()+ParameterController.getBasePlatePinCircleHeight()));
		
		return result;
	}

	private static ScadObject getMinusTileCorner(double angle) {// r=10 w=3 e=0.25
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

		return (new Rotate(new Difference(temp),angle, 0, 0, 1));
	}

	@Override
	public String printCommand() {
		// First part Wall fitting segment
		ArrayList<ScadObject> cornerBaseDifference = new ArrayList<>();
		cornerBaseDifference.add(getBaseTile(1,true));
		ArrayList<Edge> cornerEdges = n.getAdjacentEdges();
		for (Edge cornerEdge : cornerEdges){ 
			// System.out.println("Vector to Line: " + v.toLine());
//			 System.out.println("//Wall of " + cornerEdge + "\n" +(new Wall(cornerEdge)).printCommand());
			cornerBaseDifference.add(new Wall(cornerEdge));
			cornerBaseDifference.add(getMinusTileCorner(cornerEdge.toVector().angleInDegrees()));
			// System.out.println("\nangle to: " + Corners.get(i) + " is " +
			// v.angleD() + "\n" );
		}
		
		ScadObject finalDifference = new Translate(new Scale(new Difference(cornerBaseDifference), 1, 1, ParameterController.getHeight()-ParameterController.getBasePlateHeight()), 0, 0, 0.5*(ParameterController.getHeight()-ParameterController.getBasePlateHeight()));

		// Second part base plate fitting segment
		ScadObject ttemp;
		Rotate rttemp;
		ArrayList<ScadObject> unionArrayList = new ArrayList<>();
		ArrayList<Double> maxPinLength = new ArrayList<>();
	
		for (Edge cornerEdge : cornerEdges) {
			//v2 = this.getClosestVector(vec);
			Edge prevEdge = cornerEdge.getPrev().getTwin();
			if(!(cornerEdge.getFace().getArea()<0)){
			double edgeAngel = cornerEdge.angleToEdge(prevEdge);
			ttemp = getPinPositive(edgeAngel);
			System.out.println(edgeAngel);
			System.out.println("TILE length should be:  " + calculateD(edgeAngel) + "\n" + ttemp.printCommand()  );
			
			maxPinLength.add(2*ParameterController.getPinPRadius()+ calculateD(edgeAngel));
			rttemp = new Rotate(ttemp, cornerEdge.toVector().bisectorOfAngleTo(prevEdge.toVector()), 0, 0, 1);
			unionArrayList.add(rttemp);
			}
		}
		
		unionArrayList.add(getBaseTile(ParameterController.getBasePlateHeight(),false));
		unionArrayList.add(getBaseTileLow(Collections.max(maxPinLength)));

		Union Unionbottom = new Union(unionArrayList);
		System.out.println(Unionbottom.printCommand());
		//Translate sd = new Translate(new Scale(finalDifference, 1, 1,ParameterController.getHeight()-ParameterController.getBasePlateHeight()),0,0,ParameterController.getHeight()/2);

		Union finalUnion = new Union(new ArrayList<ScadObject>(Arrays.asList(Unionbottom, finalDifference)));
		Translate result = new Translate(finalUnion, 0, 0, 0);

		return result.printCommand();
		// return null; 
	}

}
