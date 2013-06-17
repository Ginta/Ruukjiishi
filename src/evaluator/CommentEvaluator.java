package evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import main.FileProcessor;

import common.Comment;

import evaluator.WordSequenceAligner.Alignment;
import evaluator.WordSequenceAligner.SummaryStatistics;

public class CommentEvaluator {
	public static Collection<Alignment> compareFiles(String ref, String hyp)
	{
		
		Collection<Alignment> aligments=new ArrayList<Alignment>();
		WordSequenceAligner aligner = new WordSequenceAligner();
		
		try {
			ArrayList<Comment> ref_com=FileProcessor.processFile(ref);
			ArrayList<Comment> hyp_com=FileProcessor.processFile(hyp);
			ArrayList<String> ref_arr,hyp_arr;
			
			Iterator<Comment> ref_it=ref_com.iterator();
			Iterator<Comment> hyp_it=hyp_com.iterator();
			
			boolean ref_b;
			boolean hyp_b;
			
			while(true)
			{
				ref_b=ref_it.hasNext();
				hyp_b=hyp_it.hasNext();
				
				if(ref_b==false && hyp_b==false)
				{
					break;
				}
				
				if(ref_b==false || hyp_b==false)
				{
					throw new Exception("Komentaari nav sinhroni!");
				}
				
				ref_arr=ref_it.next().getWords();
				hyp_arr=hyp_it.next().getWords();
				aligments.add(aligner.align(ref_arr.toArray(new String[ref_arr.size()]), hyp_arr.toArray(new String[hyp_arr.size()])));
				
			}
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aligments;
	}
}
