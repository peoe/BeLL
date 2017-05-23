package render;
import java.util.ArrayList;

import graph.*;
import render.objects.*;

public class BasePlate implements ScadObject{
	
	//face of base plate
	private Face f;

	public Face getF() {
		return f;
	}

	public void setF(Face f) {
		this.f = f;
	}
	
	public BasePlate(Face f){
		this.f = f;
	}
	//modifies a polygon if its edges appear outside
	/**
	 * Modifies outer Polygons
	 * @param p Polygon
	 * @return Polygon Modified Polygon 
	 */
	public static Polygon modifyPolygon(Polygon p){
		ArrayList<Vector> newPoints = new ArrayList<>();
		for (Vector v : p.getPoints()){
			newPoints.add(v);
		}
		
		for (Edge e : p.getIncidentEdge().getFace().getEdges()){
			int index = p.getPoints().indexOf(e.getN1().getOrigin());
			if (e.getTwin().getFace().getArea() < 0.0){
				newPoints.set(index, newPoints.get(index).add(BasePlate.getCornerPoint(e, e.getPrev())));
				newPoints.set((index +1) % newPoints.size(), newPoints.get((index +1) % newPoints.size()).add(BasePlate.getCornerPoint(e, e.getNext())));
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
	private static Vector getCornerPoint(Edge e, Edge e2){
		Vector vE = e.toVector();
		Vector vE2 = e2.toVector();
		Vector epsilonVec = e.toVector().rotate(-0.5*Math.PI).changeLength(Params.getE() + Params.getWallwidth()*0.5);
		double determinantDivisor = -vE.getX() * vE2.getY() + vE.getY() * vE2.getX();
		if (determinantDivisor != 0){
		return vE.multiply((epsilonVec.getX() * vE2.getY() - epsilonVec.getY() * vE2.getX())/determinantDivisor).add(epsilonVec);
		}
		else {
			return epsilonVec;
		}
		
		
		
	}
	
	/**
	 * Calculates the Object consisting of Differences between a polygon and the negative corner elements
	 * @return the basePlate ScadObject
	 */
	private ScadObject getBasePlateObject(){
		ArrayList<ScadObject> differenceCorners = new ArrayList<>();
		differenceCorners.add(new Translate(new Scale(BasePlate.modifyPolygon(new Polygon(getF().getIncidentEdge(), -0.5 * Params.getE())), 1, 1, Params.getBasePlateHeight()), 0, 0, 0.5*Params.getBasePlateHeight()));
		
		for(Edge e: getF().getEdges()){
			differenceCorners.add(new Translate(new CornerPin(e, Params.getE()), e.getN1().getOrigin(), 0));
		}
		return new Difference(differenceCorners);
	}

	@Override
	public String toString() {
		return getBasePlateObject().toString();
	}

}
