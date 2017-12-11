package render;

import java.util.ArrayList;

import graph.*;
import render.objects.Cube;
import render.objects.Difference;
import render.objects.Rotate;
import render.objects.Scale;
import render.objects.Translate;

public class CornerCylinder implements ScadObject {

	//epsilon value
	private double epsilon;
	
	//node for which the cylinder need to be created
	private Node n;
	//Parameters
	private Params params;

	//getters and setters
	public double getEpsilon() {
		return epsilon;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

	public Node getN() {
		return n;
	}

	public void setN(Node n) {
		this.n = n;
	}

	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	//constructor
	/**
	 * Constructor of CornerCylinder class
	 * @param n DCEL Node of CornerCylinder
	 * @param epsilon Epsilon margin
	 */
	public CornerCylinder(Node n, double epsilon, Params params) {
		this.epsilon = epsilon;
		this.n = n;
		this.params = params;
	}

	// returns the difference tile for wall cuttings
	/**
	 * returns rotated cube used for difference in the corner cylinder
	 * @param angle
	 * @return cube ScadObject
	 */
	private ScadObject getMinusTileCorner(double angle) {

		double cornerRadius = params.getCornerRadius();
		double wallWidth = params.getWallWidth() / 2 - 2 * getEpsilon();
		Cube tileCube = new Cube(cornerRadius, wallWidth, 4, true);
		Translate tTileCube = new Translate(tileCube, cornerRadius + getEpsilon(), 0, 0);

		return (new Rotate(tTileCube, angle, 0, 0, 1));
	}

	/**
	 * returns cylinder for specific node with cut outs for all walls
	 * @return corner cylinder ScadObject
	 */
	public ScadObject getCornerCylinder() {
		ArrayList<ScadObject> cornerBaseDifference = new ArrayList<>();
		//Adds bottom circular CornerBaseTile used for the difference
		cornerBaseDifference.add(new CornerBaseTile(1, getEpsilon(), params));
		//Adds cut outs for every adjacent edge
		ArrayList<Edge> cornerEdges = n.getAdjacentEdges();
		for (Edge cornerEdge : cornerEdges) {
			cornerBaseDifference.add(getMinusTileCorner(cornerEdge.toVector().angleInDegrees()));
		}
		//Scale to fit wallHeight
		//Translate to move the object to the right position
		return new Translate(
				new Scale(new Difference(cornerBaseDifference), 1, 1, params.getHeight() - params.getBasePlateHeight()),
				0, 0, params.getBasePlateHeight() + 0.5 * (params.getHeight() - params.getBasePlateHeight()));
	}
	
	/**
	 * returns cylinder for specific node with cut outs for a specific edge
	 * @return corner cylinder ScadObject
	 */
	public ScadObject getCornerCylinder(Edge e) {
		Difference cornerBaseDifference = new Difference();
		//Adds bottom circular CornerBaseTile used for the difference
		cornerBaseDifference.getObjects().add(new CornerBaseTile(1, getEpsilon(), params));
		//given Edge must start in N
		if (e.getN1() != getN()){
			e = e.getTwin();
		}
		//Adds a cut out cube directing in e's direction
		cornerBaseDifference.getObjects().add(getMinusTileCorner(e.toVector().angleInDegrees()));
		
		//Scale to fit wallHeight
		//Translate to move the object to the right position
		return new Translate(
				new Scale(cornerBaseDifference, 1, 1, params.getHeight() - params.getBasePlateHeight()),
				0, 0, params.getBasePlateHeight() + 0.5 * (params.getHeight() - params.getBasePlateHeight()));
	}

	// returns scad command
	@Override
	public String toString() {
		return getCornerCylinder().toString();
	}

}
