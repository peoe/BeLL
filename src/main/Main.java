package main;

import java.util.ArrayList;
import java.util.Arrays;

import javax.transaction.TransactionRequiredException;

import graph.Face;
import graph.Point;
import graph.Vector;
import rend.*;
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
		
		Point H = new Point(-100,0);
		Point I = new Point(0,100);
		
		
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

		ArrayList<Face> faces = face.decomposeFace();
		
		for (int iter=0; iter<faces.size();iter++){
		System.out.println("\nFACE:\n" + faces.get(iter));
		System.out.println("\nSchwerpunkt\n" + faces.get(iter).getPoints() + "\n");
		}
		
		
		Cube t1 = new Cube(10, 11, 11, true);
		Cube t2 = new Cube(12, 12, 12);
		Translate tr1 = new Translate(t1, 1, 1, 1);
		ArrayList<ScadObject> in1 = new ArrayList<>();
		in1.add(t2);
		in1.add(tr1);
		Difference in = new Difference(in1);
		Rotate r1 = new Rotate(t1, 45, 0, 0, 1);
		System.out.println(r1.printcommand());
		System.out.println(in.printcommand()); 
		System.out.println(j);
		RawWall w = new RawWall(new Vector(H, I));
		System.out.println(w.printcommand());
		Corner test = new Corner(I, new ArrayList<>(Arrays.asList(H, A)), 75);
		System.out.println(test.printcommand());
		
		
		
//		Corner test = new Corner(A, new ArrayList<>(Arrays.asList(B,D,E)), 10);
//		System.out.println(test.printcommand());
		
		
//		for (Face fc : faces) {
//			System.out.println(fc);
//		}
	}

}