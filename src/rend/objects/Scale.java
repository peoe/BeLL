package rend.objects;

import java.util.ArrayList;
import java.util.Locale;

import rend.ScadObject;

public class Scale implements ScadObject{

	private ArrayList<ScadObject> objects;
	private double x, y, z;
	
	final static String scale = "scale([%1$.3f, %2$.3f, %3$.3f]){\n%4$s}";
	
	public Scale(ArrayList<ScadObject> objects, double x, double y, double z) {
		super();
		this.objects = objects;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Scale(ScadObject object, double x, double y, double z) {
		super();
		this.objects = new ArrayList<>(); 
		this.objects.add(object);
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
		String s = String.format(Locale.UK, scale, x, y, z, objectsprint);
		return s;
	}

}
