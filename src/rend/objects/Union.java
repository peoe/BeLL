package rend.objects;

import java.util.ArrayList;
import java.util.Locale;

import rend.ScadObject;

public class Union implements ScadObject{
	
	private ArrayList<ScadObject> objects;
	
	final static String union = "union(){\n%s}";
	
	public Union(ArrayList<ScadObject> objects) {
		super();
		this.objects = objects;
	}

	public ArrayList<ScadObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<ScadObject> objects) {
		this.objects = objects;
	}

	@Override
	public String printCommand() {
		String objectsprint = "";
		for (ScadObject o : objects){
			objectsprint =  objectsprint.concat("\t" + o.printCommand());
		}
		String s = String.format(Locale.UK, union, objectsprint );
		return s;
	}
	
	

}
