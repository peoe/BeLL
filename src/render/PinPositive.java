package render;

import java.util.ArrayList;
import java.util.Arrays;

import graph.*;
import render.objects.*;
import render.*;

public class PinPositive implements ScadObject {

	private Edge cornerEdge;

	public Edge getCornerEdge() {
		return cornerEdge;
	}

	public void setCornerEdge(Edge cornerEdge) {
		this.cornerEdge = cornerEdge;
	}

	public PinPositive(Edge e) {
		cornerEdge = e;

	}

	private static ScadObject getBaseTileLow(double l, boolean Mode) {
		double r = l + Params.getPinPRadius();
		ScadObject baseTile;
		if(Mode){
			baseTile = new Cylinder(Params.getBasePlatePinCircleHeight(), r, true);
		} else {
			
			baseTile = new Cylinder(Params.getBasePlatePinCircleHeight(), r, true).resize(Params.getE(), Params.getE(), Params.getE());	
		}
		return (new Translate(baseTile, 0, 0, 0.5*Params.getBasePlatePinCircleHeight()));
	}

	private static double calculateD(double a) {
		double min = Params.getPinMinLength() + Params.getCornerRadius();
		double d = ((Params.getPinDistance() + Params.getPinNRadius()
				+ 0.5 * Params.getWallwidth()) / Math.sin(a / 2)) - Params.getPinPRadius()
				+ Params.getCornerRadius();
		if (d < min) {
			d = min;
		}
		return (d);
	}

	private static ScadObject getPinPositive(double a, boolean Mode) {
		// calculate necessary distance for a 4mm gap between wall and Pin
		double d = calculateD(a);
		double totalHeight = (Params.getBasePlateHeight()
				- Params.getBasePlatePinCircleHeight()) / 2
				+ 0.5 * Params.getBasePlatePinCircleHeight();

		ScadObject baseCube, fillCube;
		ScadObject pinCylinder;
		if (Mode) {
			baseCube = new Cube(d, Params.getPinPWidth(), totalHeight, true);
			pinCylinder = new Cylinder(totalHeight, Params.getPinPRadius(), true);
			fillCube = new Cube(1, Params.getPinPWidth(), totalHeight, true);
		} else {
			baseCube = (new Cube(d, Params.getPinPWidth(), totalHeight, true)).resize(0.0, 0.0,
					Params.getE(), Params.getE(), 0.0, Params.getE());
			pinCylinder =  new Cylinder(totalHeight, Params.getPinPRadius(), true).resize(Params.getE(), 0.0,
							Params.getE());
			fillCube = new Cube(1, Params.getPinPWidth(), totalHeight, true).resize(0.0, 0.0,
					Params.getE(), Params.getE(), 0.0, Params.getE());
		}
		Translate tPinCylinder = new Translate(pinCylinder, (0.5 * d + Params.getPinPRadius()), 0, 0);

		Translate tFillCube = new Translate(fillCube, d * 0.5, 0, 0);
		Union pinUnion = new Union(new ArrayList<>(Arrays.asList(baseCube, tPinCylinder, tFillCube)));
		Translate result = new Translate(pinUnion, 0.5 * d, 0,
				0.5 * totalHeight + 0.5 * Params.getBasePlatePinCircleHeight());

		return result;
	}

	public ScadObject getPinObject(boolean Mode) {
		ArrayList<ScadObject> unionArrayList = new ArrayList<>();
		double edgeAngle, pinLength;
		ArrayList<ScadObject> pinIntersection = new ArrayList<>();
		Edge prevEdge = cornerEdge.getPrev().getTwin();
		double faceArea = cornerEdge.getFace().getArea();

		if (faceArea > 0) {
			edgeAngle = cornerEdge.angleToEdge(prevEdge);
			ScadObject pinPositive = getPinPositive(edgeAngle, Mode);
			pinLength = calculateD(edgeAngle);

			unionArrayList.add(
					new Rotate(pinPositive, cornerEdge.toVector().bisectorOfAngleTo(prevEdge.toVector()), 0, 0, 1));

		} else {
			pinLength = Params.getPinMinLength();
		}
		pinLength += 2 * Params.getPinPRadius();
		pinIntersection.add(getBaseTileLow(pinLength,Mode));
		pinIntersection
				.add(new Translate(new Scale(new Polygon(cornerEdge), 1, 1, 100),
						cornerEdge.getN1().getOrigin().multiply(-1), 0));
		if (faceArea > 0) {
			unionArrayList.add(new Intersection(pinIntersection));
		} else {
			unionArrayList.add(new Difference(pinIntersection));
		}
		return (new Union(unionArrayList));
	}
	

	@Override
	public String toString() {
		return getPinObject(true).toString();
	}

}
