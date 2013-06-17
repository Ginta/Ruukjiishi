package main;

import java.io.FileInputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import methods.TranslitProcessor;


import common.Comment;
import common.SharedObjects;
import common.Word;
import convert.Convertor;

public class FileProcessor {

	
	public static ArrayList<Comment> processFile(String fileName) throws Exception
	{
		ArrayList<Comment> comments=new ArrayList<Comment>();
		BufferedReader br=null;
		TranslitProcessor processor=SharedObjects.getTranslitProcessor();
		br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
		String line;
		while ((line = br.readLine()) != null) {
			comments.add(processor.processString(line));
		}
		br.close();

		
		return comments;
	}
	
	public static void printCommentsToTxtFile(ArrayList<Comment> comments, String fileName)
	{
		try
		{
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName),"UTF-8"));
			for(Comment c : comments)
			{
				bufferedWriter.write(c.toString());
				bufferedWriter.write('\n');
			}
			
			bufferedWriter.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void printXmlResultInFile(String fileName,ArrayList<Comment> comments) throws Exception
	{
		 BufferedWriter bufferedWriter = null;
	        
	        try {
	            
	            //Construct the BufferedWriter object
	            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName),"UTF-8"));
	            
	            //Start writing to the output stream
	            bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	            bufferedWriter.newLine();
	            bufferedWriter.write("<comments>");
	            bufferedWriter.newLine();
	            
	            for(Comment comment : comments)
	            {
	            	bufferedWriter.write("\t<comment");
	            	
	            	bufferedWriter.write(" words=\"");
	            	bufferedWriter.write(String.format("%d",comment.wordsTotal));
	            	bufferedWriter.write("\"");
	            	

	            	bufferedWriter.write(" words_in_dictionary=\"");
	            	bufferedWriter.write(String.format("%.5f",comment.wordsInTranslitDictionary));
	            	bufferedWriter.write("\"");
	            	

	            	bufferedWriter.write(" ngram_probability=\"");
	            	bufferedWriter.write(String.format("%.5f",comment.ngramTranslitProbability));
	            	bufferedWriter.write("\"");
	            	

	            	bufferedWriter.write(" percentage_of_trasnlit_rules=\"");
	            	bufferedWriter.write(String.format("%.5f",comment.percentageOfTranslitRules));
	            	bufferedWriter.write("\"");
	            	

	            	bufferedWriter.write(" cumulative_probability=\"");
	            	bufferedWriter.write(String.format("%.5f",comment.cumulativeProbabilty));
	            	bufferedWriter.write("\"");
	            	
	            	
		            bufferedWriter.write(">");
		            bufferedWriter.newLine();
		            
		            bufferedWriter.write("\t\t<original>");
		            bufferedWriter.write(comment.original);
		            bufferedWriter.write("</original>");
		            bufferedWriter.newLine();
		            
		            bufferedWriter.write("\t\t<words>");
		            bufferedWriter.newLine();
		            
		            for(Word word : comment.words)
		            {
		            	if(!word.isWord)
		            		continue;
		            	
		            	bufferedWriter.write("\t\t\t<word");
		            	
		            	bufferedWriter.write(" dictionary_probability=\"");
		            	bufferedWriter.write(String.format("%.5f",word.isInTranslitDictionary));
		            	bufferedWriter.write("\"");
		            	

		            	bufferedWriter.write(" ngram_probability=\"");
		            	bufferedWriter.write(String.format("%.5f",word.ngramTranslitProbability));
		            	bufferedWriter.write("\"");
		            	

		            	bufferedWriter.write(" percentage_of_trasnlit_rules=\"");
		            	bufferedWriter.write(String.format("%.5f",word.percentageOfTranslitRules));
		            	bufferedWriter.write("\"");
		            	
			            bufferedWriter.write(">");
			            
			            bufferedWriter.write(word.value);
			            
			            bufferedWriter.write("</word>");
			            bufferedWriter.newLine();
			            
		            }
		            
		            bufferedWriter.write("\t\t</words>");
		            bufferedWriter.newLine();
		            
		            bufferedWriter.write("\t</comment>");
		            bufferedWriter.newLine();
	            }
	            
	            bufferedWriter.write("</comments>");
	            bufferedWriter.newLine();
	            
	        } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } finally {
	            //Close the BufferedWriter
	            try {
	                if (bufferedWriter != null) {
	                    bufferedWriter.flush();
	                    bufferedWriter.close();
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	}

	public static ArrayList<Comment> readXMLFile(String fileName) throws Exception// to do
	{
		ArrayList<Comment> comments=new ArrayList<Comment>(); 
		BufferedReader br=null;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
		
		br.close();
		return comments;
	}

	public static void processCommentFile(String input, String output)
	{
		
	}
	
	public static ArrayList<Integer[]> processTestFile(String testfile, String baseline) throws Exception// to do
	{
		ArrayList<Comment> comments = processFile(baseline);
		ArrayList<Integer[]> rez= new ArrayList<Integer[]>();
		
		Convertor r = SharedObjects.getConvertor();
		ArrayList<Comment> commentsConv = r.convertComments(testfile);
		
		Iterator<Comment> eIterator = comments.iterator();
		Iterator<Comment> aIterator = commentsConv.iterator();
		
		
		while (eIterator.hasNext() && aIterator.hasNext())
		{
			Integer[] addEl = eIterator.next().compareComment(aIterator.next());
			
			/*System.out.println();
			
			for(int i : addEl)
			{
				System.out.print(i+" ");
			}*/
			
			rez.add(addEl);
		}
		
		if(eIterator.hasNext() && aIterator.hasNext())
		{
			throw new Exception("Nav sinhroni koemntāri pēc \""+eIterator.getClass()+"\" \""+aIterator.getClass()+"\"");
		}
		
		return rez;
	}
}
