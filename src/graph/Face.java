package graph;

import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.Generated;

import org.omg.Messaging.SyncScopeHelper;

public class Face {

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
	 * Returns an ArrayList of all vectors from the given ArrayList.
	 * 
	 * @param vs
	 *            ArrayList of vectors
	 * @return ArrayList of angles, corresponding to the vector ArrayList
	 */
	public static ArrayList<Double> anglesOfVectors(ArrayList<Vector> vs) {
		ArrayList<Double> angles = new ArrayList<>();

		Point p = getCommonPoint(vs);

		for (Vector v : vs) {
			double ang = 0.0;
//			if (v.getP1() == p) {
//				ang = Math.toDegrees(Math.atan2(p.getY() - v.getP2().getY(), p.getX() - v.getP2().getX()));
//			} else {
//				ang = Math.toDegrees(Math.atan2(p.getY() - v.getP1().getY(), p.getX() - v.getP1().getX()));
//			}
			ang = Math.toDegrees(Math.atan2(v.getP2().getY()-v.getP1().getY(), v.getP2().getX()-v.getP1().getX()));

//			if (ang == 0.0) {
//				angles.add(180.0);
//			} else if (ang == 180.0) {
//				angles.add(0.0);
//			} else {
			angles.add(ang);
//			}
		}

		return angles;
	}

	/**
	 * Returns an ArrayList of angles of all vectors on Point p.
	 * 
	 * @param p
	 *            the point with adjacent vectors
	 * @return ArrayList of angles
	 */
	public ArrayList<Double> anglesOfVectors(Point p) {
		ArrayList<Double> angles = new ArrayList<>();
		ArrayList<Vector> vs = new ArrayList<>();

		for (Vector v : this.getEdges()) {
			if (v.contains(p)) {
				vs.add(v);
			}
		}

		for (Vector v : vs) {
			double ang = 0.0;
			if (v.getP1() == p) {
				ang = Math.toDegrees(Math.atan2(p.getY() - v.getP2().getY(), p.getX() - v.getP2().getX()));
			} else {
				ang = Math.toDegrees(Math.atan2(p.getY() - v.getP1().getY(), p.getX() - v.getP1().getX()));
			}

			if (ang == 0.0 || ang == 180.0) {
				angles.add(ang);
			} else {
				angles.add(180.0 + ang);
			}
		}

		return angles;
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
				System.out.println("Error occured while determining common point");
				return null;
			} else {
				return p;
			}
		} else {
			return p;
		}

	}

	/**
	 * Returns all angles of the vectors of the ArrayList to the one specified
	 * by i.
	 * 
	 * @param vs
	 *            ArrayList of all vectors
	 * @param i
	 *            index of the vector to be compared
	 * @return ArrayList of all angles relative to the selected vector
	 */
	public static ArrayList<Double> angleBetweenVectors(ArrayList<Vector> vs, int i) {
		if (i < vs.size()) {
			ArrayList<Double> doubles = new ArrayList<>();
			Vector v = vs.get(i);

			doubles = Face.anglesOfVectors(vs);

			for (int j = 0; j < vs.size(); j++) {
				if (j != i) {
					if (doubles.get(i) - doubles.get(j) > 0) {
						doubles.set(j, doubles.get(i) - doubles.get(j));
					} else if (doubles.get(i) - doubles.get(j) != 0) {
						doubles.set(j, doubles.get(i) - doubles.get(j) + 360.0);
					} else {
						doubles.set(j, 360.0);
					}
				}
			}

			doubles.set(i, 360.0);

			return doubles;
		} else {
			return null;
		}
	}

	/**
	 * Generates complementary vectors for al vectors already existing
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
		ArrayList<Vector> vs = new ArrayList<>();

		for (int i = 0; i < getEdges().size(); i++) {
			if (getEdges().get(i).getP1() == p) {
				vs.add(getEdges().get(i));
			}
		}

		return vs;
	}

	/**
	 * Returns the vector which is closest to the original vector counter
	 * clockwise.
	 * 
	 * @param v
	 *            the vector from which the closest vector is to be determined
	 * @return the closest vector to the specified one
	 */
	public Vector getClosestVector(Vector v) {
		ArrayList<Vector> vs = getVectorsPointingAway(v.getP2());

		ArrayList<Double> doubles = Face.anglesOfVectors(vs);
		
		//System.out.println(doubles);

		int ind = 0;

		for (int i = 0; i < vs.size(); i++) {
			if (vs.get(i).equals(v.getComplementaryVector())) {
				ind = i;
			}
		}

		for (int i = 0; i < doubles.size(); i++) {
			if (i != ind) {
				doubles.set(i, (doubles.get(i) - doubles.get(ind)));
				// == 180.0 ? doubles.get(ind) - doubles.get(i) : doubles.get(ind) - doubles.get(i) + 180
			}
		}

		doubles.set(ind, 1337.0);
		System.out.println(doubles);

//		for (int i = 0; i < doubles.size(); i++) {
//			if (doubles.get(i) < 0) {
//				doubles.set(i, doubles.get(i) + 360.0);
//			}
//		}

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

		ArrayList<Face> faces = new ArrayList<>();
		Vector ov = null;
		Vector v = null;
		ArrayList<Vector> vs = new ArrayList<>();
		
		System.out.println(Math.toDegrees(Math.atan2(1, 0)) - Math.toDegrees(Math.atan2(1, 1)));

//		while (getEdges().size() != 0) {
//			if (vs.size() != 0) {
//				for (int i = 0; i < vs.size(); i++) {
//					vs.remove(i);
//				}
//			}
//
//			ov = getEdges().get(0);
//			//System.out.println("ov:" + ov);
//			vs.add(ov);
//			v = getClosestVector(ov);
//
//			int x = 0;
//			while (!ov.equals(v)) {
//				x++;
//				vs.add(v);
//				//System.out.println(v);
//				v = getClosestVector(v);
//				
//				if (x > 100) {
//					break;
//				}
//			}
//
//			Face f = new Face();
//
//			for (int i = 0; i < vs.size(); i++) {
//				f.getEdges().add(vs.get(i));
//			}
//			
//			removeVectors(vs);
//			faces.add(f);
//			System.out.println("added");
//		}

		System.out.println("returning");
		return faces;
	}

	/**
	 * Removes all vectors given in the ArrayList from edges of the Face.
	 * 
	 * @param vs vectors to be removed from edges
	 */
	private void removeVectors(ArrayList<Vector> vs) {
		for (Vector v : vs) {
			getEdges().remove(v);
		}
	}

}
