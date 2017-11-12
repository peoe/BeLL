package graph;

import java.util.ArrayList;

import render.objects.Polygon;

public class Face {

	// incident edge of face
	private Edge incidentEdge;

	//getter and setter
	public Edge getIncidentEdge() {
		return incidentEdge;
	}

	public void setIncidentEdge(Edge incidentEdge) {
		this.incidentEdge = incidentEdge;
	}

	// constructor
	/**
	 * Creates a new instance of the Face class.
	 */
	public Face(Edge e) {
		incidentEdge = e;
	}

	// get - set
	// getEdges
	/**
	 * Returns the ArrayList containing all of the edges.
	 * 
	 * @return ArrayList of all edges
	 */
	public ArrayList<Edge> getEdges() {
		ArrayList<Edge> returnEdges = new ArrayList<>();
		Edge startingEdge = this.getIncidentEdge();
		Edge iterationEdge = startingEdge;
		do {
			returnEdges.add(iterationEdge);
			iterationEdge = iterationEdge.getNext();
		} while (startingEdge != iterationEdge);
		return returnEdges;

	}

	// printing all edges
	/**
	 * Prints out a String containing all edges from within the Face.
	 */
	public String toString() {
		String s = "[";
		// loop through all edges and add them to the existing String
		for (int i = 0; i < getEdges().size(); i++) {
			s = s.concat(getEdges().get(i).toString());
			if (i != getEdges().size() - 1) {
				s = s.concat(",");
			}
		}
		return s.concat("]");
	}
	/**
	 * Returns the area of a face using shoelace formula (Gausssche
	 * Trapezformel).
	 * 
	 * @return area of specific face
	 */
	public Double getArea() {

		Double area = 0.0;

		// stop if the face has to few points (e.g.: <= 2)
		// if (points.get(0) == null || points.size() < 3) {
		// return 0.0;
		// }

		// this is where the magic happens
		for (int i = 0; i < getEdges().size(); i++) {
			area += (getEdges().get(i).getN1().getOrigin().getY()
					+ getEdges().get(i).getNext().getN1().getOrigin().getY())
					* (getEdges().get(i).getN1().getOrigin().getX()
							- getEdges().get(i).getNext().getN1().getOrigin().getX());
		}

		return area / 2.0;
	}
	/**
	 * Returns all nodes of face
	 * @return all nodes of face
	 */
	public ArrayList<Node> getNodes() {
		ArrayList<Node> returnNodes = new ArrayList<>();
		ArrayList<Edge> faceEdges = getEdges();
		for (Edge e : faceEdges) {
			returnNodes.add(e.getN1());
		}
		return returnNodes;
	}
	
	//calculating convex hull
	/**
	 * calculates the convex hull of the face
	 * @return ArrayList<Node> of the connvex hull
	 */
	public ArrayList<Node> getConvexHull(){
		int mostLeftNode = 0;
		ArrayList<Node> convexHull = new ArrayList<>();
		ArrayList<Node> faceNodes = getNodes();
		for (int i = 0; i < faceNodes.size();i++){
			if (faceNodes.get(i).getOrigin().getX() < faceNodes.get(mostLeftNode).getOrigin().getX()){
				mostLeftNode = i;
			}
		}
		int i = mostLeftNode, nextPoint;
	
		double angleBetweenVectors;
		
		do {
			//int P1 = i;
			convexHull.add(faceNodes.get(i));
			nextPoint = (i + 1) % faceNodes.size();
			for(int j = 0; j < faceNodes.size();j++){
				angleBetweenVectors = new Vector(faceNodes.get(i).getOrigin(), faceNodes.get(j).getOrigin()).angletoVector(new Vector(faceNodes.get(i).getOrigin(), faceNodes.get(nextPoint).getOrigin()));
				if((j != i && angleBetweenVectors < Math.PI) || angleBetweenVectors == 2*Math.PI){
					nextPoint = j;
				}	
			}
			i = nextPoint;
			
		} while(nextPoint != mostLeftNode);
		
	return convexHull;
	}
	
	//
	public double getOMBBAngle(){
		ArrayList<Node> nodeConvexHull = getConvexHull();
		ArrayList<Vector> convexHull = new ArrayList<>();
		for (Node n : nodeConvexHull){
			convexHull.add(n.getOrigin());
		}
		
		double edgeAngle;
		double xMax = Double.MIN_VALUE, yMax = Double.MIN_VALUE,
		xMin = Double.MAX_VALUE, yMin = Double.MAX_VALUE, ombbArea, ombbAreMin = Double.MAX_VALUE, ombbAngle = 0.0;
		ArrayList<Vector> ombb = new ArrayList<>();
		for (int i  = 0; i < convexHull.size() - 2; i++){
			edgeAngle = -new Vector(convexHull.get(i), convexHull.get(i + 1)).angle();
			for (Vector v : convexHull){
				v = v.rotate(edgeAngle);
				if (v.getX() > xMax){
					xMax = v.getX();
				}
				if (v.getX() < xMin){
					xMin = v.getX();
				}
				if (v.getY() > yMax){
					yMax = v.getY();
				}
				if (v.getY() < yMin){
					yMin = v.getY();
				}
				
			}
			ombbArea = (xMax - xMin) * (yMax - yMin);
			if(ombbArea < ombbAreMin){
				ombbAreMin = ombbArea;
				ombbAngle = edgeAngle;
				if (xMax - xMin < yMax - yMin){
					ombbAngle += 0.5 * Math.PI;
				}
				
				ombb.clear();
				ombb.add(new Vector(xMin, yMin));
				ombb.add(new Vector(xMin, yMax));
				ombb.add(new Vector(xMax, yMax));   
				ombb.add(new Vector(xMax, yMin));
			}
			
			
		
			
			
		}
		System.out.println(new Polygon(ombb, 0).toString());
		return ombbAngle;
		
	}
	
	
		
		
		
	}

