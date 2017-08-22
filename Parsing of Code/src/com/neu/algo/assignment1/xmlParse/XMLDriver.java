package com.neu.algo.assignment1.xmlParse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import com.neu.algo.assignment1.StackWithArray;

public class XMLDriver {
	public static void main(String[] args) throws FileNotFoundException, IOException {

		/*
		 * Read the file
		 */
		
		int countFlag = 0;
		StackWithArray arr = new StackWithArray(50);
		File file = new File("E:\\Tools\\Eclipse Workspace\\NEU_Algo_Assignment1\\InputExample_xml.xml");
		if (file.isFile() && file.exists()) {
			InputStreamReader read = new InputStreamReader(new FileInputStream(file));
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {

				StringTokenizer st = new StringTokenizer(lineTxt, "</>", true);

				/*
				 * Tokenize
				 */
				while (st.hasMoreTokens()) {

					String token = st.nextToken();
//					System.out.println("Open Tag - " + token);
					if (token.equals("<") && st.hasMoreTokens()) {
					// Push when the input tag is open
						arr.push(token);
						//This is done to get the first value 0th position array from the resultant tokenized
						//string
						String value = st.nextToken().split(" ")[0];
//						System.out.println("Tag Value - " + value);
						/*
						 * Compare when the tag contains / and a closing tag
						 * Then we push the value
						 */
						if ((!value.equals("/")) && (!value.equals(">")) && st.hasMoreTokens()) {
							arr.push(value);
							String closeTag = st.nextToken();
							if (closeTag.equals(">")) {
								arr.push(closeTag);
//								System.out.println("closeTag - " + closeTag);
							} else if (closeTag.equals("/") && st.hasMoreTokens()) {
								String closedTag = st.nextToken();
								if (closedTag.equals(">")) {
									arr.pop();
									arr.pop();
									countFlag = 1;
								}
							}
						} else if (value.equals("/") && st.hasMoreTokens()) {
							String closingValue = st.nextToken();
							//System.out.println("closingValue: " + closingValue);
							if (st.hasMoreTokens() && st.nextToken().equals(">")) {
								arr.pop();
								arr.pop();
								String checkStack = (String) arr.pop();
//								System.out.println("Stack Result: " + checkStack);
								if (closingValue.equals(checkStack)) {
									arr.pop();
									countFlag = 1;
								} else
									countFlag = 0;
							}
						}

					}

				}
			}
		}

		if (countFlag == 0) {
			System.out.println("Tag Unbalanced");
		} else {
			System.out.println("Tag Balanced");
		}

	}

}
