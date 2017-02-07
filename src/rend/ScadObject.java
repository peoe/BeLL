package rend;

import java.util.ArrayList;
import java.util.Arrays;

import rend.objects.Cube;
import rend.objects.Cylinder;
import rend.objects.Difference;
import rend.objects.Translate;

public interface ScadObject {
	final String cylinder = "cylinder(%1$.3f,%2$.3f,%3$.3f, %4$s);\n";
	final String cube = "cube([%1$.2f,%2$.2f,%3$.2f], %4$s);\n";
	final String polygon = "polygon(%1s,%2s,10);\n";
	final String linear_extrude = "linear_extrude(%1$.2f){\n%s}";
	final String translate = "translate([%1$.2f, %2$.2f, %3$.2f]){\n%4$s}";
	final String intersection = "intersection(){\n%s}";
	final String difference = "difference(){\n%s}";
	final String union = "union(){\n%s}";
	final String rotate = "rotate(%1$.2f,[%2$d,%3$d,%4$d]){\n%5$s}";
	final String scale = "scale([%1$.2f, %2$.2f, %3$.2f]){\n%4$s}";
	final Cylinder Base = new Cylinder(1, 10, 10, true);
	final Cube UnionTile = new Cube(20,10,1,true);
	final Difference MinusTile = new Difference(new ArrayList<>(Arrays.asList(new Cube(20,10,1, true),new Translate(new Cube(10,9.5,1, true), 10, 0, 0))));
	final Difference MinusTileCorner = new Difference(new ArrayList<>(Arrays.asList(new Translate(new Cube(10,10,1, true),15,0,0),new Translate(new Cube(9.75, 9.5, 1, true),15.25,0,0))));
	
	public String printcommand();

}
