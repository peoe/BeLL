package main;

import java.util.ArrayList;
import java.util.Arrays;

//import org.kabeja.parser.ParseException;

import javax.transaction.TransactionRequiredException;

import graph.Face;
import graph.Graph;
import graph.Vector;
import graph.Line;
import io.DXFReader;
import io.ScadPrinter;
import rend.*;
import rend.objects.*;

public class Main {

	public static void main(String[] args) {

		Vector A = new Vector(0, 0);
		Vector B = new Vector(100, 0);
		Vector C = new Vector(100, 100);
		Vector D = new Vector(0, 100);
		Vector E = new Vector(-100, 0);
		Vector F = new Vector(-100, 100);
		Vector G = new Vector(0,-100);
		
		Vector H = new Vector(-100,0);
		Vector I = new Vector(0,100);
		
		
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
		
		Graph gr = new Graph(face1.getEdges());			
		
		ParameterController.setParams(0.25, 10, 2,  3, 4, 4, 300, 4);

		Corner cor1 = new Corner(B,gr,10.0);
		System.out.println(cor1.printCommand());
	}

}