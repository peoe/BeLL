package rend;

import java.util.ArrayList;

import graph.*;
import rend.objects.Cube;

public class Main {
	public static void main(String Args[]){
//	Point A = new Point(0,0);
//	Point B = new Point(1,0);
//	Point C = new Point(0,1);
//	Point D = new Point(-1,0);
//	
//	ArrayList<Point> nextpoints = new ArrayList<>();
//	nextpoints.add(B);
//	nextpoints.add(C);
//	nextpoints.add(D);		
//	
//	Pole T = new Pole(A,nextpoints,10);
//
//	System.out.println(T.renderPole());
	Cube c = new Cube(10,10,10);
	System.out.println(c.getcommand());
	}
	
	
	

}
