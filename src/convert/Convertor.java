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

	public void convertComments(String fileName) throws Exception
	{
		FileProcessor f = new FileProcessor();
		ArrayList<Comment> comments = f.processFile(fileName);
		String result;
		
		
		for(Comment comment : comments)
		{
			System.out.println();
			
			for(Word word : comment.words)
			{
				if(word.value.matches("^[a-z][a-z0-9]+$"))
				{
					result=getBestMatch(word.value);
				}
				else
				{
					result=word.value;
				}
				
				System.out.print(result);
								
				//System.out.println(this.getResults(word.value));
			}
			
		}		
		
	}
}
