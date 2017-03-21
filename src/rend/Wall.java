package rend;

import java.util.ArrayList;

import graph.*;
import rend.objects.*;

public class Wall implements ScadObject {
	
	private Line w;

	public Wall(Line w) {
		super();
		this.w = w;
	}

	@Override
	public String printCommand() {
		Vector wVec = w.toVector();
		//Vector vcube = new Vector(wVec.getLength(),0);
		Cube temp = new Cube(wVec.getLength(), 6, 1, true);
		
		double angle = wVec.angle();
		Rotate rtemp = new Rotate(temp, angle, 0, 0, 1);
		Vector trv = wVec.multiply(0.5);
		Translate walltemp = new Translate(rtemp, trv, 0);
		//Intersection
		Rotate r1MinusTile = new Rotate(MinusTile,angle, 0, 0, 1);
		Translate tr2MinusTile = new Translate(new Rotate(MinusTile,angle+180, 0, 0, 1), wVec, 0);
		ArrayList<ScadObject> diftemp = new ArrayList<>();
		diftemp.add(walltemp);
		diftemp.add(r1MinusTile);
		diftemp.add(tr2MinusTile);
		Difference finalWall = new Difference(diftemp);

		
//		return Unionwalltemp.printcommand();
		return finalWall.printCommand();
	}

}
