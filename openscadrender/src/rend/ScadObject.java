package rend;

public interface ScadObject {
	final String cylinder = " cylinder(%1$.3f,%2$.3f,%2$.3f);\n";
	final String cube = "cube([%1$.2f,%2$.2f,%3$.2f]);\n";
	final String polygon = "polygon(%1s,%2s,10);\n";
	final String linear_extrude = "linear_extrude(%1$.2f){\n%s\n}";
	final String translate = "translate([%1$.2f, %2$.2f, %3$.2f]){\n%s\n}";

	
	public String printcommand();

}
