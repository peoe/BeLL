package render;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.SynchronousQueue;

import graph.*;
import render.objects.*;

public class Corner implements ScadObject {

	private Node n;

	public Corner(Node N) {
		n = N;
	}

	private static ScadObject getBaseTile(double height, boolean center) {
		return (new Cylinder(height, Params.getCornerRadius(), 
				center));
	}
	private static ScadObject getMinusTileCorner(double angle) {// r=10 w=3
																// e=0.25
		double cornerRadius = Params.getCornerRadius();
		double wallWidth = Params.getWallwidth() / 2;
		double e = Params.getE();
		Cube c1 = new Cube(cornerRadius, wallWidth, 1, true);
		// Cube c2 = new Cube(cornerRadius, wallWidth - 2 * e, 1, true);
		Translate tc1 = new Translate(c1, cornerRadius, 0, 0);
		// Translate tc2 = new Translate(c2, cornerRadius + e, 0, 0);
		ArrayList<ScadObject> temp = new ArrayList<>();
		temp.add(tc1);
		// temp.add(tc2);

		return (new Rotate(new Difference(temp), angle, 0, 0, 1));
	}

	@Override
	public String toString() {
		// First part Wall fitting segment
		ArrayList<ScadObject> cornerBaseDifference = new ArrayList<>();
		cornerBaseDifference.add(getBaseTile(1, true));
		ArrayList<Edge> cornerEdges = n.getAdjacentEdges();
		for (Edge cornerEdge : cornerEdges) {
			cornerBaseDifference.add(getMinusTileCorner(cornerEdge.toVector().angleInDegrees()));
		}

		ScadObject finalDifference = new Translate(
				new Scale(new Difference(cornerBaseDifference), 1, 1,
						Params.getHeight() - Params.getBasePlateHeight()),
				0, 0, 0.5 * (Params.getHeight() - Params.getBasePlateHeight()));

		// Second part base plate fitting segment


		
		ArrayList<ScadObject> unionArrayList = new ArrayList<>();
		for (Edge cornerEdge : cornerEdges) {
			unionArrayList.add(new Pin(cornerEdge, 0.0));
		}
		unionArrayList.add(getBaseTile(Params.getBasePlateHeight(), false));

		Union Unionbottom = new Union(unionArrayList);
		System.out.println(Unionbottom.toString());
		// Translate sd = new Translate(new Scale(finalDifference, 1,
		// 1,ParameterController.getHeight()-ParameterController.getBasePlateHeight()),0,0,ParameterController.getHeight()/2);

		Union finalUnion = new Union(new ArrayList<ScadObject>(Arrays.asList(Unionbottom, finalDifference)));
		Translate result = new Translate(finalUnion, 0, 0, 0);

		return result.toString();
		// return null;
	}

}
