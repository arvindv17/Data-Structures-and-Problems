package com.neu.edu.algoassignment4.part3;

import java.util.*;

public class BinaryTree {
	static Map<Integer, List<BinaryTree>> resultHashMap = new Hashtable<Integer, List<BinaryTree>>();
	private String data;
	private BinaryTree left;
	private BinaryTree right;
	private int level = 0;
	static int count;
	static int node;
	static int[] levelArray = new int[10];

	public BinaryTree() {
		this.data = null;
		this.left = null;
		this.right = null;
		this.level = 0;
	}

	public BinaryTree(String data) {
		this.data = data;
		this.left = null;
		this.right = null;
		this.level = 0;
	}

	public BinaryTree insert(BinaryTree root, String data) {
		if (root == null) {
			root = new BinaryTree();
			root.data = data;
		} else if (data.compareTo(root.data) > 0) {
			root.left = insert(root.left, data);
		} else {
			root.right = insert(root.right, data);
		}

		return root;
	}

	public void compareString(BinaryTree treeNode, String searchNode) {

		Queue<BinaryTree> q = new LinkedList<BinaryTree>();
		int levelNodes = 0;
		int i = 0;
		if (treeNode == null)
			return;
		q.add(treeNode);
		while (!q.isEmpty()) {
			i++;
			levelNodes = q.size();
			List<BinaryTree> vals = new ArrayList<BinaryTree>();
			while (levelNodes > 0) {
				BinaryTree n = (BinaryTree) q.remove();
				if (n.data.contains(searchNode)) {
					count++;
					vals.add(n);
					// resultHashMap.put(i,n);
				}
				System.out.print(" " + n.data);
				if (n.left != null)
					q.add(n.left);
				if (n.right != null)
					q.add(n.right);
				levelNodes--;
				// System.out.println(" is:" + i);
			}
			resultHashMap.put(i, vals);
			System.out.println("");
		}

	}

	public static void main(String args[]) {

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
		BinaryTree root = null;
		BinaryTree binarySearchTree = new BinaryTree();
		final int SIZE = sizeOfArray;
		String[] a = inArray;
		System.out.printf("The Tree has been created with %d nodes \n\n\n", SIZE);
		for (int i = 0; i < SIZE; i++) {
			root = binarySearchTree.insert(root, a[i]);
			System.out.print(a[i] + " ");
		}
		int counter = 0;
		Scanner reader2 = new Scanner(System.in);
		System.out.println("\nEnter the value that you wish to search");

		String searchTree = reader2.nextLine();
		root.compareString(root, searchTree);
//		System.out.println(count);
		int length_key;
		for (Map.Entry<Integer, List<BinaryTree>> entry : resultHashMap.entrySet()) {
			Integer key = entry.getKey();
			List<BinaryTree> values = entry.getValue();

//			for (int i = 0; i < values.size(); i++) {
//				counter++;
//				
//			}
//			System.out.print("Value in the tree : "+values.get(i).data);
			System.out.print(" Level :" + key+" ");
			System.out.print("Count of the occurance : " +values.size()+"\n");
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