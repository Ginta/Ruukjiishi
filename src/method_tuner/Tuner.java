/**
 * 
 */
package method_tuner;

import java.io.*;
import java.util.ArrayList;

import main.FileProcessor;

import common.Comment;

/**
 * @author Ginta
 *
 */
public class Tuner 
{
	int[] correctValues;
	ArrayList<Comment> comments;
	ArrayList<Values> values;
	
	public Tuner(String commentsfile, String valuesfile)
	{
		try
		{
			comments = FileProcessor.processFile(commentsfile);
			
			readValues(valuesfile);
			evalCoef();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void readValues(String vaulesfile) throws Exception
	{
		BufferedReader br=null;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(vaulesfile), "utf-8"));
		
		int size = comments.size();
		correctValues = new int[size];
		
		int counter=0;
		
		String line;
		while ((line = br.readLine()) != null) {
			correctValues[counter]=Integer.parseInt(line);
			counter++;
		}
		br.close();
	}
	
	public void evalCoef()
	{
		values = new ArrayList<Values>();
		int z, counter;
		Values val;
		double sum;
		
		for(int x=0; x<=100; x++)
		{
			for(int y=0; y<=100-x; y++)
			{
				z=100-x-y;
				val = new Values();
				val.dict_c=x;
				val.ngram_c=y;
				val.rules_c=z;
				counter=0;
				
				for(Comment c:comments)
				{
					sum=x*c.wordsInTranslitDictionary+y*c.ngramTranslitProbability+z*c.percentageOfTranslitRules;
					
					if(correctValues[counter]==1 && val.recall_b>sum)
					{
						val.recall_b=sum;
					}
					else if(correctValues[counter]==0 && val.precision_b<sum)
					{
						val.precision_b=sum;
					}
					counter++;
				}
				values.add(val);
			}
		}
	}

	public void writeFile(String filename) throws Exception
	{
		BufferedWriter br=null;
		br= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename),"UTF-8"));

		br.write("Dict"+"\t"+"Ngram"+"\t"+"Rules"+"\t"+"Precision"+"\t"+"Recall"+"\n");
		for (Values val : values)
		{
			
			br.write(Integer.toString(val.dict_c));
			br.write("\t");
			
			br.write(Integer.toString(val.ngram_c));
			br.write("\t");
			
			br.write(Integer.toString(val.rules_c));
			br.write("\t");
			
			br.write(Double.toString(val.precision_b));
			br.write("\t");
			
			br.write(Double.toString(val.recall_b));
			br.write("\n");			
		}
		
		br.close();
	}
}
