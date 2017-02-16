package main;

import java.util.ArrayList;
import java.util.Arrays;

import org.kabeja.parser.ParseException;

import javax.transaction.TransactionRequiredException;

import graph.Face;
import graph.Graph;
import graph.Vector;
import graph.Line;
import io.DXFReader;
import rend.*;
import rend.objects.*;

public class Main {

	public static void main(String[] args) {
<<<<<<< HEAD
		Point A = new Point(0, 0);
		Point B = new Point(100, 0);
		Point C = new Point(100, 100);
		Point D = new Point(0, 100);
		Point E = new Point(-100, 0);
		Point F = new Point(-100, 100);
		Point G = new Point(0,-100);
=======
		Vector A = new Vector(0, 0);
		Vector B = new Vector(1, 0);
		Vector C = new Vector(1, 1);
		Vector D = new Vector(0, 1);
		Vector E = new Vector(-1, 0);
		Vector F = new Vector(-1, 1);
		Vector G = new Vector(0,-1);
		
		Vector H = new Vector(-100,0);
		Vector I = new Vector(0,100);
>>>>>>> refs/remotes/origin/master
		
		
		Line a = new Line(A, B);
		Line b = new Line(B, C);
		Line c = new Line(C, D);
		Line d = new Line(D, A);
		Line e = new Line(A, E);
		Line f = new Line(E, F);
		Line g = new Line(F, D);
		Line h = new Line(A, G);
		Line i = new Line(B, G);
		Line j = new Line(E, G);
		
		Face face1 = new Face();
		face1.getEdges().add(a);
		face1.getEdges().add(b);
		face1.getEdges().add(c);
		face1.getEdges().add(d);
		face1.getEdges().add(e);
		face1.getEdges().add(f);
		face1.getEdges().add(g);
		face1.getEdges().add(h);
		face1.getEdges().add(i);
		face1.getEdges().add(j);
		Face face = new Face();
<<<<<<< HEAD
		face.setEdges(face1.getEdges());
		face1.generateComplementaryEdges();
		System.out.println(face1);
		Corner test = new Corner(G, face1, 75);
		System.out.println(test.printCommand());
		Wall w1 = new Wall(j);
		System.out.println(w1.printCommand());
	//	ArrayList<Face> faces = face.decomposeFace();
=======
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

//		ArrayList<Face> faces = face.decomposeFace();
>>>>>>> refs/remotes/origin/master
//		
//		for (int iter=0; iter<faces.size();iter++){
//		System.out.println("\nFACE:\n" + faces.get(iter));
//		System.out.println("\nSchwerpunkt\n" + faces.get(iter).getPoints() + "\n");
<<<<<<< HEAD
//		}
		
//		Polygon poly1 = new Polygon(faces.get(0));
//		System.out.println(poly1.printcommand());
		
		
		Cube t1 = new Cube(10, 11, 11, true);
		Cube t2 = new Cube(12, 12, 12, true);
		Translate tr1 = new Translate(t1, 1, 1, 1);
		ArrayList<ScadObject> in1 = new ArrayList<>();
		in1.add(t2);
		in1.add(tr1);
		Difference in = new Difference(in1);
		Rotate r1 = new Rotate(t1, 45, 0, 0, 1);
//		System.out.println(r1.printcommand());
		System.out.println(in.printCommand()); 
//		System.out.println(j);
//		RawWall w = new RawWall(new Vector(H, I));
//		System.out.println(w.printcommand());
//		Corner test = new Corner(I, new ArrayList<>(Arrays.asList(H, A)), 75);
//		System.out.println(test.printcommand());
		
		
		
		System.out.println(face1);
		
		
//		for (Face fc : faces) {
//			System.out.println(fc);
=======
>>>>>>> refs/remotes/origin/master
//		}
//		
//		
//		Cube t1 = new Cube(10, 11, 11, true);
//		Cube t2 = new Cube(12, 12, 12);
//		Translate tr1 = new Translate(t1, 1, 1, 1);
//		ArrayList<ScadObject> in1 = new ArrayList<>();
//		in1.add(t2);
//		in1.add(tr1);
//		Difference in = new Difference(in1);
//		Rotate r1 = new Rotate(t1, 45, 0, 0, 1);
//		System.out.println(r1.printcommand());
//		System.out.println(in.printcommand()); 
//		System.out.println(j);
//		RawWall w = new RawWall(new Line(H, I));
//		System.out.println(w.printcommand());
//		Corner test = new Corner(I, new ArrayList<>(Arrays.asList(H, A)), 75);
//		System.out.println(test.printcommand());
//		
//		
//		
////		Corner test = new Corner(A, new ArrayList<>(Arrays.asList(B,D,E)), 10);
////		System.out.println(test.printcommand());
//		
//		
////		for (Face fc : faces) {
////			System.out.println(fc);
////		}
		
		try {
			ArrayList<Line> vcs = DXFReader.getAutocadFile(DXFReader.URI);

			Graph gr = new Graph(vcs);
			
			ArrayList<Face> fcs = gr.getFaces();
			
			for (int ind = 0; ind < fcs.size(); ind++) {
				System.out.println(fcs.get(ind));
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}

}