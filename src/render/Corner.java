package render;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.SynchronousQueue;

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
	
	public ScadObject getCorner(){
		ArrayList<ScadObject> cornerObjects = new ArrayList<>();
		for(Edge e : getN().getAdjacentEdges()){
			cornerObjects.add(new Pin(e, getEpsilon()));
		}
		cornerObjects.add(new CornerCylinder(getN(), getEpsilon()));
		return new Union(cornerObjects);
	}
	

	@Override
	public String toString() {
		return getCorner().toString();
	}

}
