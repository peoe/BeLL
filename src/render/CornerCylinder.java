package render;

import java.util.ArrayList;

import graph.*;
import render.objects.Cube;
import render.objects.Difference;
import render.objects.Rotate;
import render.objects.Scale;
import render.objects.Translate;

public class CornerCylinder implements ScadObject {

	private double epsilon;

	private Node n;

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

	public CornerCylinder(Node n, double epsilon) {
		this.epsilon = epsilon;
		this.n = n;
	}

	// returns the tile which is cut out for walls
	private ScadObject getMinusTileCorner(double angle) {

		double cornerRadius = Params.getCornerRadius();
		double wallWidth = Params.getWallwidth() / 2 - 2 * getEpsilon();
		Cube tileCube = new Cube(cornerRadius, wallWidth, 1, true);
		Translate tTileCube = new Translate(tileCube, cornerRadius + getEpsilon(), 0, 0);

		return (new Rotate(tTileCube, angle, 0, 0, 1));
	}

	public ScadObject getCornerCylinder() {
		// First part Wall fitting segment
		ArrayList<ScadObject> cornerBaseDifference = new ArrayList<>();
		cornerBaseDifference.add(new CornerBaseTile(1, getEpsilon()));
		ArrayList<Edge> cornerEdges = n.getAdjacentEdges();
		for (Edge cornerEdge : cornerEdges) {
			cornerBaseDifference.add(getMinusTileCorner(cornerEdge.toVector().angleInDegrees()));
		}

		return new Translate(
				new Scale(new Difference(cornerBaseDifference), 1, 1, Params.getHeight() - Params.getBasePlateHeight()),
				0, 0, Params.getBasePlateHeight() + 0.5 * (Params.getHeight() - Params.getBasePlateHeight()));
	}

	// returns scad command
	@Override
	public String toString() {
		return getCornerCylinder().toString();
	}

}
