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
		wordsTotal=words.size();
		wordsInTranslitDictionary=0;
		cumulativeProbabilty=0;
		percentageOfTranslitRules=0;
	}
}
