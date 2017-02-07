package main;

import java.util.ArrayList;

import org.kabeja.parser.ParseException;

import graph.Face;
import graph.Point;
import graph.Vector;
import io.DXFReader;
import rend.ScadObject;
import rend.objects.*;

public class Main {

	public static void main(String[] args) {
//		Point A = new Point(0, 0);
//		Point B = new Point(1, 0);
//		Point C = new Point(1, 1);
//		Point D = new Point(0, 1);
//		Point E = new Point(-1, 0);
//		Point F = new Point(-1, 1);
//		Point G = new Point(0,-1);
//		
//		Vector a = new Vector(A, B);
//		Vector b = new Vector(B, C);
//		Vector c = new Vector(C, D);
//		Vector d = new Vector(D, A);
//		Vector e = new Vector(A, E);
//		Vector f = new Vector(E, F);
//		Vector g = new Vector(F, D);
//		Vector h = new Vector(A, G);
//		Vector i = new Vector(B, G);
//		Vector j = new Vector(E, G);
//		
//		Face face = new Face();
//		face.getEdges().add(a);
//		face.getEdges().add(b);
//		face.getEdges().add(c);
//		face.getEdges().add(d);
//		face.getEdges().add(e);
//		face.getEdges().add(f);
//		face.getEdges().add(g);
//		face.getEdges().add(h);
//		face.getEdges().add(i);
//		face.getEdges().add(j);
//
//		ArrayList<Face> faces = face.decomposeFace();
//
//		Cube t1 = new Cube(10, 11, 11);
//		Cube t2 = new Cube(12, 12, 12);
//		Translate tr1 = new Translate(t1, 1, 1, 1);
//		ArrayList<ScadObject> in1 = new ArrayList<>();
//		in1.add(t2);
//		in1.add(tr1);
//		Difference in = new Difference(in1);
//		System.out.println(in.printcommand());
		
		try {
			ArrayList<Vector> vcs = DXFReader.getAutocadFile(DXFReader.URI);

			Face f = new Face();
			
			for (int ind = 0; ind < vcs.size(); ind++) {
				//System.out.println(vcs.get(ind));
				f.getEdges().add(vcs.get(ind));
			}
			
			ArrayList<Face> fcs = new ArrayList<>();
			fcs = f.decomposeFace();
			
			for (int i = 0; i < fcs.size(); i++) {
				System.out.println(fcs.get(i));
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}

}