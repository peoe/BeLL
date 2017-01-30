package main;

import java.util.ArrayList;

import graph.Face;
import graph.Point;
import graph.Vector;

public class Main {

	public static void main(String[] args) {
		
		Point A = new Point(0, 0);
		Point B = new Point(1, 0);
		Point C = new Point(1, 1);
		Point D = new Point(0, 1);
		Point E = new Point(-1, 0);
		Point F = new Point(-1, 1);
		
		Vector a = new Vector(A, B);
		Vector b = new Vector(B, C);
		Vector c = new Vector(C, D);
		Vector d = new Vector(D, A);
		Vector e = new Vector(A, E);
		Vector f = new Vector(E, F);
		Vector g = new Vector(F, D);
		
		Face face = new Face();
		face.getEdges().add(a);
		face.getEdges().add(b);
		face.getEdges().add(c);
		face.getEdges().add(d);
		face.getEdges().add(e);
		face.getEdges().add(f);
		face.getEdges().add(g);
		
		ArrayList<Face> faces = face.decomposeFace();
		
//		System.out.println(Face.anglesOfVectors(face.getVectorsPointingAway(D)));
		
		for (int i = 0; i < faces.size(); i++) {
			System.out.println(faces.get(i).getEdges().toString());
		}
	}

}
