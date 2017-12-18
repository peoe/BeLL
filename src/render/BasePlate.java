package render;

import java.util.ArrayList;
import java.util.Comparator;

import graph.*;
import render.Params;
import render.objects.*;

public class BasePlate implements ScadObject {
	// face of base plate
	private Face f;
	// params
	private Params params;
	// rotating angle, width and length of the ombb
	private double ombbAngle, width, length;

	/**
	 * Constructor of the BasePlate class using a Face and a Params object.
	 * 
	 * @param f
	 *            the Face of the BasePlate
	 * @param params
	 *            the Params object
	 */
	public BasePlate(Face f, Params params) {
		this.f = f;
		this.params = params;
		if (f.getArea() > 0) {
			ArrayList<Double> values = f.getOMBBInformation();
			ombbAngle = values.get(0);
			width = values.get(1);
			length = values.get(2);
		}
	}

	/**
	 * Returns the modified Polygon.
	 * 
	 * @param p
	 *            the Polygon to be modified
	 * @return modified Polygon
	 */
	public Polygon modifyPolygon(Polygon p) {
		ArrayList<Vector> newPoints = new ArrayList<>();

		for (Vector v : p.getPoints()) {
			newPoints.add(v);
		}

		for (Edge e : p.getIncidentEdge().getFace().getEdges()) {
			int index = p.getPoints().indexOf(e.getN1().getOrigin());

			if (e.getTwin().getFace().getArea() < 0.0) {
				newPoints.set(index, newPoints.get(index).add(this.getCornerPoint(e, e.getPrev())));
				newPoints.set((index + 1) % newPoints.size(),
						newPoints.get((index + 1) % newPoints.size()).add(this.getCornerPoint(e, e.getNext())));
			}
		}

		return new Polygon(newPoints, p.getDelta());
	}

	/**
	 * Returns the translation Vector of the outer Polygon.
	 * 
	 * @param e
	 *            the first Edge of the corner point
	 * @param e2
	 *            the second Edge of the corner point
	 * @return translation Vector
	 */
	private Vector getCornerPoint(Edge e, Edge e2) {
		Vector vE = e.toVector();
		Vector vE2 = e2.toVector();
		Vector epsilonVec = e.toVector().rotate(-0.5 * Math.PI)
				.changeLength(params.getEpsilon() + params.getWallWidth() * 0.5);

		double determinantDivisor = -vE.getX() * vE2.getY() + vE.getY() * vE2.getX();

		if (determinantDivisor != 0) {
			return vE.multiply((epsilonVec.getX() * vE2.getY() - epsilonVec.getY() * vE2.getX()) / determinantDivisor)
					.add(epsilonVec);
		} else {
			return epsilonVec;
		}
	}

	/**
	 * Returns the BasePlate object.
	 * 
	 * @return ScadObject of the BasePlate
	 */
	private ScadObject getBasePlateObject() {
		ArrayList<ScadObject> differenceCorners = new ArrayList<>();

		differenceCorners.add(new Translate(
				new Scale(this.modifyPolygon(new Polygon(getF().getIncidentEdge(), -0.5 * params.getEpsilon())), 1, 1,
						params.getBasePlateHeight()),
				0, 0, 0.5 * params.getBasePlateHeight()));

		for (Edge e : getF().getEdges()) {
			differenceCorners
					.add(new Translate(new CornerPin(e, params.getEpsilon(), params), e.getN1().getOrigin(), 0));
		}

		return new Difference(differenceCorners);
	}

	// getters - setters
	/**
	 * Returns the Face of the BasePlate.
	 * 
	 * @return Face of the BasePlate
	 */
	public Face getF() {
		return f;
	}

	/**
	 * Sets the Face of the BasePlate.
	 * 
	 * @param f
	 *            the Face to be set for the BasePlate
	 */
	public void setF(Face f) {
		this.f = f;
	}

	/**
	 * Returns the Params object of the BasePlate.
	 * 
	 * @return Params object of BasePlate
	 */
	public Params getParams() {
		return params;
	}

	/**
	 * Sets the Params object of the BasePlate.
	 * 
	 * @param params
	 *            the Params object to be set for the BasePlate
	 */
	public void setParams(Params params) {
		this.params = params;
	}

	/**
	 * Returns the ombb angle of the BasePlate.
	 * 
	 * @return ombb angle as Double value
	 */
	public double getOmbbAngle() {
		return ombbAngle;
	}

	/**
	 * Sets the ombb angle of the BasePlate.
	 * 
	 * @param ombbAngle
	 *            the value to be set for the ombb angle
	 */
	public void setOmbbAngle(double ombbAngle) {
		this.ombbAngle = ombbAngle;
	}

	/**
	 * Returns the width of the BasePlate.
	 * 
	 * @return width as Double value
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Sets the width of the BasePlate.
	 * 
	 * @param width
	 *            the width to be set for the BasePlate
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * Returns the length of the BasePlate.
	 * 
	 * @return length as Double value
	 */
	public double getLength() {
		return length;
	}

	/**
	 * Sets the length of the BasePlate.
	 * 
	 * @param length
	 *            the length to be set for the BasePlate
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * Returns the ombb area of the BasePlate.
	 * 
	 * @return ombb area as Double value
	 */
	public double getOmbbArea() {
		return width * length;
	}

	/**
	 * Comparator used for ArrayList sorting
	 * sorts after OMBB area
	 */
	public static Comparator<BasePlate> BasePlateOMBBComparator = new Comparator<BasePlate>() {

		public int compare(BasePlate b1, BasePlate b2) {
			double ombb1 = b1.getOmbbArea();
			double ombb2 = b2.getOmbbArea();
		   return Double.compare(ombb1, ombb2);
	    }};
	
	/**
	 * Returns a String of the BasePlate object used for creating it in
	 * OpenSCAD.
	 * 
	 * @return String of BasePlate
	 */
	@Override
	public String toString() {
		return getBasePlateObject().toString();
	}

}
