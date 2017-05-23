package render.objects;

import java.util.ArrayList;
import java.util.Locale;

import render.ScadObject;

public class Offset implements ScadObject{
	
	//offset amount
	private double delta;
	//ScadObject to transform
	private ScadObject obj;
	
	//scad string
	final static String OFFSET = "offset(%1$.3f){%2$s}"; 

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}
	

	public ScadObject getObjects() {
		return obj;
	}

	public void setObj(ScadObject obj) {
		this.obj = obj;
	}

	/**
	 * Constructor of Offset class
	 * @param obj Specified 2D ScadObject 
	 * @param delta Offset Amount
	 */
	public Offset(ScadObject obj, double delta){
		this.delta = delta;
		this.obj = obj;
	}
	
	public String toString(){
		return String.format(Locale.UK, OFFSET, getDelta(), obj);
	}


}
