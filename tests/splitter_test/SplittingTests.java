package splitter_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import common.Word;

import splitter.Splitting;

public class SplittingTests {
	
	public static Splitting splitter=null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		if(splitter==null)
		{
			splitter=new Splitting();
		}
	}

	@Test
	public void test() {
		ArrayList<Word> words = Splitting.tokenize("domaaju:)");
		System.out.println(words);
		assertEquals(3, words.size());
	}

	@Test
	public void test2() {
		ArrayList<Word> words = Splitting.tokenize("Nu to tu paprasi Zatlerim, ko viņš ar to domājis");
		System.out.println(words);
		assertEquals(20, words.size());
	}

	@Test
	public void test3() {
		ArrayList<Word> words = Splitting.tokenize("4dien");
		System.out.println(words);
		assertEquals(1, words.size());
	}
}
