/**
 * 
 */
package com.neu.edu.algoassignment4.part2;

/**
 * @author Arvind
 *
 */

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

class Node {

	String data;
	Node left;
	Node right;

	Node(String d) {
		data = d;
		left = right = null;
	}
}

public class BinarySearchTree {
	static Node root;

	/*
	 * Sorting the Binary search tree to make it Balanced
	 */
	Node sortedArrayToBST(String arr[], int start, int end) {

		if (start > end) {
			return null;
		}

		/*
		 * For a balanced BST, we need to find the middle element to make it
		 * sorted. This way the root will be the middle element.
		 */
		int mid = (start + end) / 2;
		Node node = new Node(arr[mid]);

		node.left = sortedArrayToBST(arr, start, mid - 1);

		node.right = sortedArrayToBST(arr, mid + 1, end);

		return node;
	}

	void preOrder(Node node) {
		if (node == null) {
			return;
		}
		System.out.print(node.data + " ");
		preOrder(node.left);
		preOrder(node.right);
	}

	public Node search(Node root, String key) {

		if (root == null || root.data.equals(key))
			return root;

		if (root.data.compareTo(key) == 1) {
			return search(root.left, key);
		}

		else if (root.data.compareTo(key) == 1) {
			return search(root.right, key);
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		KnuthShuffle ks = new KnuthShuffle();
		System.out.println("Enter the size of the String\n");
		Scanner reader = new Scanner(System.in);
		int k = reader.nextInt();
		System.out.println("Enter the number of elements of the BST\n");
		Scanner reader1 = new Scanner(System.in);
		int sizeOfArray = reader1.nextInt();
		String[] inArray = new String[sizeOfArray];
		for (int i = 0; i < sizeOfArray; i++) {

			inArray[i] = ks.randomString(k);
		}

		System.out.println("Shuffling for 1000 times by default");
		for (int i = 0; i < 1000; i++) {
			ks.shuffleArray(inArray);
		}

		Arrays.sort(inArray);

		// System.out.println("String array sorted (case sensitive)");

		// print sorted elements
		for (int j = 0; j < inArray.length; j++) {
			System.out.print(inArray[j] + " ");
		}

		BinarySearchTree bst = new BinarySearchTree();
		int n = inArray.length;

		root = bst.sortedArrayToBST(inArray, 0, n - 1);

		// System.out.println("root :" + root.data);
		System.out.print("root :" + root.data + " ");
		System.out.println("\nPreorder traversal of constructed BST");
		bst.preOrder(root);
		// double timeComplexity=log2(sizeOfArray);
		// System.out.println("The time complexity for this balanced tree search
		// is"+timeComplexity);

		/*
		 * System.out.println("\nEnter the string to be searched\n"); Scanner r
		 * = new Scanner(System.in); String search = r.nextLine(); Node search1
		 * = bst.search(root, search); if (search1 == null) {
		 * System.out.println("Value could not be found in the tree");
		 * 
		 * } else { System.out.println("The value is present in the tree "); }
		 */

	}

	private static double log2(int n) {
		// TODO Auto-generated method stub
		return (Math.log(n) / Math.log(2));

	}
}

class KnuthShuffle {

	static void shuffleArray(String[] ar) {

		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);

			String a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
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