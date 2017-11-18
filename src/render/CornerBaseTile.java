package render;

import render.objects.Cylinder;

public class CornerBaseTile implements ScadObject {

	// height of the cylinder
	private double height;
	// intern epsilon
	private double epsilon;
	//Parameters
	private Params params;

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

	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	/**
	 * Constructor of CornerBaseTile class
	 * @param height height of the Object
	 * @param epsilon Epsilon margin
	 */
	public CornerBaseTile(double height, double epsilon, Params params) {
		this.height = height;
		this.epsilon = epsilon;
		this.params = params;
	}

	// returns the corner cylinder
	/**
	 * Returns a primitive cylinder with calculated epsilon difference
	 * @return
	 */
	public ScadObject getBaseTile() {
		return new Cylinder(getHeight(), params.getCornerRadius(), true).resize(0, 0, getEpsilon());
	}
	
	@Override 
	public String toString(){
		return getBaseTile().toString();
	}

}
