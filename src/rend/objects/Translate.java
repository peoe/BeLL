package rend.objects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

import rend.ScadObject;

public class Translate implements ScadObject{
	
	private ArrayList<ScadObject> objects;
	private double x,y,z;
	

	public Translate(ArrayList<ScadObject> object, double x, double y, double z) {
		super();
		this.objects = object;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Translate(ScadObject object, double x, double y, double z) {
		super();
		this.objects = new ArrayList<>();
		this.objects.add(object);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public ArrayList<ScadObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<ScadObject> objects) {
		this.objects = objects;
	}

	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}


	public double getZ() {
		return z;
	}


	public void setZ(double z) {
		this.z = z;
	}


	@Override
	public String printcommand() {
		String objectsprint = "";
		for (ScadObject o : objects){
			objectsprint =  objectsprint.concat("\t" + o.printcommand());
		}
		String s = String.format(Locale.UK, translate, x, y, z, objectsprint );
		return s;
	}
	
	

}
