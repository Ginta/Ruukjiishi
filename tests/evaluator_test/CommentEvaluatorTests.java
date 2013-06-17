package evaluator_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import evaluator.CommentEvaluator;
import evaluator.WordSequenceAligner;
import evaluator.WordSequenceAligner.Alignment;
import evaluator.WordSequenceAligner.SummaryStatistics;

public class CommentEvaluatorTests {

	@Test
	public void test() {
		Collection<Alignment> alignments=CommentEvaluator.compareFiles("test files/piemers.lv", "test files/piemers.tr.conv");
		
		if(alignments==null)
		{
			fail();
		}
		
		for(Alignment a : alignments)
		{
			System.out.println(a.toString());
			System.out.println();
		}
		
		WordSequenceAligner a = new WordSequenceAligner();
		SummaryStatistics statistics = a.new SummaryStatistics(alignments);
		System.out.println(statistics);
		
	}

}
