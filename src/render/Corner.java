package render;

import java.util.ArrayList;
import graph.*;
import render.objects.*;

public class Corner implements ScadObject {

	private Node n;
	//epsilon distance between positive corner and negative 
	private double Epsilon;
	//3D Object
	private ScadObject object;
	//Parameters
	private Params params;
	//width
	private double width;

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

	public ScadObject getObject() {
		return object;
	}

	public void setObject(ScadObject object) {
		this.object = object;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
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
		this.object = getCorner();
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
		this.object = getCorner();
	}
	
	/**
	 * returns whole corner object consisting of CornerCylinder and CornerPin
	 * @return the Corner ScadObject
	 */
	public ScadObject getCorner(){
		ArrayList<ScadObject> cornerObjects = new ArrayList<>();
		double maxD = 0.0;
		for(Edge e : getN().getAdjacentEdges()){
			cornerObjects.add(new CornerPin(e, getEpsilon(), params));
			if ((( CornerPin ) cornerObjects.get(cornerObjects.size() - 1)).calculateD() > maxD) {
				maxD = (( CornerPin ) cornerObjects.get(cornerObjects.size() - 1)).calculateD(); 
			}
		}
		setWidth(2 * maxD + 6 * params.getPinPRadius() + 4 *params.getEpsilon());
		cornerObjects.add(new CornerCylinder(getN(), getEpsilon(), params));
		return new Union(cornerObjects);
	}
	

	@Override
	public String toString() {
		return getObject().toString();
	}

}
