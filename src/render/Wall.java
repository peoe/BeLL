package render;

import java.util.ArrayList;
import java.util.Arrays;

import graph.*;
import io.ClipboardCopier;
import render.objects.*;

public class Wall implements ScadObject {
	
	// the Line object for the Wall to be based upon
	private Edge e;

	// constructor
	/**
	 * Creates a new Wall object based upon the given Line object.
	 * @param w the given Line object
	 */
	public Wall(Edge e) {
		super();
		this.e = e;
	}
	
	//final Difference MinusTile = new Difference(new ArrayList<>(Arrays.asList(new Cube(20,10,1, true),new Translate(), 10.25, 0, 0))));

	// getting the negative tile
	/**
	 * Returns the minus tile of the wall.
	 * @return the minus tile
	 */
	private ScadObject getMinusTile(boolean angle){
	 Cube baseCube = new Cube(Params.getCornerRadius(),Params.getWallwidth()/2-2*Params.getE(),1, true);
	 Translate tBaseCube = new Translate(baseCube,Params.getCornerRadius()+Params.getE(),0,0);
	 Cube subtractCube = new Cube(2*Params.getCornerRadius(),Params.getCornerRadius(),1,true);
	 ArrayList<ScadObject> cubeDifference = new ArrayList<>();
	 cubeDifference.add(subtractCube);
	 cubeDifference.add(tBaseCube);
	 ScadObject differenceTile = new Difference(cubeDifference);
	 int tileAngle = (angle) ? 1 : 0;
	 int tileTranslateDirection = (angle) ? 1 : -1;
	 ScadObject rDifferenceTile = new Rotate(differenceTile, tileAngle*180, 0, 0, 1);
	 return (new Translate(rDifferenceTile, 0.5*this.e.toVector().getLength()*tileTranslateDirection, 0, 0));
	}
	
	//getting a Wall Tile which is not used for differences
	public ScadObject getPresentationWall(){
		Scale s1 = new Scale(this,1,1,Params.getHeight()-Params.getBasePlateHeight());
		Translate t1 = new Translate(s1, e.getN1().getOrigin(), Params.getBasePlateHeight()+(Params.getHeight()-Params.getBasePlateHeight())/2 );
		return t1;
	}
	
	// printing the command for creating the Wall object
	/**
	 * Prints a String which can be used to create the Wall object.
	 */
	@Override
	public String toString() {
		Vector wallVector = e.toVector();
		//Vector vcube = new Vector(wVec.getLength(),0);
		Cube rawWallCube = new Cube(wallVector.getLength(), Params.getWallwidth(), 1, true);
		
		double wallAngle = wallVector.angleInDegrees();
		//Rotate rtemp = new Rotate(temp, angle, 0, 0, 1);
		//Translate walltemp = new Translate(rawWallCube,  wallVector.multiply(0.5), 0);
		
		
		//Intersection
		//Rotate r1MinusTile = new Rotate(getMinusTile(),wallAngle, 0, 0, 1);
		//Translate tr2MinusTile = new Translate(new Rotate(getMinusTile(),wallAngle+180, 0, 0, 1), wallVector, 0);
		ArrayList<ScadObject> wallDifference = new ArrayList<>();
		wallDifference.add(rawWallCube);
		wallDifference.add(getMinusTile(true));
		wallDifference.add(getMinusTile(false));
		Difference finalWall = new Difference(wallDifference);
		Rotate rFinalWall = new Rotate(finalWall, wallAngle, 0, 0, 1);
		Translate trFinalWall = new Translate(rFinalWall, wallVector.multiply(0.5), 0);
		
		// return Unionwalltemp.printcommand();
		return trFinalWall.toString();
	}

}
