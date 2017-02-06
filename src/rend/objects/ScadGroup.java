package rend.objects;

import java.util.ArrayList;

import rend.ScadObject;

public class ScadGroup implements ScadObject{
	
	private ArrayList<ScadObject> objects;

	public ScadGroup(ArrayList<ScadObject> objects) {
		super();
		this.objects = objects;
	}
	
	public ScadGroup(ScadObject object) {
		super();
		this.objects =new ArrayList<>();
		this.objects.add(object);
	}

	public ArrayList<ScadObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<ScadObject> objects) {
		this.objects = objects;
	}
	

	@Override
	public String printcommand() {
		String s = "";
		for (ScadObject o : objects){
		s = s.concat(o.printcommand());
		}
		return s;
	}

}
