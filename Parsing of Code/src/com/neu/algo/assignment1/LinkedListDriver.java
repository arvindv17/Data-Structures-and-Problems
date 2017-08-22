package com.neu.algo.assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class LinkedListDriver {

	public static void main(String[] args) throws IOException, FileNotFoundException {
		 long execTime = System.currentTimeMillis();
		// 1. read file
		// Creating an Arraylist to store the values of the stringtoken
//		ArrayList<String> arr = new ArrayList();
		int numberOfToken = 0;
		StackWithLinkedList ll = new StackWithLinkedList();
		String[] paranthesisPattern = new String[500];
		int paranthesisCount = 0;
		String match = "NO";
		int numberOfOpenParanthesis = 0;
		int numberOfCloseParanthesis = 0;
		int balanceStatusFlag = 0;
		// Path of the input file in the project
		File file = new File("E:\\Tools\\Eclipse Workspace\\NEU_Algo_Assignment1\\Input"); // the
																							// file
																							// could
																							// be
																							// .txt
																							// .csv
																							// or
																							// anything
																							// else
		// Stream Reader
		if (file.isFile() && file.exists()) {
			InputStreamReader read = new InputStreamReader(new FileInputStream(file));
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				System.out.println(lineTxt);
				StringTokenizer st = new StringTokenizer(lineTxt,"{}", true);
				while (st.hasMoreTokens()) {
					String token = st.nextToken();
					/*
					 * If the token is equal to the opening paranthesis,
					 * we increment the counter by 1 and push it into the stack.
					 * To display the result and the number of { which we encounter, we are putting it
					 * in a string array and based on the position we are displaying it.
					 */
					if (token.equals("{")) {
						numberOfToken++;
						ll.push(token);
						paranthesisPattern[paranthesisCount] = token;
						paranthesisCount++;
					} else if (token.equals("}")) {
						numberOfToken++;
						String peekExistingTop = (String) ll.peek();
						if (peekExistingTop.equals("{")) {
							ll.pop();

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
		} else {
			System.out.println("File doesn't exist");
		}
		
		//Print out the Number of tokens:
        System.out.println("*******Number of Tokens ******\n " + numberOfToken);
        
     // Result to print the pattern of the paranthesis which is there in the code
        System.out.println("*****Paranthesis pattern******");
        for (int p = 0; p < paranthesisCount; p++) {

            System.out.print(paranthesisPattern[p]);
        }
        
      //To match the paranthesis if they are matching or remain unmatches
        System.out.print("\n The paranthesis which are provided are ");
        if (ll.isEmpty() && balanceStatusFlag == 0) {

            System.out.print(" BALANCED \n");
            match = "YES";
            System.out.println("PARANTHESIS MATCHED RESULT: " + match);
        } else {
            System.out.print(" UNBALANCED \n");
            System.out.println("PARANTHESIS MATCH RESULT " + match);

            while (!ll.isEmpty()) {
                String op1 = (String) ll.pop();
                
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
       
        final long duration = System.currentTimeMillis() - execTime;
        System.out.println("Execution Time: "+duration);  
        
		
	}

}
