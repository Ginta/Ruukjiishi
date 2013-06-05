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
	
	@Test
	public void test2() throws Exception
	{
		TranslitProcessor pr = new TranslitProcessor();
		Comment c = pr.processString("Šis ir rūķīšu projekts");
		System.out.println(c.cumulativeProbabilty);
	}
	
	@Test
	public void test3() throws Exception
	{
		TranslitProcessor pr = new TranslitProcessor();
		Comment c = pr.processString("Sis ir rukisu projekts");
		System.out.println(c.cumulativeProbabilty);
	}
	
	@Test
	public void test4() throws Exception
	{
		TranslitProcessor pr = new TranslitProcessor();
		Comment c = pr.processString("Vai šis ir rukisu projekts?");
		System.out.println(c.cumulativeProbabilty);
	}
	
	@Test
	public void test5() throws Exception
	{
		TranslitProcessor pr = new TranslitProcessor();
		Comment c = pr.processString("Teksts, kur nevaram noteikt ir vai nav");
		System.out.println(c.cumulativeProbabilty);
	}
	
	@Test
	public void test6() throws Exception
	{
		TranslitProcessor pr = new TranslitProcessor();
		Comment c = pr.processString("Teksts ar vienu diakritisko zīmi");
		System.out.println(c.cumulativeProbabilty);
	}
	
	@Test
	public void test7() throws Exception
	{
		TranslitProcessor pr = new TranslitProcessor();
		Comment c = pr.processString("");
		System.out.println(c.cumulativeProbabilty);
	}
	
	@Test
	public void translitaTests() throws Exception
	{
		
		TranslitProcessor pr = new TranslitProcessor();
		Comment c = pr.processString("");
		System.out.println(c.cumulativeProbabilty);
	}
	
}
