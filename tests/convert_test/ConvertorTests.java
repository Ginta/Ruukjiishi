package convert_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import main.FileProcessor;

import org.junit.Test;

import common.Comment;
import common.SharedObjects;
import convert.Convertor;

public class ConvertorTests {

	@Test
	public void test() {
		String input="test files/piemers.tr";
		String output="test files/piemers.tr.conv";
		try {
			Convertor r = SharedObjects.getConvertor();
			ArrayList<Comment> comments=r.convertComments(input);
			FileProcessor.printCommentsToTxtFile(comments, output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
