package common;

public class Word {
	public String value;
	public double isInTranslitDictionary;
	public double ngramTranslitProbability;
	public double percentageOfTranslitRules;
	public boolean isWord;
	
	public Word(String word)
	{
		value=word;
		isWord=word.matches("^[\\p{L}0-9]+$");
	}
	
	public Integer compareWord(String w)
	{
		if(w.equals(value))
			return 1;

		else
		{
			System.out.print(" ["+w+"] ");
			return 0;
		}
			
	}
	
	public String toString()
	{
		return value;
	}
}
