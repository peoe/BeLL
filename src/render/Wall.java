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
		this.e = e;
	}
	
	public Edge getE() {
		return e;
	}

	public void setE(Edge e) {
		this.e = e;
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
		
		Cube rawWallCube = new Cube(wallVector.getLength(), Params.getWallwidth(), 1, true);
		
		double wallAngle = wallVector.angleInDegrees();
		
		ArrayList<ScadObject> wallDifference = new ArrayList<>();
		wallDifference.add(rawWallCube);
		
		wallDifference.add(new Translate(new CornerCylinder(getE().getN1(), Params.getE()), -0.5*wallVector.getLength(), 0, -0.5*Params.getHeight()));
		wallDifference.add(new Translate(new CornerCylinder(getE().getN2(), Params.getE()), 0.5*wallVector.getLength(), 0, -0.5*Params.getHeight()));
		//Difference of pre-defined objects -> rotate to wall position -> Scale it to height -> Translate it to wall position -> output toString()
		return new Translate(new Scale(new Rotate(new Difference(wallDifference), wallAngle, 0, 0, 1), 1.0, 1.0, Params.getHeight()-Params.getBasePlateHeight()), wallVector.multiply(0.5), Params.getBasePlateHeight() + 0.5*(Params.getHeight()-Params.getBasePlateHeight()) ).toString();
		
		
		
	}

}
