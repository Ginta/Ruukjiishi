package dictionaty_test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import lv.ailab.lnb.fraktur.translit.ResultData;
import lv.ailab.lnb.fraktur.translit.Variant;
import lv.semti.morphology.analyzer.Analyzer;

import org.junit.BeforeClass;
import org.junit.Test;

import common.SharedObjects;
import convert.Convertor;

import dictionary.Dictionary;
import dictionary.DictionaryValue;

public class DictionaryTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	//@Test //uncomment to run
	public void test() {
		
		
		
		Dictionary dic=new Dictionary();
		DictionaryValue v;
		String k;
		Analyzer analyzer = null;
		try {
			analyzer = new Analyzer();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int i=0;
		
		for(Entry<String, DictionaryValue> e : dic.map.entrySet())
		{
			v=e.getValue();
			k=e.getKey();
			if(!analyzer.analyze(k).isRecognized())
			{
				v.probability=1;
			}
			else
			{
				v.probability=0.5;
			}
			

			dic.map.put(k,v);
			i++;
			if(i>100000)
			{
				i=0;
				try {
					dic.recMan.commit();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		try {
			dic.recMan.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void test_balanseetais()
	{
		Dictionary dic=new Dictionary();
		DictionaryValue v;
		String k, key;
		Convertor c = null;
		
		Analyzer analyzer = null;
		try {
			analyzer = new Analyzer();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			c = SharedObjects.getConvertor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Variant> sorted;
		HashMap<String,Integer> vardi = new HashMap<String, Integer>();
		
		try {
			FileInputStream fstream = new FileInputStream("test files/balanseetais.wfreq");
		
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			String[] parts;
			while((line = br.readLine()) != null)
			{
				parts=line.split(" ");
				vardi.put(parts[0].toLowerCase(), new Integer(parts[1]));
			}
			br.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ResultData r;
		int sum;
		int exact_count;
		DictionaryValue dicval;
		Integer tmp_int;
		int i=0;
		
		for(Entry<String, Integer> e : vardi.entrySet())
		{
			key=e.getKey();
			
			if(!key.matches("^[a-z]+$")) continue;
			if(analyzer.analyze(key).isRecognized()==false) continue;
			sum=0;
			r=c.getResults(key);
			sorted = new ArrayList<Variant>();

			sorted.addAll(r.DICT_EXACT.data.keySet());
			sorted.addAll(r.DICT_EXACT_GUESS.data.keySet());
			sorted.addAll(r.DICT_FUZZY.data.keySet());
			sorted.addAll(r.DICT_FUZZY_GUESS.data.keySet());
			
			sorted.addAll(r.NO_DICT_FUZZY);
			sorted.addAll(r.NO_DICT_EXACT);
			
			for(Variant j : sorted)
			{
				if(!j.token.equals(key))
				{
					tmp_int=vardi.get(j.token);
					if(tmp_int!=null)
					{
						sum+=tmp_int;
					}
				}
			}
			exact_count=e.getValue();
			dicval=new DictionaryValue(exact_count);
			dicval.probability=(exact_count+sum)/(double)(exact_count+exact_count+sum);
			dic.map.put(key, dicval);
			
			
			
			
			i++;
			if(i>100000)
			{
				i=0;
				try {
					dic.recMan.commit();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		try {
			dic.recMan.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test2()
	{
		Dictionary dic=new Dictionary();
		System.out.println(dic.get("suni"));
	}
	
	@Test
	public void test3()
	{
		Analyzer analyzer = null;
		try {
			analyzer = new Analyzer();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		analyzer.enableGuessing=true;
		
		analyzer.analyze("zaabaks").print(new PrintWriter(System.out));
	}

}
