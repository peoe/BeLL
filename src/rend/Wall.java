package rend;

import java.util.ArrayList;

import graph.*;
import rend.objects.*;

public class Wall implements ScadObject {
	
	private Vector w;

	public Wall(Vector w) {
		super();
		this.w = w;
	}

	@Override
	public String printcommand() {
		Vector vcube = new Vector(w.getLength(),0);
		Cube temp = new Cube(w.getLength(), 6, 1, true);
		double angle = w.angle();
		Rotate rtemp = new Rotate(temp, angle, 0, 0, 1);
		Vector trv = w.multiply(0.5);
		Translate walltemp = new Translate(rtemp, trv, 0);
		//Union
		Rotate Unioncube1 = new Rotate(UnionTile, angle, 0, 0, 1);
		Translate Unioncube2 = new Translate(Unioncube1, w, 0);
		ArrayList<ScadObject> UnionAL = new ArrayList<>();
		UnionAL.add(Unioncube1);
		UnionAL.add(Unioncube2);
		UnionAL.add(walltemp);
		Union Unionwalltemp = new Union(UnionAL);
		//Intersection
		Rotate r1MinusTile = new Rotate(MinusTile,angle, 0, 0, 1);
		Translate tr2MinusTile = new Translate(new Rotate(MinusTile,angle+180, 0, 0, 1), w, 0);
		ArrayList<ScadObject> diftemp = new ArrayList<>();
		diftemp.add(Unionwalltemp);
		diftemp.add(r1MinusTile);
		diftemp.add(tr2MinusTile);
		Difference finalWall = new Difference(diftemp);
		//Translate result = new Translate(finalWall, (new Vector(w.getP1())), 0);
		
		System.out.println("MinusTile\n" + MinusTile.printcommand());
		System.out.println("CMinusTile\n" + MinusTileCorner.printcommand());
		
		
//		return Unionwalltemp.printcommand();
		return finalWall.printcommand();
	}

}
