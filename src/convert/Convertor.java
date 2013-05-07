package convert;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import lv.ailab.lnb.fraktur.Transliterator;
import lv.ailab.lnb.fraktur.translit.Engine;
import lv.ailab.lnb.fraktur.translit.ResultData;
import lv.ailab.lnb.fraktur.translit.Rules;
import lv.semti.morphology.analyzer.Analyzer;

public class Convertor {
	Rules rules;
	Transliterator t;
	
	public Convertor() throws Exception
	{
		t = new Transliterator("path.conf", new Analyzer());
	}
	
	public  HashMap<String, Boolean> generate (String token)
	{
		File f = new File("models/convertorRules/core.xml");
		try {
			rules = new Rules(f);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Engine.transform(token, rules, true);
	}
	
	public ResultData getResults(String token)
	{
		
		try {
			t = new Transliterator("path.conf", new Analyzer());
			return t.processWord(token, "core", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
