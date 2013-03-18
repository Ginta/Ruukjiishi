package translit_test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import translit.Ngrams;

public class NgramTests {

	@Test
	public void test() {
		try {
			Ngrams ngram = new Ngrams("<s>", "</s>", "<unk>", "misc/vocabulary.txt", "misc/lm5.txt", 5);
			assertEquals(-8.27327, ngram.evaluateToken("p o l i t i k u"),0.0001);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
