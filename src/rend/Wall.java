package rend;

import java.util.ArrayList;

import graph.*;
import rend.objects.*;

public class Wall implements ScadObject {
	
	private Line w;

<<<<<<< HEAD:src/rend/Wall.java
	public Wall(Vector w) {
=======
	public RawWall(Line w) {
>>>>>>> refs/remotes/origin/master:src/rend/RawWall.java
		super();
		this.w = w;
	}

	@Override
<<<<<<< HEAD:src/rend/Wall.java
	public String printCommand() {
		Vector vcube = new Vector(w.getLength(),0);
=======
	public String printcommand() {
		Line vcube = new Line(w.getLength(),0);
>>>>>>> refs/remotes/origin/master:src/rend/RawWall.java
		Cube temp = new Cube(w.getLength(), 6, 1, true);
		double angle = w.angle();
		Rotate rtemp = new Rotate(temp, angle, 0, 0, 1);
		Line trv = w.multiply(0.5);
		Translate walltemp = new Translate(rtemp, trv, 0);
		//Union
//		Rotate Unioncube1 = new Rotate(UnionTile, angle, 0, 0, 1);
//		Translate Unioncube2 = new Translate(Unioncube1, w, 0);
		//Intersection
		Rotate r1MinusTile = new Rotate(MinusTile,angle, 0, 0, 1);
		Translate tr2MinusTile = new Translate(new Rotate(MinusTile,angle+180, 0, 0, 1), w, 0);
		ArrayList<ScadObject> diftemp = new ArrayList<>();
		diftemp.add(walltemp);
		diftemp.add(r1MinusTile);
		diftemp.add(tr2MinusTile);
		Difference finalWall = new Difference(diftemp);
		
		Translate result = new Translate(finalWall,new Vector(w.getP1()),0);
		
//		return Unionwalltemp.printcommand();
		return result.printCommand();
	}

}
