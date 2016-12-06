package graph;

import java.util.ArrayList;

public class Face {

	public static int NONE = -1;
	public static int P1 = 1;
	public static int P2 = 2;
	public static int BOTH = 0;

	private ArrayList<Vector> edges;
	private ArrayList<Point> intersections = new ArrayList<>();
	private ArrayList<Point> points = new ArrayList<>();
	private ArrayList<Integer> quan = new ArrayList<>();

	public Face() {
		setEdges(new ArrayList<>());
	}

	public ArrayList<Vector> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Vector> edges) {
		this.edges = edges;
	}

	public ArrayList<Point> getIntersections() {
		return intersections;
	}

	public void markQuantities() {
		for (int j = 0; j < getEdges().size(); j++) {
			if (!points.contains(getEdges().get(j).getP1())) {
				points.add(getEdges().get(j).getP1());
				quan.add(new Integer(0));
				//System.out.println("p1" + v.getP1().toString());
			}
			if (!points.contains(getEdges().get(j).getP2())) {
				points.add(getEdges().get(j).getP2());
				quan.add(new Integer(0));
				//System.out.println("p2" + v.getP2().toString());
			}
			if (points.contains(getEdges().get(j).getP1())) {
				for (int i = 0; i < points.size(); i++) {
					//System.out.println(getEdges().get(j).getP1() + " - " + points.get(i));
					if (getEdges().get(j).getP1() == points.get(i)) {
						quan.set(i, quan.get(i) + 1);
						//System.out.println(getEdges().get(j).getP1().toString() + "," + quan.get(i));
						break;
					}
				}
			}
			if (points.contains(getEdges().get(j).getP2())) {
				for (int i = 0; i < points.size(); i++) {
					//System.out.println(getEdges().get(j).getP2() + " - " + points.get(i));
					if (getEdges().get(j).getP2() == points.get(i)) {
						quan.set(i, quan.get(i) + 1);
						//System.out.println(getEdges().get(j).getP2().toString() + "," + quan.get(i));
						break;
					}
				}
			}
		}

		for (int i = 0; i < points.size(); i++) {
			if (quan.get(i) > 2 && points.get(i) != null) {
				getIntersections().add(new Point(points.get(i).getX(), points.get(i).getY()));
			}
		}
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

	public ArrayList<Integer> getQuan() {
		return quan;
	}

	public void setQuan(ArrayList<Integer> quan) {
		this.quan = quan;
	}

}
