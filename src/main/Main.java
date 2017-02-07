package main;

import java.util.ArrayList;

import org.kabeja.parser.ParseException;

import graph.Face;
import graph.Point;
import graph.Vector;
import io.DXFReader;
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
		Wall w = new Wall(new Vector(H, I));
		System.out.println(w.printcommand());
		
//		Corner test = new Corner(A, new ArrayList<>(Arrays.asList(B,D,E)), 10);
//		System.out.println(test.printcommand());
		
		
//		for (Face fc : faces) {
//			System.out.println(fc);
//		}
		
		try {
			ArrayList<Vector> vcs = DXFReader.getAutocadFile(DXFReader.URI);

			Face f1 = new Face();
			
			for (int ind = 0; ind < vcs.size(); ind++) {
				//System.out.println(vcs.get(ind));
				f1.getEdges().add(vcs.get(ind));
			}
			
			ArrayList<Face> fcs = new ArrayList<>();
			fcs = f1.decomposeFace();
			
			for (int ind = 0; ind < fcs.size(); ind++) {
				System.out.println(fcs.get(ind));
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}

}