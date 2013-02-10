package common;

public class Word {
	public String value;
	public boolean isInTranslitDictionary;
	public double ngramTranslitProbability;
	public double percentageOfTranslitRules;
	
	public Word(String word)
	{
		value=word;
	}
}
