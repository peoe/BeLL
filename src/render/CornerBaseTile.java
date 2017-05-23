package render;

import render.objects.Cylinder;

public class CornerBaseTile implements ScadObject {

	// height of the cylinder
	private double height;
	// epsilon :0
	private double epsilon;

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getEpsilon() {
		return epsilon;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

	/**
	 * Constructor of CornerBaseTile class
	 * @param height height of the Object
	 * @param epsilon Epsilon margin
	 */
	public CornerBaseTile(double height, double epsilon) {
		this.height = height;
		this.epsilon = epsilon;
	}

	// returns the corner cylinder
	/**
	 * Returns a primitive cylinder with calculated epsilon difference
	 * @return
	 */
	public ScadObject getBaseTile() {
		return new Cylinder(getHeight(), Params.getCornerRadius(), true).resize(0, 0, getEpsilon());
	}
	
	@Override 
	public String toString(){
		return getBaseTile().toString();
	}

}
