package io;

import java.util.ArrayList;

import java.lang.reflect.InvocationTargetException;

public class Quicksort<T> {

	// ArrayList to be sorted
	private ArrayList<T> elements;
	// String used for comparison property of sort
	private String comparisonField;

	/**
	 * Constructor of the Quicksort class.
	 * 
	 * @param e
	 *            the ArrayList of objects to be sorted
	 * @param c
	 *            the String indicating the sorting property
	 */
	public Quicksort(ArrayList<T> e, String c) {
		// add all elements of e to elements
		elements = new ArrayList<>();
		elements.addAll(e);

		// set up comparison property string
		comparisonField = c;
	}

	/**
	 * Returns a sorted ArrayList of the specified type.
	 * 
	 * @return sorted ArrayList of specified type
	 */
	public ArrayList<T> sortArray() {
		// sort
		quicksort(0, elements.size() - 1);

		// return sorted ArrayList
		return elements;
	}

	/**
	 * Sorts elements from lower index to higher index.
	 * 
	 * @param low
	 *            the lower index of the array
	 * @param high
	 *            the higher index of the array
	 */
	private void quicksort(int low, int high) {
		int i = low, j = high;
		// getting the pivot element index
		int pivot = low + (high - low) / 2;

		// dividing into two lists
		while (i <= j) {
			// if current value of left list smaller than pivot element get next
			// element
			while (getField(i) < getField(pivot)) {
				i++;
			}

			// if current value of right list larger than pivot element get next
			// element
			while (getField(j) > getField(pivot)) {
				j--;
			}

			// swap values if left value larger and right value smaller than
			// pivot element
			if (i <= j) {
				exchange(i, j);
				i++;
				j--;
			}
		}

		// recursion
		if (low < j)
			quicksort(low, j);
		if (i < high)
			quicksort(i, high);
	}

	/**
	 * Exchanges two elements within the ArrayList.
	 * 
	 * @param i
	 *            the index of the first element
	 * @param j
	 *            the index of the second element
	 */
	private void exchange(int i, int j) {
		T temp = getElements().get(i);
		getElements().set(i, getElements().get(j));
		getElements().set(j, temp);
	}

	/**
	 * Returns value of comparison property String of an element within the
	 * ArrayList at the given index.
	 * 
	 * @param index
	 *            the index of the item
	 * @return Double value of comparison property String
	 */
	private double getField(int index) {
		try {
			java.lang.reflect.Method method = getElements().get(index).getClass().getMethod(comparisonField);
			method.setAccessible(true);
			return (double) method.invoke(getElements().get(index));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return -1.0;
	}

	// getters - setters
	/**
	 * Returns an ArrayList of all elements.
	 * 
	 * @return ArrayList of all elements
	 */
	public ArrayList<T> getElements() {
		return elements;
	}

	/**
	 * Sets the ArrayList of elements.
	 * 
	 * @param elements
	 *            the ArrayList to be set as elements
	 */
	public void setElements(ArrayList<T> elements) {
		this.elements = elements;
	}

	/**
	 * Returns the comparison property String.
	 * 
	 * @return String of comparison property
	 */
	public String getComparisonField() {
		return comparisonField;
	}

	/**
	 * Sets the comparison property String.
	 * 
	 * @param comparisonField
	 *            the String to be set as comparison property
	 */
	public void setComparisonField(String comparisonField) {
		this.comparisonField = comparisonField;
	}

}
