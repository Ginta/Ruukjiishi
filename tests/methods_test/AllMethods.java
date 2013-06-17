package methods_test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import main.FileProcessor;
import method_tuner.Tuner;
import methods.TranslitProcessor;

import org.junit.Test;

import common.Comment;
import common.SharedObjects;

public class AllMethods 
{
	@Test
	public void test1() throws Exception
	{
		TranslitProcessor pr = SharedObjects.getTranslitProcessor();
		Comment c = pr.processString("Shis ir ruukjiishu projekts");
		System.out.println(c.cumulativeProbabilty);
	}
	
	@Test
	public void test2() throws Exception
	{
		TranslitProcessor pr = SharedObjects.getTranslitProcessor();
		Comment c = pr.processString("Šis ir rūķīšu projekts");
		System.out.println(c.cumulativeProbabilty);
	}
	
	@Test
	public void test3() throws Exception
	{
		TranslitProcessor pr = SharedObjects.getTranslitProcessor();
		Comment c = pr.processString("Sis ir rukisu projekts");
		System.out.println(c.cumulativeProbabilty);
	}
	
	@Test
	public void test4() throws Exception
	{
		TranslitProcessor pr = SharedObjects.getTranslitProcessor();
		Comment c = pr.processString("Vai šis ir rukisu projekts?");
		System.out.println(c.cumulativeProbabilty);
	}
	
	@Test
	public void test5() throws Exception
	{
		TranslitProcessor pr = SharedObjects.getTranslitProcessor();
		Comment c = pr.processString("Teksts, kur nevaram noteikt ir vai nav");
		System.out.println(c.cumulativeProbabilty);
	}
	
	@Test
	public void test6() throws Exception
	{
		TranslitProcessor pr = SharedObjects.getTranslitProcessor();
		Comment c = pr.processString("Teksts ar vienu diakritisko zīmi");
		System.out.println(c.cumulativeProbabilty);
	}
	
	@Test
	public void test7() throws Exception
	{
		TranslitProcessor pr = SharedObjects.getTranslitProcessor();
		Comment c = pr.processString("");
		System.out.println(c.cumulativeProbabilty);
	}
	
	@Test
	public void translitaTests() throws Exception // Metode domāta precision un recall novērtēšanai komentāriem bez pieturzīmēm.
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("test files/prec_rec_atpaz.baseline"), "utf-8"));
		
		double prec, rec;
		double right=0;
		double recognized=0;
		double actually=0;
		double wrong = 0;
		ArrayList<Comment> coms = FileProcessor.processFile("test files/prec_rec_atpaz.test");
		for(Comment c : coms)
		{
			int l = br.read()-48; //bah, nolasa char 0, nolasa atstarpi ka char
			br.read();
			
			if(l==1 || l==2)
				actually++;
			
			if(c.cumulativeProbabilty>=0.5)
				recognized++;
			
			if(l==1 && c.cumulativeProbabilty>0.5)
				right++;
			
			else if(l==2 && c.cumulativeProbabilty>=0.5)
				right++;
			
			if((l==1 || l==2) && c.cumulativeProbabilty<0.5)
				wrong++;
		}
		br.close();
		
		prec = right/(right+wrong)*100;
		rec = right/recognized*100;
		
		System.out.println("precision: "+prec+"%");
		System.out.println("recall: "+rec+"%");

	}
	
	
	
}
