package main;

import java.util.ArrayList;
import java.util.Arrays;

//import org.kabeja.parser.ParseException;

import javax.transaction.TransactionRequiredException;

import org.kabeja.parser.ParseException;

import graph.Edge;
import graph.Face;
import graph.Graph;
import graph.Vector;
import graph.Line;
import io.ClipboardCopier;
import io.DXFReader;
import io.Quicksort;
import io.ScadPrinter;
import render.*;
import render.objects.*;

public class Main {

	public static void main(String[] args) throws ParseException {

		Vector A = new Vector(0, 0);
		Vector B = new Vector(170, 0);
		Vector C = new Vector(100, 100);
		Vector D = new Vector(0, 100);
		Vector E = new Vector(-130, 0);
		Vector F = new Vector(-100, 100);
		Vector G = new Vector(0,-150);
		
		Vector H = new Vector(-50,-50);
		Vector I = new Vector(100, -100);
		
		
		Line a = new Line(A, B);
		Line b = new Line(B, C);
		Line c = new Line(C, D);
		Line d = new Line(D, A);
		Line e = new Line(A, E);
		Line f = new Line(E, F);
		Line g = new Line(F, D);
		Line h = new Line(A, G);
		Line i = new Line(E, H);
		Line j = new Line(H, G);
		Line k = new Line(G, I);
		Line l = new Line(I, B);
		
		
		ArrayList<Line> lines = new ArrayList<>();
		
		
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
		lines.add(k);
		lines.add(l);
		
//		Graph gr = new Graph(DXFReader.getAutocadFile("C:\\Users\\Johann\\Documents\\GitHub\\BeLL\\res\\Zeichnung1.dxf"));
		Params p = new Params(0.25, 10.0, 2.0,  4.0, 4.0, 2.0, 75.0, 4.0, 4.0, 1.0, 6.0, 185.0, 153.0);
		ScadProcessor proc = new ScadProcessor(lines, p);
		
		@SuppressWarnings("unchecked")
		ArrayList<Corner> oa = new Quicksort(proc.getCorners(), "width").sortArray();
		
		for (Object o : oa){
			System.out.println(((Corner) o).getWidth());
		}
		
		//System.out.println(proc.renderWallFiles());
		Union u = new Union(new ArrayList<>());
////		u.getObjects().add(proc.outputBasePlates());
////	 	u.getObjects().add(proc.outputWalls());
		u.getObjects().add(proc.outputCorners());
		ClipboardCopier.copyToClipboard(u.toString());
	//	u.getObjects().add((new Corner(gr.getNodes().get(0), p.getE(), p)));
//		ClipboardCopier.copyToClipboard(new Corner(gr.getNodes().get(6), p.getE(), p).toString());
//		ClipboardCopier.copyToClipboard(new CornerPin(gr.getEdges().get(3), Params.getE()).toString());

		
	}

}
