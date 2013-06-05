package main;

import java.util.ArrayList;
import common.Comment;


public class Main {


	public static void main(String[] args) {
		

		String file1="test files/latviesu";
		String file2="test files/dazadi";
		String file3="test files/translits";
		FileProcessor processor=new FileProcessor();
		ArrayList<Comment> comments;
		try 
		{
			/*comments = processor.processFile(file1+".txt");
			processor.printXmlResultInFile(file1+".xml", comments);*/
			
			comments = processor.processFile(file2+".txt");
			processor.printXmlResultInFile(file2+".xml", comments);
			
			/*comments = processor.processFile(file3+".txt");
			processor.printXmlResultInFile(file3+".xml", comments);*/
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	

}
