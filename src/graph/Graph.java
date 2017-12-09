package graph;

import java.util.ArrayList;

import render.*;
import render.objects.*;

public class Graph {

	// list of faces used for decomposition
	private ArrayList<Face> faces = new ArrayList<>();
	// list of nodes of graph
	private ArrayList<Node> nodes = new ArrayList<>();
	// list of edges of graph
	private ArrayList<Edge> edges = new ArrayList<>();
	// maximum printing width
	private double maxPrintWidth;

	/**
	 * Constructor of the Graph class.
	 * 
	 * @param ls
	 *            the ArrayList of lines
	 * @param params
	 *            the params object used for this graph
	 */
	public Graph(ArrayList<Line> ls, Params params) {
		// set maximum printing width
		maxPrintWidth = params.getMaxPrintWidth();

		// create edges from lines
		processData(ls);
		// create twin edges
		computeTwins();

		completeEdges();
		completeFaces();
		completeNodes();
	}

	/**
	 * Creates nodes and edges according to the given lines.
	 * 
	 * @param ls
	 *            the ArrayList of given lines
	 */
	private void processData(ArrayList<Line> ls) {
		int i = 0;

		do {
			if (ls.get(i).toVector().getLength() > maxPrintWidth) {
				ls.add(new Line(ls.get(i).getP1(), ls.get(i).getP1().add(ls.get(i).toVector().multiply(0.5))));
				ls.add(new Line(ls.get(i).getP2(), ls.get(i).getP2().add(ls.get(i).toVector().multiply(-0.5))));
				ls.remove(ls.get(i));
			} else {
				i += 1;
			}
		} while (i != ls.size() - 1);

		// create nodes and egdes
		for (Line l : ls) {
			Node n1 = createNode(l.getP1());
			Node n2 = createNode(l.getP2());
			edges.add(new Edge(n1, n2));
		}
	}

	/**
	 * Creates a node at a certain point.
	 * 
	 * @param p
	 *            the vector pointing towards the point
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

	/**
	 * Generates the twin edges for all edges within the graph.
	 */
	private void computeTwins() {
		// getting original amount of edges
		int amount = edges.size();

		// add twin edges
		for (int i = 0; i < amount; i++) {
			edges.add(edges.get(i).generateTwin());
			edges.get(i).setTwin(edges.get(edges.size() - 1));
			edges.get(edges.size() - 1).setTwin(edges.get(i));
			;
		}
	}

	/**
	 * Completes the setting of edges within the graph.
	 */
	private void completeEdges() {
		ArrayList<ArrayList<Edge>> nodeEdges = new ArrayList<>();

		// add all edges within the graph
		for (int i = 0; i < nodes.size(); i++) {
			nodeEdges.add(new ArrayList<Edge>());
		}

		for (Edge e : edges) {
			nodeEdges.get(nodes.indexOf(e.getN1())).add(e);
		}

		// sort all edges
		sortEdges(nodeEdges);

		for (ArrayList<Edge> e : nodeEdges) {
			for (int i = 0; i < e.size(); i++) {
				e.get(i).setPrev(e.get((i + 1) % e.size()).getTwin());
				e.get(i).getTwin().setNext(e.get(Math.floorMod((i - 1), e.size())));
			}
		}
	}

	/**
	 * Completes the setting of faces within the graph.
	 */
	private void completeFaces() {
		// set all statuses to false
		ArrayList<Boolean> edgeStatus = new ArrayList<>();

		for (int i = 0; i < edges.size(); i++) {
			edgeStatus.add(false);
		}

		Edge e, originalEdge;

		// complete all faces
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
	 * Completes the setting of nodes within the graph.
	 */
	public void completeNodes() {
		for (Node n : nodes) {
			for (Edge e : edges) {
				if (n == e.getN1()) {
					n.setIncidentEdge(e);
					break;
				}
			}
		}
	}

	/**
	 * Sorts all adjacent edges of all edges within the graph via their angle.
	 * 
	 * @param ArrayList
	 *            of ArrayList containing all adjacent edges
	 */
	private void sortEdges(ArrayList<ArrayList<Edge>> e) {
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

	// getter und setter
	/**
	 * Returns an ArrayList of all nodes within the Graph.
	 * 
	 * @return ArrayList of nodes
	 */
	public ArrayList<Node> getNodes() {
		return nodes;
	}

	/**
	 * Returns an ArrayList of all edges within the Graph.
	 * 
	 * @return ArrayList of edges
	 */
	public ArrayList<Edge> getEdges() {
		return edges;
	}

	/**
	 * Returns an ArrayList containing all faces of the graph.
	 * 
	 * @return ArrayList of faces
	 */
	public ArrayList<Face> getFaces() {
		return faces;
	}

	/**
	 * Returns an ArrayList of all edges pointing away from the specified node.
	 * 
	 * @param n
	 *            the specified node
	 * @return ArrayList of edges pointing away
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

	/**
	 * Returns the node at a certain point.
	 * 
	 * @param v
	 *            vector to the point
	 * @return node of the point
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
