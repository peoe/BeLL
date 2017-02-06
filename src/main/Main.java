package main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import graph.Face;
import graph.Point;
import graph.Vector;
import rend.objects.*;

public class Main {

	public static void main(String[] args) {
		
		Point A = new Point(0, 0);
		Point B = new Point(1, 0);
		Point C = new Point(1, 1);
		Point D = new Point(0, 1);
		Point E = new Point(-1, 0);
		Point F = new Point(-1, 1);
		Point G = new Point(0,-1);
		
		Vector a = new Vector(A, B);
		Vector b = new Vector(B, C);
		Vector c = new Vector(C, D);
		Vector d = new Vector(D, A);
		Vector e = new Vector(A, E);
		Vector f = new Vector(E, F);
		Vector g = new Vector(F, D);
		Vector h = new Vector(A, G);
		Vector i = new Vector(B, G);
		Vector j = new Vector(E, G);
		
		Face face = new Face();
		face.getEdges().add(a);
		face.getEdges().add(b);
		face.getEdges().add(c);
		face.getEdges().add(d);
		face.getEdges().add(e);
		face.getEdges().add(f);
		face.getEdges().add(g);
		face.getEdges().add(h);
		face.getEdges().add(i);
		face.getEdges().add(j);
		//System.out.println((face.getClosestVector(c)));
		ArrayList<Face> faces = face.decomposeFace();
		System.out.println(faces.get(0).getEdges().get(0));
	//	System.out.println(a.angleto(c));
//		System.out.println(Face.anglesOfVectors(face.getVectorsPointingAway(D)));
		
//		for (int i = 0; i < faces.size(); i++) {
//			System.out.println(faces.get(i).getEdges().toString());
//		}
		Cylinder test = new Cylinder(5,10,10);
		Cube test2 = new Cube(10,5,10);
		ScadGroup t = new ScadGroup(ArrayList<ScadObject>(test,test2));
		Translate tr1test = new Translate(test, 1, 20, 30);
//		System.out.println(tr1test.getObject().printcommand());
		System.out.println(tr1test.printcommand());
		
	}

}
