package graph;

import java.util.ArrayList;

import render.*;

public class Graph {

	// list of Faces used for decomposition
	private ArrayList<Face> faces = new ArrayList<>();
	// list of Nodes of Graph
	private ArrayList<Node> nodes = new ArrayList<>();
	// list of Edges of Graph
	private ArrayList<Edge> edges = new ArrayList<>();
	// maximum printing width
	private double maxPrintWidth;

	/**
	 * Constructor of the Graph class.
	 * 
	 * @param ls
	 *            the ArrayList of Lines
	 * @param params
	 *            the Params object used for this Graph
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
	 * Creates Nodes and Edges according to the given Lines.
	 * 
	 * @param ls
	 *            the ArrayList of given Lines
	 */
	private void processData(ArrayList<Line> ls) {
		int i = 0;

//		do {
//			if (ls.get(i).toVector().getLength() > maxPrintWidth) {
//				ls.add(new Line(ls.get(i).getP1(), ls.get(i).getP1().add(ls.get(i).toVector().multiply(0.5))));
//				ls.add(new Line(ls.get(i).getP2(), ls.get(i).getP2().add(ls.get(i).toVector().multiply(-0.5))));
//				ls.remove(ls.get(i));
//			} else {
//				i += 1;
//			}
//		} while (i != ls.size() - 1);

		// create nodes and egdes
		for (Line l : ls) {
			Node n1 = createNode(l.getP1());
			Node n2 = createNode(l.getP2());
			edges.add(new Edge(n1, n2));
		}
	}

	/**
	 * Creates a Node at a certain point.
	 * 
	 * @param p
	 *            the Vector pointing towards the point
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
	 * Generates the twin Edges for all Edges within the Graph.
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
	 * Completes the setting of Edges within the Graph.
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
	 * Completes the setting of Faces within the Graph.
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
	 * Completes the setting of Nodes within the Graph.
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
	 * Sorts the adjacent Edges of all Edges within the Graph using their angle.
	 * 
	 * @param ArrayList
	 *            of ArrayList containing all adjacent Edges
	 */
	private void sortEdges(ArrayList<ArrayList<Edge>> e) {
		ArrayList<Edge> sortedEdges = new ArrayList<>();
		
		for (ArrayList<Edge> eList : e) {
			sortedEdges.clear();
			for (Edge edg : eList) {
				int index = 0;
				for(int i = 0; i < sortedEdges.size(); i++){
					if(sortedEdges.get(i).toVector().angle() > edg.toVector().angle()){
						index = i;
						break;
					}
					if(i == sortedEdges.size() - 1){
						index = i + 1;
					}
				}
					
				if (index != sortedEdges.size()){
					sortedEdges.add(index, edg);
				} else {
					sortedEdges.add(edg);
				}
					
				
			}
			eList = (ArrayList<Edge>) sortedEdges.clone();
			
		}
	}

	// getter und setter
	/**
	 * Returns an ArrayList of all Nodes within the Graph.
	 * 
	 * @return ArrayList of Nodes
	 */
	public ArrayList<Node> getNodes() {
		return nodes;
	}

	/**
	 * Returns an ArrayList of all Edges within the Graph.
	 * 
	 * @return ArrayList of Edges
	 */
	public ArrayList<Edge> getEdges() {
		return edges;
	}

	/**
	 * Returns an ArrayList containing all Faces of the Graph.
	 * 
	 * @return ArrayList of Faces
	 */
	public ArrayList<Face> getFaces() {
		return faces;
	}

	/**
	 * Returns an ArrayList of all Edges pointing away from the specified Node.
	 * 
	 * @param n
	 *            the specified Node
	 * @return ArrayList of Edges pointing away
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
	 * Returns the Node at a certain point.
	 * 
	 * @param v
	 *            Vector to the point
	 * @return Node of the point
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
