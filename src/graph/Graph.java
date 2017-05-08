package graph;

import java.util.ArrayList;
import java.util.Collections;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import render.*;
import render.objects.*;

public class Graph {

	// list of faces used for decomposition
	private ArrayList<Face> faces = new ArrayList<>();
	// list of points
	private ArrayList<Node> nodes = new ArrayList<>();
	// list of edges
	private ArrayList<Edge> edges = new ArrayList<>();

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	// getter for faces list
	/**
	 * Returns the ArrayList containing all faces of the graph.
	 * 
	 * @return ArrayList of the graphs faces
	 */
	public ArrayList<Face> getFaces() {
		return faces;
	}

	// // list of faces used for decomposition
	// private ArrayList<Face> faces = new ArrayList<>();
	// // list of points
	// private ArrayList<Vector> points = new ArrayList<>();
	// // list of lines
	// private ArrayList<Line> lines = new ArrayList<>();
	//
	// // infinte Face
	// private Face infFace = new Face();
	//
	// // constructor takes list of lines, decomposes, generates pts
	// /**
	// * Constructor for the Graph class. Takes an ArrayList of lines to
	// generate
	// * all contained faces and points.
	// *
	// * @see Face, Line
	// *
	// * @param vs
	// * ArrayList of lines
	// */
	public Graph(ArrayList<Line> ls) {
		processData(ls);
		computeTwins();
		completeEdges();
		completeFaces();
		completeNodes();
		// computeEdges(ls);
		// computeComplementaryLines();
		// computePoints();
		// cloneLines();
		// computeFaces();
	}

	// constructor for cloning graphs
	/**
	 * Constructor used for the purpose of cloning graphs.
	 */
	private Graph() {
	}

	// Creates Nodes and Edges out of the given Line ArrayList
	/**
	 * Creates Nodes and Edges out of the given Lines and stores them in the
	 * specific field in the graph class
	 * 
	 * @param ls
	 *            the list of lines
	 */
	private void processData(ArrayList<Line> ls) {
		for (Line l : ls) {
			edges.add(new Edge(createNode(l.getP1()), createNode(l.getP2())));
		}

	}

	// function for creating a node using a point if this specific node hasn't
	// been created yet
	/**
	 * Creates a node out of the given point if this node does not already exist
	 * in the Graph class
	 * 
	 * @param p
	 *            Point which needs to be converted into node
	 */
	private Node createNode(Vector p) {
		for (Node n : nodes) {
			if (n.getOrigin() == p) {
				return n;
			}
		}
		nodes.add(new Node(p));
		return (nodes.get(nodes.size() - 1));
	}

	// function for adding all given lines to the lines list
	/**
	 * Adds all lines from the given ArrayList to the list of lines belonging to
	 * the graph.
	 * 
	 * @param ls
	 *            the ArrayList containing all lines to be added
	 */
//	private void computeEdges(ArrayList<Line> ls) {
//		// adding all lines
//		for (int i = 0; i < ls.size(); i++) {
//			getLines().add(ls.get(i));
//		}
//	}

	// function for generating complementary lines
	/**
	 * Generates the complementary lines for the entirety of the already
	 * existing lines.
	 */
	private void computeTwins() {
		// getting the amount of lines to be added (size of getLines is going to
		// be changed -> needs to be saved for later reuse)
		int amount = edges.size();

		// adding the complementary lines
		for (int i = 0; i < amount; i++) {
			edges.add(edges.get(i).generateTwin());
			edges.get(i).setTwin(edges.get(edges.size() - 1));
			edges.get(edges.size() - 1).setTwin(edges.get(i));
			;
		}
	}

	private void completeEdges() {
		System.out.println(nodes.size());
		ArrayList<ArrayList<Edge>> nodeEdges = new ArrayList<>();
		for (int i = 0; i < nodes.size(); i++) {
			nodeEdges.add(new ArrayList<Edge>());
		}
		System.out.println(nodeEdges.size());
		for (Edge e : edges) {
			nodeEdges.get(nodes.indexOf(e.getN1())).add(e);
		}

		sortEdges(nodeEdges);

		System.out.println(nodes);
		System.out.println(nodeEdges);

		for (ArrayList<Edge> e : nodeEdges) {
			for (int i = 0; i < e.size(); i++) {
				e.get(i).setPrev(e.get((i + 1) % e.size()).getTwin());
				System.out.println(e.size());
				System.out.println(Math.floorMod((i - 1), e.size()));
				e.get(i).getTwin().setNext(e.get(Math.floorMod((i - 1), e.size())));
			}

		}
		// setPointers(e);
		//
		// System.out.println(e);
		// System.out.println(e);
		// System.out.println(getClosestEdge(e));
		// System.out.println(getFurthestEdge(e));
		// System.out.println("\n");

	}

	private void completeFaces() {

		ArrayList<Boolean> edgeStatus = new ArrayList<>();
		for (int i = 0; i < edges.size(); i++) {
			edgeStatus.add(false);
		}
		Edge e, originalEdge;
		while (edgeStatus.contains(false)) {
			originalEdge = edges.get(edgeStatus.indexOf(false));
			faces.add(new Face(originalEdge));
			originalEdge.setFace(faces.get(faces.size() - 1));
			edgeStatus.set(edges.indexOf(originalEdge), true);
			e = originalEdge.getNext();
			while (e != originalEdge) {
				edgeStatus.set(edges.indexOf(e), true);
				e.setFace(faces.get(faces.size() - 1));
				e = e.getNext();
			}
		}
		for (Face f : faces)
			System.out.println("test:" + f.getIncidentEdge().getFaceEdges() + " area: " + f.getIncidentEdge().getFace().getArea());

	}
	
	public void completeNodes(){
		for(Node n : nodes){
			for(Edge e: edges){
				if(n==e.getN1()){
					n.setIncidentEdge(e);
					break;
				}
			}
		}
	}

	private void sortEdges(ArrayList<ArrayList<Edge>> e) {
		// getAngles
		ArrayList<Double> doubles = new ArrayList<>();
		for (ArrayList<Edge> eList : e) {
			doubles.clear();
			for (Edge edg : eList) {
				doubles.add(edg.toVector().angle());
				double temp = 0.0;
				Edge tempEdg = null;
				for (int i = 0; i < doubles.size() - 1; i++) {
					for (int j = i + 1; j < doubles.size(); j++) {
						if (doubles.get(i) > doubles.get(j)) {
							temp = doubles.get(i);
							doubles.set(i, doubles.get(j));
							doubles.set(j, temp);
							tempEdg = eList.get(i);
							eList.set(i, eList.get(j));
							eList.set(j, tempEdg);
						}
					}
				}
			}
		}

	}

	// function for computing all the points contained in the graph
	/**
	 * Adds all points contained in the graph to the list of points.
	 */
//	private void computePoints() {
//		ArrayList<Vector> p = new ArrayList<>();
//
//		// adding all points
//		for (int i = 0; i < getLines().size(); i++) {
//			p.add(getLines().get(i).getP1());
//			p.add(getLines().get(i).getP2());
//		}
//
//		// p.clone instead of p to avoid using pointers
//		@SuppressWarnings("unchecked")
//		ArrayList<Vector> pts = (ArrayList<Vector>) p.clone();
//
//		// removing of duplicates
//		for (Vector pt : pts) {
//			if (p.indexOf(pt) != p.lastIndexOf(pt)) {
//				p.remove(p.lastIndexOf(pt));
//			}
//		}
//
//		// adding all points to the point list
//		for (int i = 0; i < p.size(); i++) {
//			getPoints().add(p.get(i));
//		}
//	}

	// function for getting all the lines pointing away from a certain node
	/**
	 * Returns an ArrayList of all lines pointing away from the specified point.
	 * 
	 * @param n
	 *            the specified node
	 * @return ArrayList of edges pointing away from the given node
	 */
	public ArrayList<Edge> getEdgesPointingAway(Node n) {
		ArrayList<Edge> edgs = new ArrayList<>();

		// adding all edges where the starting node is equal to the given node
		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).getN1() == n) {
				edgs.add(edges.get(i));
			}
		}

		return edgs;
	}

	// function for calculating the closest line
	/**
	 * Returns the line that is closest to the specified line.
	 * 
	 * @param e
	 *            the specified line
	 * @return the line closest to the given line
	 */
//	private Edge getClosestEdge(Edge e) {
//		ArrayList<Edge> edgs = getEdgesPointingAway(e.getN2());
//		ArrayList<Double> doubles = new ArrayList<>();
//
//		// calculating all angles
//		for (Edge edg : edgs) {
//			doubles.add(e.angleTo(edg, true));
//		}
//
//		// returning only the line with the smallest angle
//		return edgs.get(doubles.indexOf(Collections.min(doubles)));
//	}

	// function for cloning the list containing all lines
	/**
	 * Returns an identical copy of the lines list.
	 * 
	 * @return a copy of the lines list
	 */
//	private ArrayList<Line> cloneLines() {
//		ArrayList<Line> ls = new ArrayList<>();
//
//		// cloning all elements of the lines list
//		for (int i = 0; i < getLines().size(); i++) {
//			ls.add(getLines().get(i));
//		}
//
//		return ls;
//	}

	// function for removing the specified lines from the specified list
	/**
	 * Removes all specified lines from the target list.
	 * 
	 * @param target
	 *            the list being targeted
	 * @param lines
	 *            the list of lines to be removed
	 * @return target list without the specified lines
	 */
//	private ArrayList<Line> removeVectors(ArrayList<Line> target, ArrayList<Line> lines) {
//		// remove all lines in 'lines' from 'target'
//		for (Line l : lines) {
//			target.remove(l);
//		}
//
//		// returning the target list
//		return target;
//	}

	// function for dissecting/decomposing the graph into its individual faces
	/**
	 * Computes all the faces contained in the graph and adds them to the list
	 * of faces.
	 * 
	 * @see Face
	 */
//	private void computeFaces() {
//		Line ol = null;
//		Line l = null;
//		ArrayList<Line> ols = cloneLines();
//		ArrayList<Line> ls = new ArrayList<>();
//
//		// continue while there are still lines to be checked
//		while (ols.size() != 0) {
//			// this is where the magic happens
//			if (ls.size() != 0) {
//				ls.clear();
//			}
//
//			ol = ols.get(0);
//			ls.add(ol);
//			l = getClosestEdge(ol);
//
//			while (!ol.equals(l)) {
//				ls.add(l);
//				l = getClosestEdge(l);
//			}
//
//			Face f = new Face();
//
//			for (int i = 0; i < ls.size(); i++) {
//				f.getEdges().add(ls.get(i));
//			}
//
//			removeVectors(ols, ls);
//			faces.add(f);
//		}
//
//		ArrayList<Double> max = new ArrayList<>();
//
//		for (int i = 0; i < faces.size(); i++) {
//			max.add(faces.get(i).getArea());
//		}
//
//		// area of the Face to be removed
//		Double tbr = Collections.max(max);
//
//		// cloning the infinite Face before removing it
//		infFace = faces.get(max.indexOf(tbr)).clone();
//
//		faces.remove(max.indexOf(tbr));
//	}

	// getter for points list
	/**
	 * Returns the ArrayList containing all points of the graph.
	 * 
	 * @return ArrayList of the graphs points
//	 */
//	public ArrayList<Vector> getPoints() {
//		return points;
//	}

	// getter for lines list
	/**
	 * Returns the ArrayList containing all lines of the graph.
	 * 
	 * @return ArrayList of the graphs lines
	 */
//	public ArrayList<Line> getLines() {
//		return lines;
//	}
//
//	public Face getInfiniteFace() {
//		if (infFace != null) {
//			return infFace;
//		} else {
//			return new Face();
//		}
//	}

	// function for cloning the graph
	/**
	 * Returns an exact copy of the graph.
	 */
//	public Graph clone() {
//		Graph g = new Graph();
//
//		// adding all faces
//		for (int i = 0; i < getFaces().size(); i++) {
//			g.getFaces().add(getFaces().get(i));
//		}
//
//		// adding all points
//		for (int i = 0; i < getPoints().size(); i++) {
//			g.getPoints().add(getPoints().get(i));
//		}
//
//		// adding all lines
//		for (int i = 0; i < getLines().size(); i++) {
//			g.getLines().add(getLines().get(i));
//		}
//
//		// returning fully cloned graph
//		return g;
//	}
	
	public ScadObject outputCorners(){
		ArrayList<ScadObject> objectList = new ArrayList<>();
		for(Node n : nodes){
			objectList.add(new Translate(new Corner(n),n.getOrigin(),0));
		}
		for(Edge e : edges){
			objectList.add(new Wall(e).getPresentationWall());
		}
		return (new Union(objectList));
		
	}

	public Node getNodeByPoint(Vector v) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getOrigin() == v) {
				return nodes.get(i);
			}
		}
		return null;
	}

}
