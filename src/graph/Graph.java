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
	
	//getter und setter
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
	
	/**
	 * Constructor of Graph class which creates DCEL
	 * @param ls
	 * 
	 */
	public Graph(ArrayList<Line> ls) {
		processData(ls);
		computeTwins();
		completeEdges();
		completeFaces();
		completeNodes();
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
			Node n1 = createNode(l.getP1());
			Node n2 = createNode(l.getP2());
			edges.add(new Edge(n1, n2));
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
			if (n.getOrigin().equals(p)) {
				return n;
			}
		}
		nodes.add(new Node(p));
		return (nodes.get(nodes.size() - 1));
	}

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

	/**
	 * finishes all Edges in DCEL
	 */
	private void completeEdges() {
		ArrayList<ArrayList<Edge>> nodeEdges = new ArrayList<>();
		for (int i = 0; i < nodes.size(); i++) {
			nodeEdges.add(new ArrayList<Edge>());
		}
		for (Edge e : edges) {
			nodeEdges.get(nodes.indexOf(e.getN1())).add(e);
		}

		sortEdges(nodeEdges);

		for (ArrayList<Edge> e : nodeEdges) {
			for (int i = 0; i < e.size(); i++) {
				e.get(i).setPrev(e.get((i + 1) % e.size()).getTwin());
				e.get(i).getTwin().setNext(e.get(Math.floorMod((i - 1), e.size())));
			}

		}
	}
	/**
	 * finsihes faces in DCEL
	 */
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
	}
	/**
	 * finishes nodes in DCEL (adds incident edges)
	 */
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

	/**
	 * 
	 * @param ArrayList of ArrayList of Edge e (adjacentEdges of nodes) 
	 * 
	 * @return sorted adjacentEdges of nodes
	 */
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
	//test
	public ScadObject outputCorners(){
		ArrayList<ScadObject> objectList = new ArrayList<>();
		for(Node n : nodes){
			objectList.add(new Translate(new Corner(n),n.getOrigin(),0));
		}
		for(Edge e : edges){
			objectList.add(new Wall(e));
		}
		for(Face f: faces){
			if(f.getArea()>0.0){
			objectList.add(new BasePlate(f));
			}
		}
		return (new Union(objectList));
		
	}
	//prints one face with corners
	public ScadObject printFace(Face f){
		ArrayList<ScadObject> objectList = new ArrayList<>();
		for(Node n : f.getNodes()){
			objectList.add(new Translate(new Corner(n),n.getOrigin(),0));
		}
		objectList.add(new BasePlate(f));
		return new Union(objectList);
	}
	
	/**
	 * 
	 * @param v Vector
	 * @return Node with Origin = v and incidentEdge = null
	 */
	public Node getNodeByPoint(Vector v) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getOrigin() == v) {
				return nodes.get(i);
			}
		}
		return null;
	}

}
