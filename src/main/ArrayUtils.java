package main;

public class ArrayUtils {

	// getting the smallest index where values[i] is still smaller than x
	/**
	 * Returns the smallest index where the value in the array is still smaller
	 * than the x value.
	 * 
	 * @param values
	 *            the array of values
	 * @param x
	 *            the x value
	 * @return the index where x fits in
	 */
	public static int sortInValue(double[] values, double x) {
		if (values[0] < x) {
			int i = 0;
			
			// x > values[i] and i < values.length
			while (i < values.length && values[i] < x) {
				i++;
			}
			
			return i;
		} else {
			return 0;
		}	
	}

}
