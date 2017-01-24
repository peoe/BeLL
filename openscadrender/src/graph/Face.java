//package graph;
//
//import java.util.ArrayList;
//import java.util.Collections;
//
//public class Face {
//
////	public static int NONE = -1;
////	public static int P1 = 1;
////	public static int P2 = 2;
////	public static int BOTH = 0;
//
//	private ArrayList<Vector> edges;
////	private ArrayList<Point> intersections = new ArrayList<>();
////	private ArrayList<Point> points = new ArrayList<>();
////	private ArrayList<Integer> quan = new ArrayList<>();
//
//	public Face() {
//		setEdges(new ArrayList<>());
//	}
//
//	public ArrayList<Vector> getEdges() {
//		return edges;
//	}
//
//	public void setEdges(ArrayList<Vector> edges) {
//		this.edges = edges;
//	}
//
////	public void markQuantities() {
////		for (int j = 0; j < getEdges().size(); j++) {
////			if (!points.contains(getEdges().get(j).getP1())) {
////				points.add(getEdges().get(j).getP1());
////				quan.add(new Integer(0));
////				// System.out.println("p1" + v.getP1().toString());
////			}
////			if (!points.contains(getEdges().get(j).getP2())) {
////				points.add(getEdges().get(j).getP2());
////				quan.add(new Integer(0));
////				// System.out.println("p2" + v.getP2().toString());
////			}
////			if (points.contains(getEdges().get(j).getP1())) {
////				for (int i = 0; i < points.size(); i++) {
//////					 System.out.println(getEdges().get(j).getP1() + " - " +
//////					 points.get(i));
////					if (getEdges().get(j).getP1() == points.get(i)) {
////						quan.set(i, quan.get(i) + 1);
////						// System.out.println(getEdges().get(j).getP1().toString()
////						// + "," + quan.get(i));
////						break;
////					}
////				}
////			}
////			if (points.contains(getEdges().get(j).getP2())) {
////				for (int i = 0; i < points.size(); i++) {
//////					 System.out.println(getEdges().get(j).getP2() + " - " +
//////					 points.get(i));
////					if (getEdges().get(j).getP2() == points.get(i)) {
////						quan.set(i, quan.get(i) + 1);
//////						 System.out.println(getEdges().get(j).getP2().toString()
//////						 + "," + quan.get(i));
////						break;
////					}
////				}
////			}
////		}
////
////		for (int i = 0; i < points.size(); i++) {
////			if (quan.get(i) > 2 && points.get(i) != null) {
////				intersections.add(new Point(points.get(i).getX(), points.get(i).getY()));
////			}
////		}
////	}
//
//	public ArrayList<Vector> getVectorsByPoint(Point p) {
//		ArrayList<Vector> vs = new ArrayList<>();
//
//		for (Vector v : getEdges()) {
//			if (v.contains(p)) {
//				vs.add(v);
//			}
//		}
//
//		return vs;
//	}
//
//	public static ArrayList<Double> anglesOfVectors(ArrayList<Vector> vs) {
//		ArrayList<Double> angles = new ArrayList<>();
//
//		Point p = getCommonPoint(vs);
//
//		for (Vector v : vs) {
//			double ang = 0.0;
//			if (v.getP1() == p) {
//				ang = Math.toDegrees(Math.atan2(p.getY() - v.getP2().getY(), p.getX() - v.getP2().getX()));
//			} else {
//				ang = Math.toDegrees(Math.atan2(p.getY() - v.getP1().getY(), p.getX() - v.getP1().getX()));
//			}
//
//			// ang = Math.toDegrees(Math.atan2(v.getDifferenceY(),
//			// v.getDifferenceX()));
//			//
//			// System.out.println(p - v.getDifferenceX() + "," +
//			// v.getDifferenceY());
//			
//			if (ang == 0.0 || ang == 180.0) {
//				angles.add(ang);
//			} else {
//				angles.add(180.0 + ang);
//			}
//		}
//
//		//System.out.println(angles);
//		
//		return angles;
//	}
//	
//	public ArrayList<Double> anglesOfVectors(Point p) {
//		ArrayList<Double> angles = new ArrayList<>();
//		ArrayList<Vector> vs = new ArrayList<>();
//		
//		for (Vector v : this.getEdges()) {
//			if (v.contains(p)) {
//				vs.add(v);
//			}
//		}
//
//		for (Vector v : vs) {
//			double ang = 0.0;
//			if (v.getP1() == p) {
//				ang = Math.toDegrees(Math.atan2(p.getY() - v.getP2().getY(), p.getX() - v.getP2().getX()));
//			} else {
//				ang = Math.toDegrees(Math.atan2(p.getY() - v.getP1().getY(), p.getX() - v.getP1().getX()));
//			}
//
//			// ang = Math.toDegrees(Math.atan2(v.getDifferenceY(),
//			// v.getDifferenceX()));
//			//
//			// System.out.println(p - v.getDifferenceX() + "," +
//			// v.getDifferenceY());
//			
//			if (ang == 0.0 || ang == 180.0) {
//				angles.add(ang);
//			} else {
//				angles.add(180.0 + ang);
//			}
//		}
//
//		return angles;
//	}
//
//	public static Point getCommonPoint(ArrayList<Vector> vs) {
//		boolean done = false;
//
//		Point p = vs.get(0).getP1();
//
//		for (Vector v : vs) {
//			if (v.contains(p)) {
//				done = true;
//			} else {
//				done = false;
//			}
//		}
//
//		if (!done) {
//			p = vs.get(0).getP2();
//
//			for (Vector v : vs) {
//				if (v.contains(p)) {
//					done = true;
//				} else {
//					done = false;
//				}
//			}
//
//			if (!done) {
//				System.out.println("Error occured while determining common point");
//				return null;
//			} else {
//				return p;
//			}
//		} else {
//			return p;
//		}
//
//	}
//	
//	public static ArrayList<Double> angleBetweenVectors (ArrayList<Vector> vs, int i) {
//		if (i < vs.size()) {
//			ArrayList<Double> doubles = new ArrayList<>();
//			Vector v = vs.get(i);
//			
//			doubles = Face.anglesOfVectors(vs);
//			
//			for (int j = 0; j < vs.size(); j++) {
//				if (j != i) {
//					if (doubles.get(i) - doubles.get(j) > 0) {
//						doubles.set(j, doubles.get(i) - doubles.get(j));
//					} else if (doubles.get(i) - doubles.get(j) != 0) {
//						doubles.set(j, doubles.get(i) - doubles.get(j) + 360.0);
//					}
//					
//				}
//			}
//			
//			doubles.set(i, 360.0);
//			
//			return doubles;
//		} else {
//			return null;
//		}
//	}
//	
//	public void generateComplementaryEdges() {
//		int amount = getEdges().size();
//		for (int i = 0; i < amount; i++) {
//			getEdges().add(getEdges().get(i).getComplementaryVector());
//		}
//	}
//
////	public ArrayList<Point> getPoints() {
////		return points;
////	}
////
////	public void setPoints(ArrayList<Point> points) {
////		this.points = points;
////	}
////
////	public ArrayList<Integer> getQuan() {
////		return quan;
////	}
////
////	public void setQuan(ArrayList<Integer> quan) {
////		this.quan = quan;
////	}
//	
//	public ArrayList<Vector> getVectorsPointingAway (Point p) {
//		ArrayList<Vector> vs = new ArrayList<>();
//		
//		for (int i = 0; i < getEdges().size(); i++) {
//			if (getEdges().get(i).getP1() == p) {
//				vs.add(getEdges().get(i));
//			}
//		}
//		
//		return vs;
//	}
//	
//	public Vector getNearestVector (Vector v) {
//		ArrayList<Vector> vs = getVectorsPointingAway(v.getP2());
//		
//		ArrayList<Double> doubles = Face.anglesOfVectors(vs);
//		
//		return vs.get(doubles.indexOf(Collections.min(doubles)));
//	}
//	
//	public ArrayList<Face> decomposeFace () {
//		generateComplementaryEdges();
//		
//		ArrayList<Face> faces = new ArrayList<>(); 
//		Vector ov = getEdges().get(0);
//		ArrayList<Vector> vs = getVectorsByPoint(ov.getP1());
//		Vector v = getNearestVector(ov);
//		System.out.println(v);
//		
//		return null;
//	}
//
//}
