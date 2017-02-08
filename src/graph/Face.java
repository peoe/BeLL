package graph;

import java.util.ArrayList;
import java.util.Collections;

public class Face {
	
	/*
	 * Die System.out.prinln(...); sind für Debugging
	 * und Überprüfen der Ausgaben vor einer weiteren Verarbeitung.
	 */

	// a list of all vectors + corresponding vectors
	private ArrayList<Vector> edges;

	// constructor
	public Face() {
		setEdges(new ArrayList<>());
	}

	// get - set
	public ArrayList<Vector> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Vector> edges) {
		this.edges = edges;
	}

	/**
	 * Returns all vectors at a given point p.
	 * 
	 * @param p
	 *            the point with adjacent vectors
	 * @return ArrayList of adjacent vectors
	 */
	public ArrayList<Vector> getVectorsByPoint(Point p) {
		ArrayList<Vector> vs = new ArrayList<>();

		for (Vector v : getEdges()) {
			if (v.contains(p)) {
				vs.add(v);
			}
		}

		return vs;
	}

	/**
	 * Returns the Point which all vectors in the ArrayList have in common.
	 * 
	 * @param vs
	 *            ArrayList of vectors to be examined
	 * @return the common Point of all vectors
	 */
	public static Point getCommonPoint(ArrayList<Vector> vs) {
		boolean done = false;

		Point p = vs.get(0).getP1();

		for (Vector v : vs) {
			if (v.contains(p)) {
				done = true;
			} else {
				done = false;
			}
		}

		if (!done) {
			p = vs.get(0).getP2();

			for (Vector v : vs) {
				if (v.contains(p)) {
					done = true;
				} else {
					done = false;
				}
			}

			if (!done) {
				//System.out.println("Error occured while determining common point");
				return null;
			} else {
				return p;
			}
		} else {
			return p;
		}

	}

	/**
	 * Generates complementary vectors for all vectors already existing
	 */
	public void generateComplementaryEdges() {
		int amount = getEdges().size();
		for (int i = 0; i < amount; i++) {
			getEdges().add(getEdges().get(i).getComplementaryVector());
		}
	}

	/**
	 * Returns a list of all vectors which point away from a certain Point.
	 * 
	 * @param p
	 *            the point from which the vectors point away
	 * @return ArrayList of vectors pointing away from p
	 */
	public ArrayList<Vector> getVectorsPointingAway(Point p) {
		//System.out.println("comp: " + p);
		
		ArrayList<Vector> vs = new ArrayList<>();

		for (int i = 0; i < getEdges().size(); i++) {			
			if (getEdges().get(i).getP1().equals(p)) {				
				vs.add(getEdges().get(i));
				
				//System.out.println("add: " + getEdges().get(i));
			}
		}

		return vs;
	}

	/**
	 * Returns the vector which is closest to the original vector counter anti
	 * clockwise.
	 * 
	 * @param v
	 *            the vector from which the closest vector is to be determined
	 * @return the closest vector to the specified one
	 */
	public Vector getClosestVector(Vector v) {
		ArrayList<Vector> vs = getVectorsPointingAway(v.getP2());
		ArrayList<Double> doubles = new ArrayList<>();
		for (Vector vec : vs) {
			doubles.add(v.angleTo(vec));
		}
		
		//System.out.println(Collections.min(doubles) + " | " + vs.get(doubles.indexOf(Collections.min(doubles))));
		
		return vs.get(doubles.indexOf(Collections.min(doubles)));
	}

	/**
	 * Returns all Faces contained in the original Face. The outer one is not
	 * being returned.
	 * 
	 * @return ArrayList of Faces
	 */
	public ArrayList<Face> decomposeFace() {
		generateComplementaryEdges();
		
//		for (int i = 0; i < getEdges().size(); i++) {
//			System.out.println(getEdges().get(i));
//		}

		ArrayList<Face> faces = new ArrayList<>();
		Vector ov = null;
		Vector v = null;
		ArrayList<Vector> vs = new ArrayList<>();

		while (getEdges().size() != 0) {
			if (vs.size() != 0) {
				vs.clear();
			}

			ov = getEdges().get(0);
			
			vs.add(ov);
			
			//System.out.println("ov: " + ov);
			
			v = getClosestVector(ov);
			
			//System.out.println("v: " + v);
			
			while (!ov.equals(v)) {
				vs.add(v);
				v = getClosestVector(v);
				
				//System.out.println("v: " + v);
			}

			Face f = new Face();

			for (int i = 0; i < vs.size(); i++) {
				f.getEdges().add(vs.get(i));
			}
			removeVectors(vs);
			faces.add(f);
			
			//System.out.println("added " + f);
		}

		//System.out.println("decomposed, deleting inf. face");

		ArrayList<Double> max = new ArrayList<>();
		
		for (int i = 0; i < faces.size(); i++) {
			max.add(faces.get(i).getArea());
		}
		
		// Inhalt des zu entfernenden Elements
		Double tbr = Collections.max(max);
		
		faces.remove(max.indexOf(tbr));
		
		//System.out.println("removed index: " + max.indexOf(tbr));
		
		return faces;
	}

	/**
	 * Removes all vectors given in the ArrayList from edges of the Face.
	 * 
	 * @param vs
	 *            vectors to be removed from edges
	 */
	private void removeVectors(ArrayList<Vector> vs) {
		for (Vector v : vs) {
			getEdges().remove(v);
		}
	}

	public String toString() {
		String s = "[";
		for (int i = 0; i < getEdges().size(); i++) {
			s = s.concat(getEdges().get(i).toString());
			if (i != getEdges().size() - 1) {
				s = s.concat(",");
			}
		}
		return s.concat("]");
	}

	/**
	 * Function that returns all points a face contains
	 * 
	 * @return all points contained in a face
	 */
	public ArrayList<Point> getPoints() {
		ArrayList<Point> p = new ArrayList<>();

		for (int i = 0; i < getEdges().size(); i++) {
			p.add(getEdges().get(i).getP1());
			p.add(getEdges().get(i).getP2());
		}

		//System.out.println("Duplicate: " + p);

		// p.clone anstatt p, da sonst als Pointer verwendet wird
		@SuppressWarnings("unchecked")
		ArrayList<Point> pts = (ArrayList<Point>) p.clone();

		// Entfernen von Duplikaten aus p
		for (Point pt : pts) {
			if (p.indexOf(pt) != p.lastIndexOf(pt)) {
				p.remove(p.lastIndexOf(pt));
			}
		}

		//System.out.println("Distinct: " + p);

		return p;
	}
	
	public Point getCOG(){ // Center of Gravity
		ArrayList<Point> points = getPoints();
		double xp = 0.0; 
		double yp = 0.0; 
		
		for (int i=0;i<points.size()-1;i++){
			xp += (points.get(i).getX()+points.get(i+1).getX())*(points.get(i).getX()*points.get(i+1).getY()-points.get(i+1).getX()*points.get(i).getY());
			yp += (points.get(i).getY()+points.get(i+1).getY())*(points.get(i).getX()*points.get(i+1).getY()-points.get(i+1).getX()*points.get(i).getY());
		}
		xp = xp*(1.0/(6.0*getArea()));
		yp = yp*(1.0/(6.0*getArea()));
		return (new Point(xp,yp));
	}

	/**
	 * Function that returns the area of a face using shoelace formula
	 * (Gausssche Trapezformel).
	 * 
	 * @return area of specific face
	 */
	public Double getArea() {
		ArrayList<Point> points = getPoints();

		Double area = 0.0;

		// bei leeren Elementen oder einem zu kleinen Face abbrechen
		if (points.get(0) == null || points.size() < 3) {
			return 0.0;
		}

		for (int i = 0; i < points.size(); i++) {
			area += (points.get(i).getY() + points.get((i + 1) % points.size()).getY())
					* (points.get(i).getX() - points.get((i + 1) % points.size()).getX());
		}

		return Math.abs(area / 2.0);
	}

}