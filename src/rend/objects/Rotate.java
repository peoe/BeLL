package rend.objects;

import java.util.ArrayList;
import java.util.Locale;

import com.sun.accessibility.internal.resources.accessibility;

import rend.ScadObject;

public class Rotate implements ScadObject{
	
	private ArrayList<ScadObject> objects;
	private double a;
	private int x, y, z;

	public Rotate(ArrayList<ScadObject> objects, double a, int x, int y, int z) {
		super();
		this.objects = objects;
		this.a = a;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Rotate(ScadObject object, double a, int x, int y, int z) {
		super();
		this.objects = new ArrayList<>(); 
		objects.add(object);
		this.a = a;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String printcommand() {
		String objectsprint = "";
		for (ScadObject o : objects){
			objectsprint =  objectsprint.concat("\t" + o.printcommand());
		}
		String s = String.format(Locale.UK, rotate, a, x, y, z, objectsprint );
		return s;
	}

}
