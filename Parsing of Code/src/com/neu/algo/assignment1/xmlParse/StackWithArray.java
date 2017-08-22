package com.neu.algo.assignment1.xmlParse;

import java.util.Arrays;

public class StackWithArray {
	private static int size = 0;
	private String[] arr;

	public StackWithArray() {

	}

	public StackWithArray(int size) {
		arr = new String[size];

	}

	/*
	 * This function is to resize the array. The size of the array is fixed, and
	 * this will allow resizing customization for the array.
	 */
	public String[] stackArrayResize(String[] arr) {
		if (arr != null && arr.length > 0) {
			int arrSize = (arr.length) * 2;
			arr = Arrays.copyOf(arr, arrSize);
			return arr;
		} else {
			return null;
		}
	}

	public String pop() {
		// insert code here and change the return value
		if ((size - 1) >= 0) {
			String pop = arr[size - 1]; // The value that is being popped
			arr[size - 1] = null;
			size--;
			return pop;// Return the value that has been removed from the stack
		} else {
			return "Stack is empty";
		}
	}

	public String peek() {
		// insert code here and change the return value
		if ((size - 1) >= 0) {
			String peekValue = arr[size - 1];
			// counter--;
			return peekValue;
		} else {
			return "Stack empty";
		}

	}

	public void push(String input) {
		// insert code here
		if (size < arr.length) {
			arr[size] = input;
			size++;
		} else if (size >= arr.length) {
			// System.out.println("counter in else if section of push:
			// "+counter);
			arr = stackArrayResize(arr);
			// System.out.println("New array size is: "+arr.length);
			arr[size] = input;
			size++;
		}
	}

	public boolean isEmpty() {
		// insert code here and change the return value
		if (arr.length == 0 || size == 0) {
			return true;
		} else {

			return false;
		}
	}
}
