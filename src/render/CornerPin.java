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
	//Parameters
	private Params params;

	// Getters and Setters
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

	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	// constructor
	/**
	 * Constructor of CornerPin class
	 * @param e DCEL Edge which defines the angle of the pin
	 * @param epsilon Epsilon margin
	 */
	public CornerPin(Edge e, double epsilon, Params params) {
		cornerEdge = e;
		this.epsilon = epsilon;
		this.params = params;

	}

	// calculates the angle to the next Edge anti-clockwise
	/**
	 * Calculates angle of pin
	 * @return double value of angle
	 */
	private double calculateAngle() {
		return cornerEdge.angleToEdge(cornerEdge.getPrev().getTwin());
	}

	// returns lowerBaseTille -> Cylinder with small height placed at the bottom
	// of the pin
	/**
	 * Returns lower baseTile 
	 * @param l Length/Radius of lower BaseTile
	 * @return ScadObject BaseTileLow
	 */
	private ScadObject getBaseTileLow(double l) {
		// calculates Radius out of given length and an addition of the positive
		// Pin Radius
		double r = l + params.getPinPRadius();
		ScadObject baseTile;
		// returns small cylinder and enlarges it by the EPSILON value
		baseTile = new Cylinder(params.getBasePlatePinCircleHeight(), r, true).resize(getEpsilon(), getEpsilon(),
				getEpsilon());
		// translates cylinder by a half of its height
		return (new Translate(baseTile, 0, 0, 0.5 * params.getBasePlatePinCircleHeight()));
	}

	// calculates the necessary length of a pin so it has a minimum distance to
	// both walls
	/**
	 * Calculates necessary length of pin to have a minimum distance to adjacent walls
	 * @return double Length
	 */
	public double calculateD() {
		// minumum length
		double min = params.getPinMinLength();
		// length calculated through trigonometry
		double d = ((params.getPinDistance() + params.getPinPRadius() + params.getEpsilon() /*+ 0.5 * Params.getWallwidth()*/)
				/ Math.sin(calculateAngle() / 2.0)) - params.getPinPRadius();
		if (d < min) {
			d = min;
		}
		return (d);
	}

	// returns Pin without basePlate
	/**
	 * Returns the PinObject without the lower BaseTile
	 * @return ScadObject Pin
	 */
	private ScadObject getPin() {
		// calculate necessary distance for a 4mm gap between wall and Pin
		double d = calculateD();

		double totalHeight = (params.getBasePlateHeight() - params.getBasePlatePinCircleHeight()) / 2
				+ 0.5 * params.getBasePlatePinCircleHeight();

		ScadObject baseCube, fillCube;
		ScadObject pinCylinder;

		// cube segment of pin
		baseCube = (new Cube(d, params.getPinPWidth(), totalHeight, true)).resize(0.0, 0.0, getEpsilon(), getEpsilon(),
				0.0, getEpsilon());
		// round cylinder segment
		pinCylinder = new Cylinder(totalHeight, params.getPinPRadius(), true).resize(getEpsilon(), 0.0, getEpsilon());
		// fill between cylinder and cube
		fillCube = new Cube(1, params.getPinPWidth(), totalHeight, true).resize(0.0, 0.0, getEpsilon(), getEpsilon(),
				0.0, getEpsilon());

		// d= width of cube; translates cylinder to end of cube
		Translate tPinCylinder = new Translate(pinCylinder, (0.5 * d + params.getPinPRadius()), 0, 0);

		// translate the fill segment between cylinder and cube
		Translate tFillCube = new Translate(fillCube, d * 0.5, 0, 0);
		// defines Union of all pre-defined elements
		Union pinUnion = new Union(new ArrayList<>(Arrays.asList(baseCube, tPinCylinder, tFillCube)));
		// rotates the union to its final position
		Translate result = new Translate(pinUnion, 0.5 * d, 0,
				0.5 * totalHeight + 0.5 * params.getBasePlatePinCircleHeight());

		return result;
	}

	// returns whole pin object(pin + lower base plate)
	/**
	 * Combines Pin and lowerBaseTile to create the Pin Object
	 * @return ScadObject whole Pin
	 */
	public ScadObject getPinObject() {
		ArrayList<ScadObject> unionArrayList = new ArrayList<>();
		double pinLength;
		ArrayList<ScadObject> pinBottom = new ArrayList<>();
		Edge prevEdge = cornerEdge.getPrev().getTwin();
		double faceArea = cornerEdge.getFace().getArea();
		//adds a pin if incident face isn't the infinite face( = negative area)
		if (faceArea > 0) {
			
			pinLength = calculateD();

			unionArrayList.add(
					new Rotate(getPin(), cornerEdge.toVector().bisectorOfAngleTo(prevEdge.toVector()), 0, 0, 1));
			//adds head of pin to the length (2*r = d)
			pinLength += 2 * params.getPinPRadius();

		} else {
			pinLength = params.getPinMinLength();
		}
		//generation of base tile -> intersection between complete circular basetile + pin and the face
		pinBottom.add(getBaseTileLow(pinLength));
		//translates the point of the face to the calculation area (0,0) and adds it to the intersection
		pinBottom
				.add(new Translate(new Scale(new Polygon(cornerEdge, getEpsilon()), 1, 1, params.getBasePlateHeight()*2),
						cornerEdge.getN1().getOrigin().multiply(-1), 0));
		//whether the face is the infinite one or not a difference or an intersection must be created
			if (faceArea > 0) {
				unionArrayList.add(new Intersection(pinBottom));
			} else  {
				unionArrayList.add(new Difference(pinBottom));
			}
			
			
		if (getEpsilon() != 0.0){
			unionArrayList.add(getBaseTileLow(params.getPinMinLength()));
		}
			
		//return of the Union of all pre-defined tiles
		
		//adds the cylinder of the cornerTile
		unionArrayList.add(new Translate(new CornerBaseTile(params.getBasePlateHeight(),getEpsilon(), params), 0, 0, 0.5*params.getBasePlateHeight()));
		return (new Union(unionArrayList));
	}

	// returns scad command
	@Override
	public String toString() {
		return getPinObject().toString();
	}

}