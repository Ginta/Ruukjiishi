package common;

import java.util.ArrayList;

import splitter.Splitter;
import splitter.Splitting;

public class Comment {
	public String original;
	public ArrayList<Word>words;
	public double ngramTranslitProbability;
	public int wordsTotal;
	public double wordsInTranslitDictionary;
	public double cumulativeProbabilty;
	public double percentageOfTranslitRules;
	
	public Comment(String comment)
	{
		//comment=comment.trim();
		original=comment;
		Splitting spliter=new Splitting();
		words=spliter.tokenize(comment);
		ngramTranslitProbability=0;
		wordsTotal=0;
		for(Word w : words)
		{
			if(w.isWord)
				wordsTotal++;
		}
		wordsInTranslitDictionary=0;
		cumulativeProbabilty=0;
		percentageOfTranslitRules=0;
	}
	
	public int wordcount()
	{
		wordsTotal=0;
		for(Word w : words)
		{
			if(w.isWord)
				wordsTotal++;
		}
		return wordsTotal;
		
	}
	
	public Integer[] compareComment(Comment c)
	{
		int l = c.words.size();
		int iterator=0;
		Integer [] result = new Integer[l];
		
		for (Word w : c.words)
		{
			result[iterator] = w.compareWord(words.get(iterator).value);
			iterator++;
		}
		
		return result;
	}
	
	public ArrayList<String> getWords()
	{
		ArrayList<String> l=new ArrayList<String>();
		
		for(Word w : words)
		{
			if(w.isWord)
			{
				l.add(w.toString());
			}
		}
		
		return l;
	}
	
	public String toString()
	{
		StringBuilder b=new StringBuilder();
		
		for(Word w : words)
		{
			b.append(w.toString());
		}
		
		return b.toString();
	}
}
