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
	private double e, cornerRadius, pinMinLength, pinPWidth, pinPRadius, pinDistance, height, pinHeight, basePlateHeight, basePlatePinCircleHeight, wallWidth;

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
	public double getE() {
		return e;
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
	public double getWallwidth() {
		return wallWidth;
	}
  
  public double getBasePlatePinCircleHeight() {
		return basePlatePinCircleHeight;
	}
	
	public double getBasePlateHeight() {
		return basePlateHeight;
	}

	// setting all params
	/**
	 * 
	 * @param E
	 * @param CornerRadius
	 * @param PinMinLength
	 * @param PinPWidth
	 * @param PinPRadius
	 * @param PinDistance
	 * @param Height
	 * @param PinHeight
	 * @param BasePlateHeight
	 * @param BasePlatePinCircleHeight
	 */
	public Params(double E, double CornerRadius, double PinMinLength, double PinPWidth, double PinPRadius, double PinDistance, double Height, double PinHeight, double BasePlateHeight, double BasePlatePinCircleHeight){
		e = E;
		cornerRadius = CornerRadius;
		pinMinLength = PinMinLength+CornerRadius;
		pinDistance = PinDistance;
		height = Height;
		pinHeight = PinHeight;
		pinPRadius = PinPRadius;
		pinPWidth = PinPWidth;
		basePlateHeight = BasePlateHeight;
		basePlatePinCircleHeight = BasePlatePinCircleHeight;
		wallWidth = 6.0;
		
	}

}
