package convert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import common.Comment;
import common.Word;

import lv.ailab.lnb.fraktur.Transliterator;
import lv.ailab.lnb.fraktur.translit.Engine;
import lv.ailab.lnb.fraktur.translit.ResultData;
import lv.ailab.lnb.fraktur.translit.Rules;
import lv.ailab.lnb.fraktur.translit.Variant;
import lv.semti.morphology.analyzer.Analyzer;
import main.FileProcessor;

public class Convertor {
	Rules rules;
	Transliterator t;
	
	public Convertor() throws Exception
	{
		Analyzer analyzer=new Analyzer();
		analyzer.enableGuessing=true;
		t = new Transliterator("path.conf", analyzer);
	}
	
	
	public ResultData getResults(String token)
	{
		
		try {
			return t.processWord(token, "core", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getBestMatch(String word)
	{
		String result=null;
		ArrayList<Variant> sorted = new ArrayList<Variant>();
		
		ResultData r=this.getResults(word);
		
		sorted.addAll(r.DICT_EXACT.data.keySet());
		sorted.addAll(r.DICT_EXACT_GUESS.data.keySet());
		sorted.addAll(r.DICT_FUZZY.data.keySet());
		sorted.addAll(r.DICT_FUZZY_GUESS.data.keySet());
		
		if(sorted.isEmpty())
		{
			sorted.addAll(r.NO_DICT_FUZZY);
			sorted.addAll(r.NO_DICT_EXACT);
		}
		
		if(sorted.isEmpty())
		{
			result=word;
		}
		else
		{
			Collections.sort(sorted, t.comparator);
			result=sorted.get(0).token;
		}
		
		return result;
	}

	public ArrayList<Comment> convertComments(String fileName) throws Exception
	{
		ArrayList<Comment> comments = FileProcessor.processFile(fileName);
		ArrayList<Comment> resultComments=new ArrayList<Comment>();
		String result;
		
		
		for(Comment comment : comments)
		{
			System.out.println();
			
			Comment tmp = new Comment(null);
			
			if (comment.cumulativeProbabilty>=0.5)
			{
				for(Word word : comment.words)
				{
					if(word.ngramTranslitProbability>0.0)
					{
						if(word.isWord)
						{
							result=getBestMatch(word.value);
						}
						else
						{
							result=word.value;
						}
						
						tmp.words.add(new Word(result));		
						System.out.print(result);
					}
					
					else
					{
						tmp.words.add(word);		
						System.out.print(word);
					}
				}
				resultComments.add(tmp);
			}
			
			else
			{
				resultComments.add(comment);
			}				
		}
		
		return resultComments;		
	}
}
