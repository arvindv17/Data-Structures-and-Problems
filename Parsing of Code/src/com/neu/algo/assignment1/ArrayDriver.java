package com.neu.algo.assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ArrayDriver {
	// 1. read file
	// Creating an Arraylist to store the values of the stringtoken
	// ArrayList<String> arr = new ArrayList();
	
	public static void main(String[] args) throws IOException {
		long execTime = System.currentTimeMillis();
		int numberOfToken = 0;
		StackWithArray arr = new StackWithArray(50);
		String[] paranthesisPattern = new String[500];
		int paranthesisCount = 0;
		String match = "NO";
		int numberOfOpenParanthesis = 0;
		int numberOfCloseParanthesis = 0;
		int balanceStatusFlag = 0;
		File file = new File("E:\\Tools\\Eclipse Workspace\\NEU_Algo_Assignment1\\Input");

		if(file.isFile()&&file.exists())
		{
			InputStreamReader read = new InputStreamReader(new FileInputStream(file));
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				System.out.println(lineTxt);
				StringTokenizer st = new StringTokenizer(lineTxt, "{}", true);
				while (st.hasMoreTokens()) {
					String token = st.nextToken();
					if (token.equals("{")) {
						numberOfToken++;
						arr.push(token);
						paranthesisPattern[paranthesisCount] = token;
						paranthesisCount++;
					} else if (token.equals("}")) {
						numberOfToken++;
						String peekExistingTop = (String) arr.peek();
						if (peekExistingTop.equals("{")) {
							arr.pop();

						}

						else {
							balanceStatusFlag = 1;
							
						}
						paranthesisPattern[paranthesisCount] = token;
						paranthesisCount++;
					}

				}
			}
			bufferedReader.close();
		}else
		{
			System.out.println("File doesn't exist");
		}

		// Print out the Number of paranthesis tokens:
		System.out.println("*******Number of Paranthesis Tokens ******\n "+numberOfToken);

		// Result to print the pattern of the paranthesis which is there in the code
		System.out.println("*****Paranthesis pattern******");for(
		int p = 0;p<paranthesisCount;p++)
		{

			System.out.print(paranthesisPattern[p]);
		}

		// To match the paranthesis if they are matching or remain unmatches
		System.out.print("\n The paranthesis which are provided are ");
		if(arr.isEmpty()&&balanceStatusFlag==0)
		{

			System.out.print(" BALANCED \n");
			match = "YES";
			System.out.println("PARANTHESIS MATCHED RESULT: " + match);
		}else
		{
			System.out.print(" UNBALANCED \n");
			System.out.println("PARANTHESIS MATCH RESULT " + match);

			while (!arr.isEmpty()) {
				String op1 = arr.pop();

				if (op1.equals("{")) {
					numberOfOpenParanthesis++;
				} else {
					numberOfCloseParanthesis++;
				}
			}

			// Closing and Opening brace count
			if (numberOfCloseParanthesis > numberOfOpenParanthesis || balanceStatusFlag == 1) {
				System.out.println("The count of the closing paranthesis is more than the count of the opening braces. \n");
			} else {
				System.out.println("The count of the opening braces is more than the count of he closing braces \n");
			}

		}
		 final long duration = System.currentTimeMillis() - execTime ;
		 System.out.println("Execution Time : "+duration);
		}

	 
	  
	  
	
}
