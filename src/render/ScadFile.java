package render;

import java.util.ArrayList;
import java.util.Arrays;

import render.objects.*;

public class ScadFile implements ScadObject {
	private ArrayList<ScadObject> objects = new ArrayList<>();

	public ArrayList<ScadObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<ScadObject> objects) {
		this.objects = objects;
	}
	
	public ScadFile(){
		
	}

	@Override
	public String toString() {
		return new Union(objects).toString();
	}

}
