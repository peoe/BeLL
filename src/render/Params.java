package render;

public class Params {

	/*
	 * e - epsilon, the distance used for measuring the distance in between objects
	 * cornerRadius - the radius of the inner corner ring
	 * pinMinLength - the minimum length of a pin
	 * pinNWidth - the width of the negative pin 
	 * pinNRadius - the radius of the negative pin
	 * pinPWidth - the width of the positive pin 
	 * pinPRadius - the radius of the positive pin
	 * pinDistance - the distance from the pin to the wall
	 * height - the height of the corner 
	 * pinHeight - the height of the pin
	 */
	private double epsilon, cornerRadius, pinMinLength, pinPWidth, pinPRadius, pinDistance, height, pinHeight, basePlateHeight, basePlatePinCircleHeight, wallWidth, maxPrintWidth, maxPrintHeight;;

	// the constant for defining the width of all walls
	//	private static final double wallWidth = 6.0;
	
	// getter - setter
	// pinPWidth
	/**
	 * Returns the parameter corresponding to the width of the positive pin.
	 * @return the width of the positive pin
	 */
	public double getPinPWidth() {
		return pinPWidth;
	}

	// pinPRadius
	/**
	 * Returns the parameter corresponding to the radius of the positive pin.
	 * @return the radius of the positive pin
	 */
	public double getPinPRadius() {
		return pinPRadius;
	}

	// epsilon
	/** 
	 * Returns the parameter corresponding to the value of epsilon.
	 * @return the value of epsilon
	 */
	public double getEpsilon() {
		return epsilon;
	}
	
	// cornerRadius
	/**
	 * Returns the parameter corresponding to the corner radius.
	 * @return the corner radius 
	 */
	public double getCornerRadius() {
		return cornerRadius;
	}

	// pinMinLength
	/**
	 * Returns the parameter corresponding to the minimum pin length.
	 * @return the minimum pin length
	 */
	public double getPinMinLength() {
		return pinMinLength;
	}

	// pinDistance
	/**
	 * Returns the parameter corresponding to the pin distance.
	 * @return the pin distance
	 */
	public double getPinDistance() {
		return pinDistance;
	}

	// height
	/**
	 * Returns the parameter corresponding to the height.
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	// pinHeight
	/**
	 * Returns the parameter corresponding to the pin height.
	 * @return the pin height
	 */
	public double getPinHeight() {
		return pinHeight;
	}

	// wallWidth
	/**
	 * Returns the parameter corresponding to the wall width.
	 * @return the wall width
	 */
	public double getWallWidth() {
		return wallWidth;
	}
  
	//
  public double getBasePlatePinCircleHeight() {
		return basePlatePinCircleHeight;
	}
	//height of the basePlates
	public double getBasePlateHeight() {
		return basePlateHeight;
	}
	
	//PrintWidth
	public double getMaxPrintWidth() {
		return maxPrintWidth;
	}

	//PrintHeight
	public double getMaxPrintHeight() {
		return maxPrintHeight;
	}


	// setting all parameters
	/**
	 * 
	 * @param epsilon
	 * @param cornerRadius
	 * @param pinMinLength
	 * @param pinPWidth
	 * @param pinPRadius
	 * @param pinDistance
	 * @param height
	 * @param pinHeight
	 * @param basePlateHeight
	 * @param basePlatePinCircleHeight
	 * @param wallWidth
	 * @param maxPrintWidth
	 * @param maxPrintHeight
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

}
