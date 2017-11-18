package io;

import java.util.ArrayList;

import render.ScadObject;

import java.lang.reflect.Field;

public class Quicksort<T> {
	
	//ArrayList which needs sorting
	private ArrayList<T> elements;
	//property of sort
	private String comparisonField;
	
	public ArrayList<T> getElements() {
		return elements;
	}

	public void setElements(ArrayList<T> elements) {
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
	public Quicksort(ArrayList<T> e, String c) {
		elements = new ArrayList<>();
		elements.addAll(e);
		comparisonField = c;
		
	}
	
	public ArrayList<T> sortArray(){
		quicksort(0, elements.size() - 1);
		return elements;
		
	}
	/**
	 * Main Quicksort method
	 * @param low lower index of the array
	 * @param high higher index of the array
	 */
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
	/**
	 * exchanges two elements in the arrayList
	 * @param i index 1
	 * @param j index 2
	 * 
	 */
	private void exchange(int i, int j){
		T temp = getElements().get(i);
		getElements().set(i, getElements().get(j));
		getElements().set(j, temp);
	}
	
	/**
	 * returns value of the comparisonField of an element at a certain index
	 * @param index the index of the item
	 * @return double value of comparisonField of index element 
	 */
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
