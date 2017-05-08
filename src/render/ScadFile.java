package render;

import java.util.ArrayList;
import java.util.Arrays;

import render.objects.*;

public class ScadFile implements ScadObject {
	private String Filetext;
	

	
	
//	public double calculateD(double a){
//		return((pinDistance+pinNRadius)/Math.sin(a/2)-pinNRadius);
//	}
//	
//	public ScadObject getPinPositive(double a){
//		double d = calculateD(a);
//		if (d<(pinNRadius+pinMinLength)){
//			d=pinNRadius+pinMinLength;
//		}
//		Cube c1 = new Cube(d, pinNWidth, 1, true);
//		Cylinder cyl1 = new Cylinder(1, pinNRadius-e, pinNRadius-e, true);
//		Translate tcyl1 = new Translate(cyl1, (0.5*d+pinNRadius-e), 0, 0);
//		
//		Cube fill = new Cube(1, pinNWidth, 1, true);
//		Translate tfill = new Translate(fill, d*0.5, 0, 0);
//		Union pinUnion = new Union(new ArrayList<>(Arrays.asList(c1,tcyl1,tfill)));
//		Translate result = new Translate(pinUnion, -(pinNRadius-e), 0, 0);
//		
//		
//		return result;
//	}
	

	public String getFiletext() {
		return Filetext;
	}

	public void setFiletext(String filetext) {
		Filetext = filetext;
	}

	@Override
	public String printCommand() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
