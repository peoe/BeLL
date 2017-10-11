package render;

import java.util.ArrayList;
import graph.*;
import render.objects.*;

public class Corner implements ScadObject {

	private Node n;
	//epsilon distance between positive corner and negative 
	private double Epsilon;
	//Parameters
	private Params params;

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

	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	/**
	 * Constructor of Corner class using a Node and an epsilon margin
	 * @param n DCEL Node of Corner
	 * @param epsilon margin amount
	 */
	public Corner(Node n, double epsilon, Params params) {
		this.n = n;
		Epsilon = epsilon;
		this.params = params;
	}
	
	/**
	 * Constructor of Corner class using a Node and epsilon margin = 0
	 * @param n DCEL Node of Corner
	 * 
	 */
	public Corner(Node n, Params params) {
		this.n = n;
		Epsilon = 0.0;
		this.params = params;
	}
	
	/**
	 * returns whole corner object consisting of CornerCylinder and CornerPin
	 * @return the Corner ScadObject
	 */
	public ScadObject getCorner(){
		ArrayList<ScadObject> cornerObjects = new ArrayList<>();
		for(Edge e : getN().getAdjacentEdges()){
			cornerObjects.add(new CornerPin(e, getEpsilon(), params));
		}
		cornerObjects.add(new CornerCylinder(getN(), getEpsilon(), params));
		return new Union(cornerObjects);
	}
	

	@Override
	public String toString() {
		return getCorner().toString();
	}

}
