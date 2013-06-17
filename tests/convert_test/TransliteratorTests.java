package convert_test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import main.FileProcessor;
import methods.TranslitProcessor;

import org.junit.*;

import common.SharedObjects;

import convert.Convertor;

public class TransliteratorTests {
	
	static Convertor r;
	
	 @BeforeClass
	 public static void testSetup() throws Exception {
		 
		 r = SharedObjects.getConvertor();
	 }
	
	/*@Test
	public void test1() throws Exception
	{			
		r.convertComments("test files/conv_test.txt");	
	}*/
	
	@Test
	public void test2() throws Exception
	{
		
		ArrayList<Integer[]> a = FileProcessor.processTestFile("test files/dazadi_testam.tr", "test files/dazadi_testam.lv");

	}
			
	@Test
	public void test3() throws Exception
	{
		System.out.println(r.getBestMatch("ruukjiishi"));
		assertEquals("rūķīši", r.getBestMatch("ruukjiishi"));
	}
	
	@Test
	public void test4() throws Exception //drīkst nestrādāt
	{
		String token="nezinu";
		String expected="nezinu";
		System.out.println(r.getResults(token));
		assertEquals(expected, r.getBestMatch(token));
	}
	
	@Test
	public void test5() throws Exception
	{
		String token="4ajai";
		String expected="ceturtajai";
		System.out.println(r.getResults(token));
		assertEquals(expected, r.getBestMatch(token));
	}
	
	@Test
	public void test7() throws Exception //exact pozīcija likumu failā
	{
		String token="vbt";
		String expected="varbūt";
		System.out.println(r.getResults(token));
		assertEquals(expected, r.getBestMatch(token));
	}
	
	@Test
	public void test8() throws Exception //exact pozīcija likumu failā
	{
		String token="toč";
		String expected="tik tiešām";
		System.out.println(r.getResults(token));
		assertEquals(expected, r.getBestMatch(token));
	}
	
	@Test
	public void test9() throws Exception //exact pozīcija likumu failā
	{
		String token="mja";
		String expected="mjā";
		System.out.println(r.getResults(token));
		assertEquals(expected, r.getBestMatch(token));
	}
	
	@Test
	public void test10() throws Exception //exact pozīcija likumu failā
	{
		String token="okei";
		String expected="ok";
		System.out.println(r.getResults(token));
		assertEquals(expected, r.getBestMatch(token));
	}	
	
}



