package translit_test;

import static org.junit.Assert.*;

import java.io.IOException;

import methods.Ngrams;

import org.junit.Test;


public class NgramTests {

	@Test
	public void test() {
		try {
			Ngrams ngram = new Ngrams("<s>", "</s>", "<unk>", "models/ngramModels/vocabulary.txt", "models/ngramModels/latviesu_unk.model", 5);
			assertEquals(-8.73677, ngram.evaluateToken("abava"),0.0001);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

}
