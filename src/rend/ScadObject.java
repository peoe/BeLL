package rend;

import java.util.ArrayList;
import java.util.Arrays;

import rend.objects.Cube;
import rend.objects.Cylinder;
import rend.objects.Difference;
import rend.objects.Scale;
import rend.objects.Translate;
import rend.objects.Union;

public interface ScadObject {
	final static String cylinder = "cylinder(%1$.3f,%2$.3f,%3$.3f, %4$s);\n";
	final static String cube = "cube([%1$.2f,%2$.2f,%3$.2f], %4$s);\n";
	final static String polygon = "polygon(%1$s,10);\n";
	final static String linear_extrude = "linear_extrude(%1$.2f){\n%2$s}";
	final static String translate = "translate([%1$.2f, %2$.2f, %3$.2f]){\n%4$s}";
	final static String intersection = "intersection(){\n%s}";
	final static String difference = "difference(){\n%s}";
	final static String union = "union(){\n%s}";
	final static String rotate = "rotate(%1$.2f,[%2$d,%3$d,%4$d]){\n%5$s}";
	final static String scale = "scale([%1$.3f, %2$.3f, %3$.3f]){\n%4$s}";
	final static Cylinder Base = new Cylinder(1, 10, 10, true);
	final static Cylinder Base2 = new Cylinder((1.0/6.0), 20, 20, true);
	final static Cube UnionTile = new Cube(20,10,1,true);
	final static Difference MinusTile = new Difference(new ArrayList<>(Arrays.asList(new Cube(20,10,1, true),new Translate(new Cube(10,2.5,1, true), 10.25, 0, 0))));
	final static Difference MinusTileCorner = new Difference(new ArrayList<>(Arrays.asList(new Translate(new Cube(10,3,1, true),10,0,0),new Translate(new Cube(9.75, 2.5, 1, true),10.125,0,0))));
	final static Translate PinNegative = new Translate(new Union(new ArrayList<>(Arrays.asList(new Cube(3,2,1, true),new Translate(new Cylinder(1, 4, 4, true), 5, 0, 0)))), -3.75, 0, 0);
	final static Translate PinPositive = new Translate(new Union(new ArrayList<>(Arrays.asList(new Cube(3,1.5,1, true),new Translate(new Cylinder(1, 3.75, 3.75, true), 5, 0, 0)))), -3.4375, 0, 0);
	
	public String printCommand();

}
