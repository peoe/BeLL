package render;

import java.util.ArrayList;
import graph.*;
import render.objects.*;

public class Corner implements ScadObject {

	private Node n;
	//epsilon distance between positive corner and negative 
	private double Epsilon;

	public Node getN() {
		return n;
	}

	public void setN(Node n) {
		this.n = n;
	}

	public double getEpsilon() {
		return Epsilon;
	}

	public void setEpsilon(double epsilon) {
		Epsilon = epsilon;
	}

	public Corner(Node n, double epsilon) {
		this.n = n;
		Epsilon = epsilon;
	}
	
	public Corner(Node n) {
		this.n = n;
		Epsilon = 0.0;
	}
	
	/**
	 * returns whole corner object consisting of CornerCylinder and CornerPin
	 * @return the Corner ScadObject
	 */
	public ScadObject getCorner(){
		ArrayList<ScadObject> cornerObjects = new ArrayList<>();
		for(Edge e : getN().getAdjacentEdges()){
			cornerObjects.add(new CornerPin(e, getEpsilon()));
		}
		cornerObjects.add(new CornerCylinder(getN(), getEpsilon()));
		return new Union(cornerObjects);
	}
	

	@Override
	public String toString() {
		return getCorner().toString();
	}

}
