package main;

import java.util.ArrayList;
import java.util.Arrays;

//import org.kabeja.parser.ParseException;

import javax.transaction.TransactionRequiredException;

import graph.Face;
import graph.Graph;
import graph.Vector;
import graph.Line;
import io.ClipboardCopier;
import io.DXFReader;
import io.ScadPrinter;
import render.*;
import render.objects.*;

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
		
		
		ArrayList<Line> lines = new ArrayList<>();
		
		//Face face1 = new Face();
		lines.add(a);
		lines.add(b);
		lines.add(c);
		lines.add(d);
		lines.add(e);
		lines.add(f);
		lines.add(g);
		lines.add(h);
		lines.add(i);
		lines.add(j);
		
		Graph gr = new Graph(lines);
		Params.setParams(0.75, 10, 2,  4, 4, 2, 75, 4, 4, 1);
		System.out.println(gr.getEdges().get(2));
		ClipboardCopier.copyToClipboard(gr.outputCorners().toString());
		
	}

}