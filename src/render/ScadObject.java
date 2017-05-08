package render;

import java.util.ArrayList;
import java.util.Arrays;

import render.objects.Cube;
import render.objects.Cylinder;
import render.objects.Difference;
import render.objects.Scale;
import render.objects.Translate;
import render.objects.Union;

public interface ScadObject {

	final Cube UnionTile = new Cube(20,10,1,true);
	
	final Difference MinusTileCorner = new Difference(new ArrayList<>(Arrays.asList(new Translate(new Cube(10,3,1, true),10,0,0),new Translate(new Cube(9.75, 2.5, 1, true),10.125,0,0))));
	final Translate PinNegative = new Translate(new Union(new ArrayList<>(Arrays.asList(new Cube(3,2,1, true),new Translate(new Cylinder(1, 4, 4, true), 5, 0, 0)))), -3.75, 0, 0);
	final Translate PinPositive = new Translate(new Union(new ArrayList<>(Arrays.asList(new Cube(3,1.5,1, true),new Translate(new Cylinder(1, 3.75, 3.75, true), 5, 0, 0)))), -3.4375, 0, 0);
	public String printCommand();

}
