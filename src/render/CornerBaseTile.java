package render;

import render.objects.Cylinder;

public class CornerBaseTile implements ScadObject {
	// height of the cylinder
	private double height;
	// intern epsilon
	private double epsilon;
	// params
	private Params params;

	/**
	 * Constructor of the CornerBaseTile class using a height value, an epsilon
	 * value and a Params object.
	 * 
	 * @param height
	 *            the height of the CornerBaseTile
	 * @param epsilon
	 *            the epsilon value of the CornerBaseTile
	 * @param params
	 *            the Params object of the CornerBaseTile
	 */
	public CornerBaseTile(double height, double epsilon, Params params) {
		this.height = height;
		this.epsilon = epsilon;
		this.params = params;
	}

	/**
	 * Returns the Cylinder of the base tile.
	 * 
	 * @return ScadObject of CornerBaseTile
	 */
	public ScadObject getBaseTile() {
		return new Cylinder(getHeight(), params.getCornerRadius(), true).resize(0, 0, getEpsilon());
	}

	// getters - setters
	/**
	 * Returns the height of the CornerBaseTile.
	 * 
	 * @return height as Double value
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Sets the height value of the CornerBaseTile.
	 * 
	 * @param height
	 *            the value to be set as height of the CornerBaseTile
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Returns the epsilon value of the CornerBaseTile.
	 * 
	 * @return epsilon as Double value
	 */
	public double getEpsilon() {
		return epsilon;
	}

	/**
	 * Sets the epsilon value of the CornerBaseTile.
	 * 
	 * @param epsilon
	 *            the value to be set for the CornerBaseTile
	 */
	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

	/**
	 * Returns the Params object of the CornerBaseTile.
	 * 
	 * @return Params object of CornerBaseTile
	 */
	public Params getParams() {
		return params;
	}

	/**
	 * Sets the Params object of the CornerBaseTile.
	 * 
	 * @param params
	 *            the Params object to be set for the CornerBaseTile
	 */
	public void setParams(Params params) {
		this.params = params;
	}

	/**
	 * Returns a String of the CornerBaseTile used for creating it in OpenSCAD.
	 * 
	 * @return String of CornerBaseTile
	 */
	@Override
	public String toString() {
		return getBaseTile().toString();
	}

}
