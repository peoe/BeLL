package rend.objects;

import java.util.ArrayList;
import java.util.Locale;

import rend.ScadObject;

public class Rotate implements ScadObject{
	
	private ArrayList<ScadObject> objects;
	private double a;
	private int x, y, z;
	
	final static String rotate = "rotate(%1$.2f,[%2$d,%3$d,%4$d]){\n%5$s}";

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
	public String printCommand() {
		String objectsprint = "";
		for (ScadObject o : objects){
			objectsprint =  objectsprint.concat("\t" + o.printCommand());
		}
		String s = String.format(Locale.UK, rotate, a, x, y, z, objectsprint );
		return s;
	}

}
