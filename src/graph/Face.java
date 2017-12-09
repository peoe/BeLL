package graph;

import java.util.ArrayList;

import render.objects.Polygon;

public class Face {

	// incident edge of face
	private Edge incidentEdge;

	// constructor
	/**
	 * Constructor for the Face class.
	 * @param e the incident edge of the face
	 */
	public Face(Edge e) {
		incidentEdge = e;
	}
	
	/**
	 * Returns the area of a face using the shoelace formula.
	 * 
	 * @return area of the face
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
	
	//calculating convex hull
	/**
	 * calculates the convex hull of the face
	 * @return ArrayList<Node> of the convex hull
	 */
	public ArrayList<Node> getConvexHull(){
		int mostLeftNode = 0;
		
		ArrayList<Node> convexHull = new ArrayList<>();
		ArrayList<Node> faceNodes = getNodes();
		
		for (int i = 0; i < faceNodes.size();i++){
			if ((faceNodes.get(i).getOrigin().getY() < faceNodes.get(mostLeftNode).getOrigin().getY()) || (faceNodes.get(i).getOrigin().getY() == faceNodes.get(mostLeftNode).getOrigin().getY() && faceNodes.get(i).getOrigin().getX() < faceNodes.get(mostLeftNode).getOrigin().getX())){
				mostLeftNode = i;
			}
		}
		
		int i = mostLeftNode, nextPoint;
		double angleBetweenVectors;
		
		do {
			convexHull.add(faceNodes.get(i));
			nextPoint = (i + 1) % faceNodes.size();
			for(int j = 0; j < faceNodes.size();j++){
				if (j != i){
				Vector vNP = new Vector(faceNodes.get(i).getOrigin(), faceNodes.get(j).getOrigin());
				Vector vnew = new Vector(faceNodes.get(i).getOrigin(), faceNodes.get(nextPoint).getOrigin());
				angleBetweenVectors = vNP.angletoVector(vnew);
				if(angleBetweenVectors < Math.PI || angleBetweenVectors == 2*Math.PI){
					nextPoint = j; 
				}	
				}
			}
			i = nextPoint;
		} while(nextPoint != mostLeftNode);
		
	return convexHull;
	}
	
	public ArrayList<Double> getOMBBInformation(){
		ArrayList<Node> nodeConvexHull = getConvexHull();
		ArrayList<Vector> convexHull = new ArrayList<>();
		ArrayList<Double> result = new ArrayList<>();
		
		result.add(0.0); //0 = angle
		result.add(0.0); //1 = width
		result.add(0.0); //2 = length
		
		for (Node n : nodeConvexHull){
			convexHull.add(n.getOrigin());
		}
		
		double edgeAngle;
		double xMax, yMax,
		xMin, yMin , ombbArea, ombbAreMin = Double.MAX_VALUE, ombbAngle = 0.0;
		//ArrayList<Vector> ombbInfo = new ArrayList<>();
		
		for (int i  = 0; i < convexHull.size() - 2; i++){
			edgeAngle = -new Vector(convexHull.get(i), convexHull.get(i + 1)).angle();
			xMax = Double.MIN_VALUE;
			yMax = Double.MIN_VALUE;
			xMin = Double.MAX_VALUE;
			yMin = Double.MAX_VALUE;
			
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
				result.set(1, xMax - xMin);
				result.set(2, yMax - yMin);
				if (xMax - xMin < yMax - yMin){
					ombbAngle += 0.5 * Math.PI;
					result.set(1, result.get(2));
					result.set(2, xMax - xMin);
				}
				result.set(0, ombbAngle);
			}
		}
		
		return result; 
	}

	//getter and setter
	/**
	 * Returns the ArrayList containing all edges of the face.
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
	
	public ArrayList<Node> getNodes() {
		ArrayList<Node> returnNodes = new ArrayList<>();
		ArrayList<Edge> faceEdges = getEdges();
		for (Edge e : faceEdges) {
			returnNodes.add(e.getN1());
		}
		return returnNodes;
	}

	public Edge getIncidentEdge() {
		return incidentEdge;
	}

	public void setIncidentEdge(Edge incidentEdge) {
		this.incidentEdge = incidentEdge;
	}

	/**
	 * Returns a string containing all edges of the face.
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
		
}

