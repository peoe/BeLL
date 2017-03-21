package rend;

public class ParameterController {

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
	private static double e, cornerRadius, pinMinLength, pinNWidth, pinNRadius, pinPWidth, pinPRadius, pinDistance, height, pinHeight;

	// the constant for defining the width of all walls
	private static final double wallWidth = 6.0;
	
	// getter - setter
	// pinPWidth
	/**
	 * Returns the parameter corresponding to the width of the positive pin.
	 * @return the width of the positive pin
	 */
	public static double getPinPWidth() {
		return pinPWidth;
	}

	// pinPRadius
	/**
	 * Returns the parameter corresponding to the radius of the positive pin.
	 * @return the radius of the positive pin
	 */
	public static double getPinPRadius() {
		return pinPRadius;
	}

	// epsilon
	/** 
	 * Returns the parameter corresponding to the value of epsilon.
	 * @return the value of epsilon
	 */
	public static double getE() {
		return e;
	}
	
	// cornerRadius
	/**
	 * Returns the parameter corresponding to the corner radius.
	 * @return the corner radius 
	 */
	public static double getCornerRadius() {
		return cornerRadius;
	}

	// pinMinLength
	/**
	 * Returns the parameter corresponding to the minimum pin length.
	 * @return the minimum pin length
	 */
	public static double getPinMinLength() {
		return pinMinLength;
	}

	// pinNWidth
	/**
	 * Returns the parameter corresponding to the negative pin width.
	 * @return the negative pin width
	 */
	public static double getPinNWidth() {
		return pinNWidth;
	}

	// pinNRadius
	/**
	 * Returns the parameter corresponding to the negative pin radius.
	 * @return the negative pin radius
	 */
	public static double getPinNRadius() {
		return pinNRadius;
	}

	// pinDistance
	/**
	 * Returns the parameter corresponding to the pin distance.
	 * @return the pin distance
	 */
	public static double getPinDistance() {
		return pinDistance;
	}

	// height
	/**
	 * Returns the parameter corresponding to the height.
	 * @return the height
	 */
	public static double getHeight() {
		return height;
	}

	// pinHeight
	/**
	 * Returns the parameter corresponding to the pin height.
	 * @return the pin height
	 */
	public static double getPinHeight() {
		return pinHeight;
	}

	// wallWidth
	/**
	 * Returns the parameter corresponding to the wall width.
	 * @return the wall width
	 */
	public static double getWallwidth() {
		return wallWidth;
	}
	
	// setting all params
	/**
	 * Sets all the params to the given values.
	 * @param E the value for the epsilon parameter
	 * @param CornerRadius the value for the corner radius parameter
	 * @param PinMinLength the value for the minimum pin length parameter
	 * @param PinNWidth the value for the negativ epin width parameter
	 * @param PinNRadius the value for the negative pin radius parameter
	 * @param PinDistance the value for the pin distance parameter
	 * @param Height the value for the height parameter
	 * @param PinHeight the value for the pin height parameter
	 */
	public static void setParams(double E, double CornerRadius, double PinMinLength, double PinNWidth, double PinNRadius, double PinDistance, double Height, double PinHeight){
		e = E;
		cornerRadius = CornerRadius;
		pinMinLength = PinMinLength;
		pinNWidth = PinNWidth;
		pinNRadius = PinNRadius;
		pinDistance = PinDistance;
		height = Height;
		pinHeight = PinHeight;
		pinPRadius = pinNRadius - e;
		pinPWidth = pinNWidth - 2*e;
		
	}

}
