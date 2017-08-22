package com.neu.algo.assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Driver2 {
	public static void main(String[] args) throws IOException {
	/*	System.out.println("1. StringTokenizer example 1:");
		StringTokenizer st = new StringTokenizer("public // void { some.thing", "{", true);
		while (st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
		System.out.println("2. StringTokenizer example 2:");
		StringTokenizer st2 = new StringTokenizer("public // void { some.thing", "/{ .", false);
		while (st2.hasMoreTokens()) {
			System.out.println(st2.nextToken());
		}*/

try{
	// Pattern pattern = Pattern.compile("<([^<>]+)>");
   // Pattern pattern = Pattern.compile("</\\\\1>");
   // Pattern pattern = Pattern.compile("<[A-Za-z]>");
    //Pattern pattern = Pattern.compile("<[A-Za-z]>(.*?)</[A-Za-z]>");
	 Pattern pattern = Pattern.compile("[^</>]");
	// Pattern pattern = Pattern.compile("[^(<+/>)]");
	//Pattern pattern = Pattern.compile("<([^<>]+)></\\1>");
	// Pattern pattern = Pattern.compile("[<(\\w+)( +.+)*>((.*))</\\1>]");


		System.out.println("3. Reading file example:");
		//ArrayList<String> arrOfBraces= new ArrayList();
		File file = new File("E:\\Tools\\Eclipse Workspace\\NEU_Algo_Assignment1\\InputExample_xml.xml"); // the file could be .txt .csv or anything else
	
		if (file.isFile() && file.exists()) {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(file);
//            NodeList list = doc.getElementsByTagName("*");
//            for (int i=0; i<list.getLength(); i++) {
//                // Get element
//                Element element = (Element)list.item(i);
//                System.out.println(element.getNodeName());
//            }
			InputStreamReader read = new InputStreamReader(new FileInputStream(file));
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
                Matcher mat = pattern.matcher(lineTxt);
				//StringTokenizer st3 = new StringTokenizer(lineTxt,"<([A-Z][A-Z0-9])\\b[^>]>(.*?)</\\1>",true);
                //System.out.println(matcher.);
//				while (st3.hasMoreTokens()) {
//					System.out.println("token: "+st3.nextToken());
//					String tokenWord=st3.nextToken();
//					if(tokenWord.equals("{")||tokenWord.equals("}")) {
//						arrOfBraces.add(tokenWord);
//					}
//				}

               // if (mat.find()) 
                {
                	String word = mat.replaceAll("");
                	if(!word.equalsIgnoreCase("</>"))
                		System.out.println(word);
                }
				//System.out.println(lineTxt);
            }
		bufferedReader.close();
		}else{
			System.out.println("File doesn't exist");
		}
//		for(String s: arrOfBraces)
//		{
//			System.out.println(s);
//		}
//		System.out.println("Count: " +arrOfBraces.size());
		}catch (Exception e) {
    System.exit(1);
}
	}

}
