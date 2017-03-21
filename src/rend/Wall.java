package rend;

import java.util.ArrayList;
import java.util.Arrays;

import graph.*;
import io.ClipboardCopier;
import rend.objects.*;

public class Wall implements ScadObject {
	
	// the Line object for the Wall to be based upon
	private Line w;

	// constructor
	/**
	 * Creates a new Wall object based upon the given Line object.
	 * @param w the given Line object
	 */
	public Wall(Line w) {
		super();
		this.w = w;
	}
	
	//final Difference MinusTile = new Difference(new ArrayList<>(Arrays.asList(new Cube(20,10,1, true),new Translate(), 10.25, 0, 0))));

	// getting the negative tile
	/**
	 * Returns the minus tile of the wall.
	 * @return the minus tile
	 */
	private ScadObject getMinusTile(){
	 Cube c1 = new Cube(ParameterController.getCornerRadius(),ParameterController.getWallwidth()/2-2*ParameterController.getE(),1, true);
	 Translate tc1 = new Translate(c1,ParameterController.getCornerRadius()+ParameterController.getE(),0,0);
	 Cube c2 = new Cube(2*ParameterController.getCornerRadius(),ParameterController.getCornerRadius(),1,true);
	 ArrayList<ScadObject> dif = new ArrayList<>();
	 dif.add(c2);
	 dif.add(tc1);
	 return (new Difference(dif));
	}
	
	//getting a Wall Tile which is not used for differences
	public ScadObject getPresentationWall(){
		Scale s1 = new Scale(this,1,1,ParameterController.getHeight()-ParameterController.getBasePlateHeight());
		Translate t1 = new Translate(s1, 0, 0, ParameterController.getBasePlateHeight()+(ParameterController.getHeight()-ParameterController.getBasePlateHeight())/2 );
		return t1;
	}
	
	// printing the command for creating the Wall object
	/**
	 * Prints a String which can be used to create the Wall object.
	 */
	@Override
	public String printCommand() {
		Vector wVec = w.toVector();
		//Vector vcube = new Vector(wVec.getLength(),0);
		Cube temp = new Cube(wVec.getLength(), ParameterController.getWallwidth(), 1, true);
		
		double angle = wVec.angleD();
		Rotate rtemp = new Rotate(temp, angle, 0, 0, 1);
		Vector trv = wVec.multiply(0.5);
		Translate walltemp = new Translate(rtemp, trv, 0);
		
		//Intersection
		Rotate r1MinusTile = new Rotate(getMinusTile(),angle, 0, 0, 1);
		Translate tr2MinusTile = new Translate(new Rotate(getMinusTile(),angle+180, 0, 0, 1), wVec, 0);
		ArrayList<ScadObject> diftemp = new ArrayList<>();
		diftemp.add(walltemp);
		diftemp.add(r1MinusTile);
		diftemp.add(tr2MinusTile);
		Difference finalWall = new Difference(diftemp);
		
		// return Unionwalltemp.printcommand();
		return finalWall.printCommand();
	}

}
