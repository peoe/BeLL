package render.objects;

import java.util.ArrayList;
import java.util.Locale;

import render.ScadObject;

public class Offset implements ScadObject{
	
	//offset amount
	private double delta;
	//ScadObject to transform
	private ArrayList<ScadObject> objects;
	
	//scad string
	final static String OFFSET = "offset(%1$.3f){%2$s}"; 

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}
	

	public ArrayList<ScadObject> getObjects() {
		return objects;
	}

	public void setObj(ArrayList<ScadObject> obj) {
		this.objects = obj;
	}

	public Offset(ArrayList<ScadObject> obj, double delta){
		this.delta = delta;
		this.objects = obj;
	}
	
	public Offset(ScadObject obj, double delta){
		this.delta = delta;
		this.objects = new ArrayList<>();
		this.objects.add(obj);
	}
	
	public String toString(){
		String objectsToString = "";
		for (ScadObject o : objects) {
			objectsToString = objectsToString.concat("\t" + o.toString());
		}
		return String.format(Locale.UK, OFFSET, getDelta(), objectsToString);
	}


}
