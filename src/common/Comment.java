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
	
	public Integer[] compareComment(Comment c)
	{
		int l = c.words.size()+1;
		int iterator=0;
		Integer [] result = new Integer[l];
		
		for (Word w : c.words)
		{
			result[iterator] = w.compareWord(words.get(iterator).value);
			iterator++;
		}
		
		return result;
	}
}
