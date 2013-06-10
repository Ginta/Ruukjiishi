package convert_test;
import static org.junit.Assert.*;
import main.FileProcessor;

import org.junit.*;

import common.SharedObjects;

import convert.Convertor;

public class TransliteratorTests {
	
	static Convertor r;
	
	 @BeforeClass
	 public static void testSetup() throws Exception {
		 
		 r = SharedObjects.getConvertor();
	 }
	
	@Test
	public void test1() throws Exception
	{			
		r.convertComments("test files/conv_test.txt");	
	}
	
	@Test
	public void test2() throws Exception
	{
		FileProcessor.processTestFile("test files/translits_testam.tr", "test files/translits_testam.lv");
	}
			
	@Test
	public void test3() throws Exception
	{
		//Convertor r = new Convertor();
		
		System.out.println(r.getResults("ruukjiishi"));
		System.out.println(r.getResults("pareizs"));
		System.out.println(r.getResults("vārdiņš"));
		System.out.println(r.getResults("vaardiņsh"));
		System.out.println(r.getResults("4dien"));
		System.out.println(r.getResults("4tais"));
		
	}
	
	@Test
	public void test4() throws Exception
	{
		//Convertor r = new Convertor();
		
		//assertEquals("Es taa pat dariitu! Un draugus talkaa sauktu! :D :D :D", "Es taa pat dariitu! Un draugus talkaa sauktu! :D :D :D", tester.multiply(10, 5));		
	}
	
	@Test
	public void SentenceTest()
	{
		assertEquals("es draudzējos ar viss labākām draudzenēm no klases ar endiju","es draudzejos ar viss labakam draudzenem no klases ar endiju");
	}
	
}



