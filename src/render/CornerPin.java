package render;

import java.util.ArrayList;
import java.util.Arrays;

import graph.*;
import render.objects.*;

public class CornerPin implements ScadObject {

	// incident Edge with defines the pin completely
	private Edge cornerEdge;
	// distance between a positive and a negative pin = EPSILON
	private double epsilon;

	// Getter und Setter
	/**
	 * 
	 * @return epsilon value of pin
	 */
	public double getEpsilon() {
		return epsilon;
	}

	/**
	 * 
	 * @param epsilon
	 *            sets the epsilon value of this pin
	 */
	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

	public Edge getCornerEdge() {
		return cornerEdge;
	}

	public void setCornerEdge(Edge cornerEdge) {
		this.cornerEdge = cornerEdge;
	}

	// constructor
	public CornerPin(Edge e, double epsilon) {
		cornerEdge = e;
		this.epsilon = epsilon;

	}

	// calculates the angle to the next Edge anti-clockwise
	private double calculateAngle() {
		return cornerEdge.angleToEdge(cornerEdge.getPrev().getTwin());
	}

	// returns lowerBaseTille -> Cylinder with small height placed at the bottom
	// of the pin
	private ScadObject getBaseTileLow(double l) {
		// calculates Radius out of given length and an addition of the positive
		// Pin Radius
		double r = l + Params.getPinPRadius();
		ScadObject baseTile;
		// returns small cylinder and enlarges it by the EPSILON value
		baseTile = new Cylinder(Params.getBasePlatePinCircleHeight(), r, true).resize(getEpsilon(), getEpsilon(),
				getEpsilon());
		// translates cylinder by a half of its height
		return (new Translate(baseTile, 0, 0, 0.5 * Params.getBasePlatePinCircleHeight()));
	}

	// calculates the necessary length of a pin so it has a minimum distance to
	// both walls
	private double calculateD() {
		// minumum length
		double min = Params.getPinMinLength() + Params.getCornerRadius();
		// length calculated through trigonometry
		double d = ((Params.getPinDistance() + Params.getPinPRadius() + Params.getE() + 0.5 * Params.getWallwidth())
				/ Math.sin(calculateAngle() / 2)) - Params.getPinPRadius() + Params.getCornerRadius();
		if (d < min) {
			d = min;
		}
		return (d);
	}

	// returns Pin without basePlate
	private ScadObject getPin() {
		// calculate necessary distance for a 4mm gap between wall and Pin
		double d = calculateD();

		double totalHeight = (Params.getBasePlateHeight() - Params.getBasePlatePinCircleHeight()) / 2
				+ 0.5 * Params.getBasePlatePinCircleHeight();

		ScadObject baseCube, fillCube;
		ScadObject pinCylinder;

		// cube segment of pin
		baseCube = (new Cube(d, Params.getPinPWidth(), totalHeight, true)).resize(0.0, 0.0, getEpsilon(), getEpsilon(),
				0.0, getEpsilon());
		// round cylinder segment
		pinCylinder = new Cylinder(totalHeight, Params.getPinPRadius(), true).resize(getEpsilon(), 0.0, getEpsilon());
		// fill between cylinder and cube
		fillCube = new Cube(1, Params.getPinPWidth(), totalHeight, true).resize(0.0, 0.0, getEpsilon(), getEpsilon(),
				0.0, getEpsilon());

		// d= width of cube; translates cylinder to end of cube
		Translate tPinCylinder = new Translate(pinCylinder, (0.5 * d + Params.getPinPRadius()), 0, 0);

		// translate the fill segment between cylinder and cube
		Translate tFillCube = new Translate(fillCube, d * 0.5, 0, 0);
		// defines Union of all pre-defined elements
		Union pinUnion = new Union(new ArrayList<>(Arrays.asList(baseCube, tPinCylinder, tFillCube)));
		// rotates the union to its final position
		Translate result = new Translate(pinUnion, 0.5 * d, 0,
				0.5 * totalHeight + 0.5 * Params.getBasePlatePinCircleHeight());

		return result;
	}

	// returns whole pin object(pin + lower base plate)
	public ScadObject getPinObject() {
		ArrayList<ScadObject> unionArrayList = new ArrayList<>();
		double pinLength;
		ArrayList<ScadObject> pinBottom = new ArrayList<>();
		Edge prevEdge = cornerEdge.getPrev().getTwin();
		double faceArea = cornerEdge.getFace().getArea();
		//just adds a pin if it incident face isn't the infinite face( = negative area)
		if (faceArea > 0) {
			
			pinLength = calculateD();

			unionArrayList.add(
					new Rotate(getPin(), cornerEdge.toVector().bisectorOfAngleTo(prevEdge.toVector()), 0, 0, 1));

		} else {
			pinLength = Params.getPinMinLength();
		}
		//adds head of pin to the length (2*r = d)
		pinLength += 2 * Params.getPinPRadius();
		//generation of base tile -> intersection between complete circular basetile + pin and the face
		pinBottom.add(getBaseTileLow(pinLength));
		//translates the point of the face to the calculation area (0,0) and adds it to the intersection
		pinBottom
				.add(new Translate(new Scale(new Polygon(cornerEdge, getEpsilon()), 1, 1, 100),
						cornerEdge.getN1().getOrigin().multiply(-1), 0));
		//whether the face is the infinite one or not a difference or an intersection must be created
			if (faceArea > 0) {
				unionArrayList.add(new Intersection(pinBottom));
			} else {
				unionArrayList.add(new Difference(pinBottom));
			}
			
		if (getEpsilon() != 0.0){
			unionArrayList.add(getBaseTileLow(Params.getPinMinLength() + 2*Params.getPinPRadius()));
		}
			
		//return of the Union of all pre-defined tiles
		
		//adds the cylinder of the cornerTile
		unionArrayList.add(new Translate(new CornerBaseTile(Params.getBasePlateHeight(),getEpsilon()), 0, 0, 0.5*Params.getBasePlateHeight()));
		return (new Union(unionArrayList));
	}

	// returns scad command
	@Override
	public String toString() {
		return getPinObject().toString();
	}

}
