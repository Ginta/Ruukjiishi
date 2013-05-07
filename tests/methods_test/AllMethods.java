package methods_test;

import method_tuner.Tuner;
import methods.TranslitProcessor;

import org.junit.Test;

import common.Comment;

public class AllMethods 
{
	@Test
	public void test1() throws Exception
	{
		TranslitProcessor pr = new TranslitProcessor();
		Comment c = pr.processString("Shis ir ruukjiishu projekts");
		System.out.println(c.cumulativeProbabilty);
		
	}
	

}
