package render;
import java.util.ArrayList;

import graph.*;
import render.Params;
import render.objects.*;

public class BasePlate implements ScadObject{
	//face of base plate
	private Face f;
	//Parameters
	private Params params;
	//rotating angle for the minimum bounding box and width and length of the ombb
	private double ombbAngle, width, length, ombbArea;

	public Face getF() {
		return f;
	}

	public void setF(Face f) {
		this.f = f;
	}
	
	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	public double getOmbbAngle() {
		return ombbAngle;
	}

	public void setOmbbAngle(double ombbAngle) {
		this.ombbAngle = ombbAngle;
	}

	public double getOmbbArea() {
		return ombbArea;
	}

	public void setOmbbArea(double ombbArea) {
		this.ombbArea = ombbArea;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public BasePlate(Face f, Params params){
		this.f = f;
		this.params = params;
		if (f.getArea() > 0) {
			ArrayList<Double> values = f.getOMBBInformation();
			ombbAngle = values.get(0);
			width = values.get(1);
			length = values.get(2);
			ombbArea = width * length;
		}
	}

	/**
	 * Modifies outer Polygons
	 * @param p Polygon
	 * @return Polygon Modified Polygon 
	 */
	public Polygon modifyPolygon(Polygon p){
		ArrayList<Vector> newPoints = new ArrayList<>();
		
		for (Vector v : p.getPoints()) {
			newPoints.add(v);
		}
		
		for (Edge e : p.getIncidentEdge().getFace().getEdges()) {
			int index = p.getPoints().indexOf(e.getN1().getOrigin());
			
			if (e.getTwin().getFace().getArea() < 0.0) {
				newPoints.set(index, newPoints.get(index).add(this.getCornerPoint(e, e.getPrev())));
				newPoints.set((index + 1) % newPoints.size(), newPoints.get((index + 1) % newPoints.size()).add(this.getCornerPoint(e, e.getNext())));
			} 
		}
		
		return new Polygon(newPoints, p.getDelta());
	}
	
	/**
	 * Calculates necessary translation of a point of outer Polygons
	 * @param e first Edge
	 * @param e2 second Edge
	 * @return Vector Translation Vector for the point
	 */
	private Vector getCornerPoint(Edge e, Edge e2){
		Vector vE = e.toVector();
		Vector vE2 = e2.toVector();
		Vector epsilonVec = e.toVector().rotate(-0.5 * Math.PI).changeLength(params.getEpsilon() + params.getWallwidth() * 0.5);
		
		double determinantDivisor = -vE.getX() * vE2.getY() + vE.getY() * vE2.getX();
		
		if (determinantDivisor != 0) {
			return vE.multiply((epsilonVec.getX() * vE2.getY() - epsilonVec.getY() * vE2.getX())/determinantDivisor).add(epsilonVec);
		} else {
			return epsilonVec;
		}		
	}
	
	/**
	 * Calculates the Object consisting of Differences between a polygon and the negative corner elements
	 * @return the basePlate ScadObject
	 */
	private ScadObject getBasePlateObject(){
		ArrayList<ScadObject> differenceCorners = new ArrayList<>();
		differenceCorners.add(new Translate(new Scale(this.modifyPolygon(new Polygon(getF().getIncidentEdge(), -0.5 * params.getEpsilon())), 1, 1, params.getBasePlateHeight()), 0, 0, 0.5 * params.getBasePlateHeight()));
		
		for(Edge e: getF().getEdges()) {
			differenceCorners.add(new Translate(new CornerPin(e, params.getEpsilon(), params), e.getN1().getOrigin(), 0));
		}
		return new Difference(differenceCorners);
	}

	@Override
	public String toString() {
		return getBasePlateObject().toString();
	}

}
