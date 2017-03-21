package graph;

import java.util.ArrayList;
import java.util.Collections;

public class Graph {

	// list of faces used for decomposition
	private ArrayList<Face> faces = new ArrayList<>();
	// list of points
	private ArrayList<Vector> points = new ArrayList<>();
	// list of lines
	private ArrayList<Line> lines = new ArrayList<>();
	
	// infinte Face
	private Face infFace = new Face();

	// constructor takes list of lines, decomposes, generates pts
	/**
	 * Constructor for the Graph class. Takes an ArrayList of lines to generate
	 * all contained faces and points.
	 * 
	 * @see Face, Line
	 * 
	 * @param vs
	 *            ArrayList of lines
	 */
	public Graph(ArrayList<Line> ls) {
		computeEdges(ls);
		computeComplementaryLines();
		computePoints();
		cloneLines();
		computeFaces();
	}
	
	// constructor for cloning graphs
	/**
	 * Constructor used for the purpose of cloning graphs.
	 */
	private Graph() {
	}

	// function for adding all given lines to the lines list
	/**
	 * Adds all lines from the given ArrayList to the list of lines belonging to
	 * the graph.
	 * 
	 * @param ls
	 *            the ArrayList containing all lines to be added
	 */
	private void computeEdges(ArrayList<Line> ls) {
		// adding all lines
		for (int i = 0; i < ls.size(); i++) {
			getLines().add(ls.get(i));
		}
	}

	// function for generating complementary lines
	/**
	 * Generates the complementary lines for the entirety of the already
	 * existing lines.
	 */
	private void computeComplementaryLines() {
		// getting the amount of lines to be added (size of getLines is going to be changed -> needs to be saved for later reuse)
		int amount = getLines().size();

		// adding the complementary lines
		for (int i = 0; i < amount; i++) {
			getLines().add(getLines().get(i).getComplementaryLine());
		}
	}

	// function for computing all the points contained in the graph
	/**
	 * Adds all points contained in the graph to the list of points.
	 */
	private void computePoints() {
		ArrayList<Vector> p = new ArrayList<>();

		// adding all points
		for (int i = 0; i < getLines().size(); i++) {
			p.add(getLines().get(i).getP1());
			p.add(getLines().get(i).getP2());
		}

		// p.clone instead of p to avoid using pointers
		@SuppressWarnings("unchecked")
		ArrayList<Vector> pts = (ArrayList<Vector>) p.clone();

		// removing of duplicates
		for (Vector pt : pts) {
			if (p.indexOf(pt) != p.lastIndexOf(pt)) {
				p.remove(p.lastIndexOf(pt));
			}
		}
		
		// adding all points to the point list
		for (int i = 0; i < p.size(); i++) {
			getPoints().add(p.get(i));
		}
	}
	
	// function for getting all the lines pointing away from a certain point
	/**
	 * Returns an ArrayList of all lines pointing away from the specified point.
	 * @param p the specified point
	 * @return ArrayList of lines point away from the given point
	 */
	public ArrayList<Line> getLinesPointingAway(Vector p) {
		ArrayList<Line> ls = new ArrayList<>();

		// adding all lines where the starting point is equal to the given point
		for (int i = 0; i < getLines().size(); i++) {			
			if (getLines().get(i).getP1().equals(p)) {				
				ls.add(getLines().get(i));
			}
		}

		return ls;
	}
	
	// function for calculating the closest line
	/**
	 * Returns the line that is closest to the specified line.
	 * @param l the specified line
	 * @return the line closest to the given line
	 */
	private Line getClosestLine(Line l) {
		ArrayList<Line> ls = getLinesPointingAway(l.getP2());
		ArrayList<Double> doubles = new ArrayList<>();
		
		// calculating all angles
		for (Line line : ls) {
			doubles.add(l.angleTo(line));
		}
		
		// returning only the line with the smallest angle
		return ls.get(doubles.indexOf(Collections.min(doubles)));
	}
	
	// function for cloning the list containing all lines
	/**
	 * Returns an identical copy of the lines list.
	 * @return a copy of the lines list
	 */
	private ArrayList<Line> cloneLines() {
		ArrayList<Line> ls = new ArrayList<>();
		
		// cloning all elements of the lines list
		for (int i = 0; i < getLines().size(); i++) {
			ls.add(getLines().get(i));
		}
		
		return ls;
	}
	
	// function for removing the specified lines from the specified list
	/**
	 * Removes all specified lines from the target list.
	 * @param target the list being targeted
	 * @param lines  the list of lines to be removed
	 * @return target list without the specified lines
	 */
	private ArrayList<Line> removeVectors(ArrayList<Line> target, ArrayList<Line> lines) {
		// remove all lines in 'lines' from 'target'
		for (Line l : lines) {
			target.remove(l);
		}
		
		// returning the target list
		return target;
	}
	
	// function for dissecting/decomposing the graph into its individual faces
	/**
	 * Computes all the faces contained in the graph and adds them to the list
	 * of faces.
	 * 
	 * @see Face
	 */
	private void computeFaces() {
		Line ol = null;
		Line l = null;
		ArrayList<Line> ols = cloneLines();
		ArrayList<Line> ls = new ArrayList<>();

		// continue while there are still lines to be checked
		while (ols.size() != 0) {
			// this is where the magic happens
			if (ls.size() != 0) {
				ls.clear();
			}

			ol = ols.get(0);
			ls.add(ol);
			l = getClosestLine(ol);

			while (!ol.equals(l)) {
				ls.add(l);
				l = getClosestLine(l);
			}

			Face f = new Face();

			for (int i = 0; i < ls.size(); i++) {
				f.getEdges().add(ls.get(i));
			}
			
			removeVectors(ols, ls);
			faces.add(f);
		}

		ArrayList<Double> max = new ArrayList<>();

		for (int i = 0; i < faces.size(); i++) {
			max.add(faces.get(i).getArea());
		}

		// area of the Face to be removed
		Double tbr = Collections.max(max);
		
		// cloning the infinite Face before removing it
		infFace = faces.get(max.indexOf(tbr)).clone();

		faces.remove(max.indexOf(tbr));
	}

	// getter for faces list
	/**
	 * Returns the ArrayList containing all faces of the graph.
	 * @return ArrayList of the graphs faces
	 */
	public ArrayList<Face> getFaces() {
		return faces;
	}

	// getter for points list
	/**
	 * Returns the ArrayList containing all points of the graph.
	 * @return ArrayList of the graphs points
	 */
	public ArrayList<Vector> getPoints() {
		return points;
	}

	// getter for lines list
	/**
	 * Returns the ArrayList containing all lines of the graph.
	 * @return ArrayList of the graphs lines
	 */
	public ArrayList<Line> getLines() {
		return lines;
	}
	
	public Face getInfiniteFace() {
		if (infFace != null) {
			return infFace;
		} else {
			return new Face();
		}
	}
	
	// function for cloning the graph
	/**
	 * Returns an exact copy of the graph.
	 */
	public Graph clone() {
		Graph g = new Graph();
		
		// adding all faces
		for (int i = 0; i < getFaces().size(); i++) {
			g.getFaces().add(getFaces().get(i));
		}
		
		// adding all points
		for (int i = 0; i < getPoints().size(); i++) {
			g.getPoints().add(getPoints().get(i));
		}
		
		// adding all lines
		for (int i = 0; i < getLines().size(); i++) {
			g.getLines().add(getLines().get(i));
		}
		
		// returning fully cloned graph
		return g;
	}

}
