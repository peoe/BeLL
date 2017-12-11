package render;

public class Params {

	/*
	 * epsilon - the distance between objects cornerRadius - the radius of inner
	 * corner ring pinMinLength - the minimum length of a pin pinPWidth - the
	 * width of the positive pin pinPRadius - the radius of the positive pin
	 * pinDistance - the distance from pin to the wall height - the height of
	 * the model pinHeight - the height of the pin basePlateHeight - the height
	 * of the baseplate basePlatePinCircleHeight - the height of the baseplate
	 * pin circle wallWidth - the width of the wall maxPrintWidth - the width of
	 * the maximum printing area maxPrintHeight - the height of the maximum
	 * printing area
	 */
	private double epsilon, cornerRadius, pinMinLength, pinPWidth, pinPRadius, pinDistance, height, pinHeight,
			basePlateHeight, basePlatePinCircleHeight, wallWidth, maxPrintWidth, maxPrintHeight;;

	/**
	 * Constructor of the Params class.
	 * 
	 * @param epsilon
	 *            the distance between objects
	 * @param cornerRadius
	 *            the radius of inner corner ring
	 * @param pinMinLength
	 *            the minimum length of a pin
	 * @param pinPWidth
	 *            the width of the positive pin
	 * @param pinPRadius
	 *            the radius of the positive pin
	 * @param pinDistance
	 *            the distance from pin to the wall
	 * @param height
	 *            the height of the model
	 * @param pinHeight
	 *            the height of the pin
	 * @param basePlateHeight
	 *            the height of the baseplate
	 * @param basePlatePinCircleHeight
	 *            the height of the baseplate pin circle
	 * @param wallWidth
	 *            the width of the wall
	 * @param maxPrintWidth
	 *            the width of the maximum printing area
	 * @param maxPrintHeight
	 *            the height of the maximum printing area
	 */
	public Params(double epsilon, double cornerRadius, double pinMinLength, double pinPWidth, double pinPRadius,
			double pinDistance, double height, double pinHeight, double basePlateHeight,
			double basePlatePinCircleHeight, double wallWidth, double maxPrintWidth, double maxPrintHeight) {
		super();
		this.epsilon = epsilon;
		this.cornerRadius = cornerRadius;
		this.pinMinLength = pinMinLength + cornerRadius;
		this.pinPWidth = pinPWidth;
		this.pinPRadius = pinPRadius;
		this.pinDistance = pinDistance;
		this.height = height;
		this.pinHeight = pinHeight;
		this.basePlateHeight = basePlateHeight;
		this.basePlatePinCircleHeight = basePlatePinCircleHeight;
		this.wallWidth = wallWidth;
		this.maxPrintWidth = maxPrintWidth;
		this.maxPrintHeight = maxPrintHeight;
	}

	// getter - setter
	/**
	 * Returns the parameter corresponding to the width of the positive pin.
	 * 
	 * @return width of the positive pin
	 */
	public double getPinPWidth() {
		return pinPWidth;
	}

	/**
	 * Returns the parameter corresponding to the radius of the positive pin.
	 * 
	 * @return radius of the positive pin
	 */
	public double getPinPRadius() {
		return pinPRadius;
	}

	/**
	 * Returns the parameter corresponding to the value of epsilon.
	 * 
	 * @return value of epsilon
	 */
	public double getEpsilon() {
		return epsilon;
	}

	/**
	 * Returns the parameter corresponding to the corner radius.
	 * 
	 * @return corner radius
	 */
	public double getCornerRadius() {
		return cornerRadius;
	}

	/**
	 * Returns the parameter corresponding to the minimum pin length.
	 * 
	 * @return minimum pin length
	 */
	public double getPinMinLength() {
		return pinMinLength;
	}

	/**
	 * Returns the parameter corresponding to the pin distance.
	 * 
	 * @return pin distance
	 */
	public double getPinDistance() {
		return pinDistance;
	}

	/**
	 * Returns the parameter corresponding to the height.
	 * 
	 * @return height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Returns the parameter corresponding to the pin height.
	 * 
	 * @return pin height
	 */
	public double getPinHeight() {
		return pinHeight;
	}

	/**
	 * Returns the parameter corresponding to the wall width.
	 * 
	 * @return wall width
	 */
	public double getWallWidth() {
		return wallWidth;
	}

	/**
	 * Returns the parameter corresponding to the height of the baseplate pin
	 * circle.
	 * 
	 * @return height of the baseplate pin circle
	 */
	public double getBasePlatePinCircleHeight() {
		return basePlatePinCircleHeight;
	}

	/**
	 * Returns the parameter corresponding to the height of the baseplate.
	 * 
	 * @return height of the baseplate
	 */
	public double getBasePlateHeight() {
		return basePlateHeight;
	}
  
	/**
	 * Returns the parameter corresponding to the width of the wall.
	 * 
	 * @return wall width
	 */
	public double getWallWidth() {
		return wallWidth;
	}

	/**
	 * Returns the parameter corresponding to the maximum print width.
	 * 
	 * @return maximum print width
	 */
	public double getMaxPrintWidth() {
		return maxPrintWidth;
	}

	/**
	 * Returns the parameter corresponding to the maximum print height.
	 * 
	 * @return maximum print height
	 */
	public double getMaxPrintHeight() {
		return maxPrintHeight;
	}

}
