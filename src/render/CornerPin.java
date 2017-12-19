package render;

import java.util.ArrayList;
import java.util.Arrays;

import graph.*;
import render.objects.*;

public class CornerPin implements ScadObject {
	// incident Edge with defines the pin completely
	private Edge cornerEdge;
	// distance between positive and negative pin
	private double epsilon;
	// params
	private Params params;

	/**
	 * Constructor of the CornerPin class using an Edge, an epsilon value and a
	 * Params object.
	 * 
	 * @param e
	 *            the Edge defining the angle of the pin
	 * @param epsilon
	 *            the epsilon value
	 */
	public CornerPin(Edge e, double epsilon, Params params) {
		cornerEdge = e;
		this.epsilon = epsilon;
		this.params = params;
	}

	/**
	 * Returns the angle to the Edge.
	 * 
	 * @return angle as Double value
	 */
	private double calculateAngle() {
		return cornerEdge.angleToEdge(cornerEdge.getPrev().getTwin());
	}

	/**
	 * Returns the lower base tile of the CornerPin.
	 * 
	 * @param l
	 *            the radius of the lower base tile
	 * @return ScadObject of lower base tile
	 */
	private ScadObject getBaseTileLow(double l) {
		ScadObject baseTile;

		// returns small cylinder and enlarges it by epsilon value
		baseTile = new Cylinder(params.getBasePlatePinCircleHeight(), l, true).resize(getEpsilon(), getEpsilon(),
				getEpsilon());

		// translates cylinder by a half of its height
		return (new Translate(baseTile, 0, 0, 0.5 * params.getBasePlatePinCircleHeight()));
	}

	/**
	 * Returns the necessary length of the Pin to have a minimum distance to
	 * adjacent Walls.
	 * 
	 * @return length as Double value
	 */
	public double calculateD() {
		double min = params.getPinMinLength();
		// length calculated through trigonometry
		double d = ((params.getPinDistance() + params.getPinPRadius()
				+ params.getEpsilon() /* + 0.5 * Params.getWallwidth() */) / Math.sin(calculateAngle() / 2.0))
				- params.getPinPRadius();

		if (d < min) {
			d = min;
		}

		return (d);
	}

	/**
	 * Returns the Pin object without the lower base tile.
	 * 
	 * @return ScadObject of the Pin
	 */
	private ScadObject getPin() {
		// calculate necessary distance for a 4mm gap between wall and Pin
		double d = calculateD() + params.getCornerRadius();
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

		// d = width of cube; translates cylinder to end of cube
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

	/**
	 * Returns the entire Pin object with the lower base tile.
	 * 
	 * @return ScadObject of the whole Pin
	 */
	public ScadObject getPinObject() {
		ArrayList<ScadObject> unionArrayList = new ArrayList<>();
		ArrayList<ScadObject> pinBottom = new ArrayList<>();

		Edge prevEdge = cornerEdge.getPrev().getTwin();

		double pinLength;
		double faceArea = cornerEdge.getFace().getArea();

		// adds a pin if incident face isn't the infinite face( = negative area)
		if (faceArea > 0) {
			pinLength = calculateD() + params.getCornerRadius();

			unionArrayList
					.add(new Rotate(getPin(), cornerEdge.toVector().bisectorOfAngleTo(prevEdge.toVector()), 0, 0, 1));

			// adds head of pin to the length (2*r = d)
			pinLength += 2 * params.getPinPRadius();
		} else {
			pinLength = params.getPinMinLength() + params.getCornerRadius();
		}

		// generation of base tile -> intersection between complete circular
		// basetile + pin and the face
		pinBottom.add(getBaseTileLow(pinLength + params.getPinPRadius()));
		// translates the point of the face to the calculation area (0,0) and
		// adds it to the intersection
		pinBottom.add(
				new Translate(new Scale(new Polygon(cornerEdge, getEpsilon()), 1, 1, params.getBasePlateHeight() * 2),
						cornerEdge.getN1().getOrigin().multiply(-1), 0));

		// whether the face is the infinite one or not a difference or an
		// intersection must be created
		if (faceArea > 0) {
			unionArrayList.add(new Intersection(pinBottom));
		} else {
			unionArrayList.add(new Difference(pinBottom));
		}

		if (getEpsilon() != 0.0) {
			unionArrayList.add(getBaseTileLow(params.getPinMinLength() + params.getCornerRadius()));
		}

		// adds the cylinder of the cornerTile
		unionArrayList.add(new Translate(new CornerBaseTile(params.getBasePlateHeight(), getEpsilon(), params), 0, 0,
				0.5 * params.getBasePlateHeight()));
		return (new Union(unionArrayList));
	}

	// getters - setters
	/**
	 * Returns the epsilon value of the Pin.
	 * 
	 * @return epsilon value of the Pin
	 */
	public double getEpsilon() {
		return epsilon;
	}

	/**
	 * Sets the Epsilon value of the Pin
	 * 
	 * @param epsilon
	 *            the value to be set as epsilon value
	 */
	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

	/**
	 * Returns the Edge of the CornerPin.
	 * 
	 * @return Edge of CornerPin
	 */
	public Edge getCornerEdge() {
		return cornerEdge;
	}

	/**
	 * Sets the Edge of the CornerPin.
	 * 
	 * @param cornerEdge
	 *            the Edge for the CornerPin
	 */
	public void setCornerEdge(Edge cornerEdge) {
		this.cornerEdge = cornerEdge;
	}

	/**
	 * Returns the Params object of the CornerPin.
	 * 
	 * @return Params object of CornerPin
	 */
	public Params getParams() {
		return params;
	}

	/**
	 * Sets the Params object of the CornerPin.
	 * 
	 * @param params
	 *            the Params object for the CornerPin
	 */
	public void setParams(Params params) {
		this.params = params;
	}

	/**
	 * Returns a String of the CornerPin used for creating it in OpenSCAD.
	 * 
	 * @return String of CornerPin
	 */
	@Override
	public String toString() {
		return getPinObject().toString();
	}

}