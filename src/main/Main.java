package main;

import java.util.ArrayList;
import common.Comment;


public class Main {


	public static void main(String[] args) {
		

		String file1="test files/latviesu";
		String file2="test files/dazadi";
		String file3="test files/translits";
		ArrayList<Comment> comments;
		try 
		{			
			comments = FileProcessor.processFile(file2+".txt");
			FileProcessor.printXmlResultInFile(file2+".xml", comments);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	

}

