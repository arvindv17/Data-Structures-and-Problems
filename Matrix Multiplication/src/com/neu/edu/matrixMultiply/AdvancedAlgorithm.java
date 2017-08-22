package com.neu.edu.matrixMultiply;

import java.util.Random;
import java.util.Scanner;

public class AdvancedAlgorithm {
	static int LEAF_SIZE = 16;
	private static int[][] a;
	private static int[][] b;
	private static int[][] c;

	public static int[][] traditionalMultiply(int[][] a, int[][] b) {
		int n = a.length;

		// initialise C
		int[][] C = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					C[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		return C;
	}

	private static int[][] add(int[][] A, int[][] B) {
		int n = A.length;
		int[][] C = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = A[i][j] + B[i][j];
			}
		}
		return C;
	}

	private static int[][] subtract(int[][] A, int[][] B) {
		int n = A.length;
		int[][] C = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = A[i][j] - B[i][j];
			}
		}
		return C;
	}

	/*
	 * Function to convert the size to the nearest power of 2 in Strassen.
	 */
	private static int roundToTwoPower(int n) {
		int log2 = (int) Math.ceil(Math.log(n) / Math.log(2));
		return (int) Math.pow(2, log2);
	}

	public static int[][] strassen(int[][] a, int[][] b) {

		/*
		 * The size has to be changed constantly and checked so that everything
		 * can be handled properly.
		 */
		int n = a.length;
		int m = roundToTwoPower(n);
		int[][] APrep = new int[m][m];
		int[][] BPrep = new int[m][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				APrep[i][j] = a[i][j];
				BPrep[i][j] = b[i][j];
			}
		}

		int[][] CPrep = strattsenRecursive(APrep, BPrep);
		int[][] C = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = CPrep[i][j];
			}
		}
		return C;
	}

	private static int[][] strattsenRecursive(int[][] A, int[][] B) {
		int n = A.length;

		if (n <= LEAF_SIZE) {
			return traditionalMultiply(A, B);
		} else {
			// initializing the new sub-matrices
			int newSize = n / 2;
			int[][] a11 = new int[newSize][newSize];
			int[][] a12 = new int[newSize][newSize];
			int[][] a21 = new int[newSize][newSize];
			int[][] a22 = new int[newSize][newSize];

			int[][] b11 = new int[newSize][newSize];
			int[][] b12 = new int[newSize][newSize];
			int[][] b21 = new int[newSize][newSize];
			int[][] b22 = new int[newSize][newSize];

			int[][] aResult = new int[newSize][newSize];
			int[][] bResult = new int[newSize][newSize];

			// dividing the matrices in 4 sub-matrices:
			for (int i = 0; i < newSize; i++) {
				for (int j = 0; j < newSize; j++) {
					a11[i][j] = A[i][j]; // top left
					a12[i][j] = A[i][j + newSize]; // top right
					a21[i][j] = A[i + newSize][j]; // bottom left
					a22[i][j] = A[i + newSize][j + newSize]; // bottom right

					b11[i][j] = B[i][j]; // top left
					b12[i][j] = B[i][j + newSize]; // top right
					b21[i][j] = B[i + newSize][j]; // bottom left
					b22[i][j] = B[i + newSize][j + newSize]; // bottom right
				}
			}

			aResult = add(a11, a22);
			bResult = add(b11, b22);
			int[][] p1 = strattsenRecursive(aResult, bResult);

			aResult = add(a21, a22);
			int[][] p2 = strattsenRecursive(aResult, b11);

			bResult = subtract(b12, b22);
			int[][] p3 = strattsenRecursive(a11, bResult);

			bResult = subtract(b21, b11); // b21 - b11
			int[][] p4 = strattsenRecursive(a22, bResult);

			aResult = add(a11, a12); // a11 + a12
			int[][] p5 = strattsenRecursive(aResult, b22);

			aResult = subtract(a21, a11);
			bResult = add(b11, b12);
			int[][] p6 = strattsenRecursive(aResult, bResult);

			aResult = subtract(a12, a22);
			bResult = add(b21, b22);
			int[][] p7 = strattsenRecursive(aResult, bResult);

			int[][] c12 = add(p3, p5);
			int[][] c21 = add(p2, p4);

			aResult = add(p1, p4);
			bResult = add(aResult, p7);
			int[][] c11 = subtract(bResult, p5);

			aResult = add(p1, p3);
			bResult = add(aResult, p6);
			int[][] c22 = subtract(bResult, p2);

			// Grouping the results obtained in a single matrix:
			int[][] C = new int[n][n];
			for (int i = 0; i < newSize; i++) {
				for (int j = 0; j < newSize; j++) {
					C[i][j] = c11[i][j];
					C[i][j + newSize] = c12[i][j];
					C[i + newSize][j] = c21[i][j];
					C[i + newSize][j + newSize] = c22[i][j];
				}
			}
			return C;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r = new Random(10000);
		Random r1 = new Random(1000);
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int m = reader.nextInt();

		int n = roundToTwoPower(m);

		a = new int[n][n];
		b = new int[n][n];
		c = new int[n][n];
		int[][] arr = new int[n][n];
		int i, j;
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				a[i][j] = r.nextInt(10000);
				b[i][j] = r1.nextInt(1000);
			}
		}
		if (n > 256) {

			System.out.println("Value greater than the breakpoint, hence applying Strassen Algorithm.");

			arr = strassen(a, b);

		
		}

		else {
			System.out.println("applying traditional algorithm");
			traditionalMultiply(a, b);

		}

	}

}
