
package com.neu.edu.algoassignment4.part1;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

class Node {
	public String value;
	public Node left;
	public Node right;
}

class Tree {
/*
 * Function to insert the data into the binary Tree
 */
	public Node insert(Node root, String data) {
		if (root == null) {
			root = new Node();
			root.value = data;
		} else if (data.compareTo(root.value) > 0) {
			root.left = insert(root.left, data);
		} else {
			root.right = insert(root.right, data);
		}

		return root;
	}
	/*
	 * Function to traverse till the end of the Binary Search Tree
	 */

	public void traverse(Node root) {
		if (root == null) {
			return;
		}

		traverse(root.left);
		traverse(root.right);
		if (root.left == null && root.right == null) {
			// System.out.println(root.value);
		}
	}

	/*
	 * Function to find the height of the Binary Search Tree
	 */
	int bstHeight(Node root) {
		if (root == null) {
			return -1;
		}

		int leftHeight = bstHeight(root.left);
		int hightHeigh = bstHeight(root.right);

		if (leftHeight > hightHeigh) {
			return leftHeight + 1;
		} else {
			return hightHeigh + 1;
		}
	}

}

class BinarySearchTree {
	public static void main(String[] args) {
		int shuffleConstant = 10;

		System.out.println("Enter the size of the String\n");
		Scanner reader = new Scanner(System.in);
		int k = reader.nextInt();
		System.out.println("Enter the number of elements of the BST\n");
		Scanner reader1 = new Scanner(System.in);
		int sizeOfArray = reader1.nextInt();
		String[] inArray = new String[sizeOfArray];
		for (int i = 0; i < sizeOfArray; i++) {

			inArray[i] = randomString(k);
		}

		if (sizeOfArray > 1000) {
			System.out.printf("The value of nodes is greater than 1000. Shuffling for %d times\n", sizeOfArray);
			for (int i = 0; i < sizeOfArray; i++) {
				shuffleArray(inArray);
				// printShuffledArr(inArray);
			}
		} else {
			System.out.println("The value of nodes is less than 1000. Shuffling for 1000 times by default");
			for (int i = 0; i < 1000; i++) {
				shuffleArray(inArray);

			}
		}
		Node root = null;
		Tree binarySearchTree = new Tree();
		final int SIZE = sizeOfArray;
		String[] a = inArray;
		System.out.printf("The Tree has been created with %d nodes \n", SIZE);
		for (int i = 0; i < SIZE; i++) {
			root = binarySearchTree.insert(root, a[i]);
		}

		System.out.println("Traversing the tree till the last node");
		binarySearchTree.traverse(root);
		int height = binarySearchTree.bstHeight(root);
		System.out.println("Height of the tree is " + height);
	}

	static void shuffleArray(String[] ar) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			String a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	static void printShuffledArr(String[] inArray) {
		for (int i = 0; i < inArray.length; i++) {
			System.out.print(inArray[i] + " ");
		}
		System.out.println();
	}

	static String randomString(int k) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String s = "";
		int maxSize = k;
		Random random = new Random();
		int randomLen = 1 + random.nextInt(maxSize);
		for (int i = 0; i < randomLen; i++) {
			char c = alphabet.charAt(random.nextInt(26));
			s += c;
		}
		return s;
	}

}