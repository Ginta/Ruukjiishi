package convert_test;
import main.FileProcessor;

import org.junit.*;
import convert.Convertor;

public class TransliteratorTests {
	
	static Convertor r;
	
	 @BeforeClass
	 public static void testSetup() throws Exception {
		 
		 r = new Convertor();
	 }
	
	@Test
	public void test1() throws Exception
	{
			
		r.convertComments("test files/conv_test.txt");
		
	}
	
	@Test
	public void test2() throws Exception
	{
		
		
		System.out.println(r.getResults("kkas"));
		//System.out.println(r.getResults("vārdiņš"));
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
	
}



