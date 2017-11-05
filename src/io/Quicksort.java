package io;

import java.util.ArrayList;

import render.ScadObject;

import java.lang.reflect.Field;

public class Quicksort {
	//ArrayList which needs sorting
	private ArrayList<Object> elements;
	//property of sort
	private String comparisonField;
	
	public ArrayList<Object> getElements() {
		return elements;
	}

	public void setElements(ArrayList<Object> elements) {
		this.elements = elements;
	}

	public String getComparisonField() {
		return comparisonField;
	}

	public void setComparisonField(String comparisonField) {
		this.comparisonField = comparisonField;
	}

	/**
	 * Constructor
	 * @param e ArrayList which needs sorting
	 * @param c property of sort
	 */
	public Quicksort(ArrayList<Object> e, String c) {
		elements = e;
		comparisonField = c;
		quicksort(0, elements.size() - 1);
	}
	
	private void quicksort(int low, int high){
		int i = low, j = high;
		//get the pivot element index
		int pivot = low + (high-low)/2;

		//Dividing into two lists
		while(i <= j){
			// If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
			while (getField(i) < getField(pivot)) {
				i++;
			}
			// If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
			while (getField(j) > getField(pivot)) {
				j--;
			}
			
			//swap values if one value in the left is larger and one in the right is smaller than the pivot element
			if (i <= j) {
				exchange(i, j);
				i++;
				j--;
			}	
		}
		//Recursion
		if (low < j) quicksort(low, j);
		if (i < high) quicksort(i, high);
		}
	
	private void exchange(int i, int j){
		Object temp = getElements().get(i);
		getElements().set(i, getElements().get(j));
		getElements().set(j, temp);
	}
	
	private double getField(int index){
		try {
			Field field = getElements().get(index).getClass().getDeclaredField(comparisonField);
			field.setAccessible(true);
			return field.getDouble(getElements().get(index));
			//return (double) ((o.getClass())).(o.getClass().getDeclaredField(str));
			
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1.0;
	}
	
	
}
