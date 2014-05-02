package convert_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import main.FileProcessor;

import org.junit.Test;

import common.Comment;
import common.SharedObjects;
import convert.Convertor;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

public class ConvertorTests {

	@Test
	public void test() {
		String input="C:\\Users\\Ginta\\Documents\\GitHub\\naivebayesclassifier\\resources\\barometrs\\neagresivi_10k.txt";
		String output="C:\\Users\\Ginta\\Documents\\GitHub\\naivebayesclassifier\\resources\\barometrs\\neagresivi_10k.txt.conv";
		try {
			Convertor r = SharedObjects.getConvertor();
			ArrayList<Comment> comments=r.convertComments(input);
			FileProcessor.printCommentsToTxtFile(comments, output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /*@Test
    public void test2() {
            
        File trainingDir = new File("C:\\Users\\Ginta\\Documents\\GitHub\\naivebayesclassifier\\resources\\datasets\\trainingConvOne");
        File trainingCategoriesDirs[] = trainingDir.listFiles( new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return new File(dir, name).isDirectory();
                }
            }
        );
                
        for( File trainingCategoryDir : trainingCategoriesDirs ) {
            File trainingFiles[] = trainingCategoryDir.listFiles();
            for( File trainingFile : trainingFiles ) {
		String input=trainingFile.getAbsolutePath();
		String output=(new File(trainingFile.getParent(),trainingFile.getName()+".conv")).getAbsolutePath();
		try {
			Convertor r = SharedObjects.getConvertor();
			ArrayList<Comment> comments=r.convertComments(input);
			FileProcessor.printCommentsToTxtFile(comments, output);
		} catch (Exception e) {
			e.printStackTrace();
		}
            }
        }

    }*/

}
