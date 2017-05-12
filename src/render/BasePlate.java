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
	
	/**
	 * Calculates the Object consisting of Differences between a polygon and the negative corner elements
	 * @return the basePlate ScadObject
	 */
	private ScadObject getBasePlateObject(){
		ArrayList<ScadObject> differenceCorners = new ArrayList<>();
		differenceCorners.add(new Translate(new Scale(new Polygon(getF().getIncidentEdge(), -0.5*Params.getE()), 1, 1, Params.getBasePlateHeight()), 0, 0, 0.5*Params.getBasePlateHeight()));
		
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
