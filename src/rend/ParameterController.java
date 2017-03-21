package rend;

public class ParameterController {

	private static double e, cornerRadius, pinMinLength, pinNWidth, pinNRadius, pinPWidth, pinPRadius, pinDistance, height, pinHeight,basePlateHeight, basePlatePinCircleHeight;
	public static double getBasePlatePinCircleHeight() {
		return basePlatePinCircleHeight;
	}

	public static double getPinPWidth() {
		return pinPWidth;
	}

	public static double getPinPRadius() {
		return pinPRadius;
	}

	public static double getE() {
		return e;
	}

	public static double getCornerRadius() {
		return cornerRadius;
	}

	public static double getPinMinLength() {
		return pinMinLength;
	}

	public static double getPinNWidth() {
		return pinNWidth;
	}

	public static double getPinNRadius() {
		return pinNRadius;
	}

	public static double getPinDistance() {
		return pinDistance;
	}

	public static double getHeight() {
		return height;
	}

	public static double getPinHeight() {
		return pinHeight;
	}

	public static double getWallwidth() {
		return wallWidth;
	}

	private static final double wallWidth = 6.0;
	
	public static double getBasePlateHeight() {
		return basePlateHeight;
	}

	public static void setParams(double E, double CornerRadius, double PinMinLength, double PinNWidth, double PinNRadius, double PinDistance, double Height, double PinHeight, double BasePlateHeight, double BasePlatePinCircleHeight){
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
		basePlateHeight = BasePlateHeight;
		basePlatePinCircleHeight = BasePlatePinCircleHeight;
		
	}

}
