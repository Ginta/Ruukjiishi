package common_test;

import static org.junit.Assert.*;

import org.junit.Test;

import common.Comment;

public class CommentTests {

	@Test
	public void test() {
		Comment c=new Comment("4diena");
		System.out.print(c.words);
	}

}
