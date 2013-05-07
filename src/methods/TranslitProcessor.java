 package methods;

import java.util.regex.Pattern;

import common.Comment;
import common.Word;
import dictionary.Dictionary;

public class TranslitProcessor {
	
	public Dictionary dic;
	public Ngrams ngramTranslit;
	public Ngrams ngramLatviesu;
	public static double ngram_c= 0.19;
	public static double rules_c = 0.48;
	public static double dic_c = 0.33;
	public static double recall_c = 0.339596419; 
	public static double precision_c = 0.3396008996;


	public TranslitProcessor() throws Exception
	{
		dic = new Dictionary();
		ngramTranslit = new Ngrams("<s>", "</s>", "<unk>", "models/ngramModels/vocabulary.txt", "models/ngramModels/translits_unk.model", 5);
		ngramLatviesu = new Ngrams("<s>", "</s>", "<unk>", "models/ngramModels/vocabulary.txt", "models/ngramModels/latviesu_unk.model", 5);
	}
	
	public Comment processString(String comment)
	{
		Comment returnValue=new Comment(comment);
		this.analyzeWordList(returnValue);
		this.calculateCumulativeResult(returnValue);
		
		return returnValue;
	}
	
	private void calculateCumulativeResult(Comment comment)// jāizmaina
	{
		comment.percentageOfTranslitRules=comment.percentageOfTranslitRules/(double)comment.wordsTotal;
		comment.ngramTranslitProbability=comment.ngramTranslitProbability/(double)comment.wordsTotal;
		comment.wordsInTranslitDictionary=comment.wordsInTranslitDictionary/(double)comment.wordsTotal;
		
		comment.cumulativeProbabilty = comment.percentageOfTranslitRules*TranslitProcessor.rules_c+comment.ngramTranslitProbability*TranslitProcessor.ngram_c+comment.wordsInTranslitDictionary*TranslitProcessor.dic_c;
		comment.cumulativeProbabilty=comment.cumulativeProbabilty/TranslitProcessor.recall_c - 0.5;
		
        if(comment.cumulativeProbabilty>1) comment.cumulativeProbabilty = 1;
        if (comment.cumulativeProbabilty<0) comment.cumulativeProbabilty = 0;
	}
	
	private void analyzeWordList(Comment comment)
	{
		for(Word w : comment.words)
		{
			w.isInTranslitDictionary=this.isInDictionary(w.value);
			w.ngramTranslitProbability=this.getNgramProbability(w.value);
			w.percentageOfTranslitRules=this.getPercentageOfRules(w.value);
			comment.wordsInTranslitDictionary+=(w.isInTranslitDictionary ? 1 : 0);
			comment.ngramTranslitProbability+=w.ngramTranslitProbability;
			comment.percentageOfTranslitRules+=w.percentageOfTranslitRules;
		}
	}
	
	public boolean isInDictionary(String s)
	{
		if(!Pattern.matches("[a-zA-Z0-9]+", s))
		{
			return false;
		}
		
		if(dic.get(s)==null && dic.get(s.toLowerCase())==null) return false;
		
		return true;
	}
	
	public double getNgramProbability(String s)
	{
		double prob = 0.5;
		
		if(!Pattern.matches("[a-zA-Z0-9]+", s))
		{
			return 0;
		}
		
        try 
        {
        	double tr =ngramTranslit.evaluateToken(s);
        	double latv =ngramLatviesu.evaluateToken(s);
           
            prob = (tr-latv)/100 + 0.5;                          
		} 
        
        catch (Exception e) 
        {
        	System.err.println(s);
			e.printStackTrace();
		}        
             
        if(prob>1) return 1;
        if (prob<0) return 0;
        
        else return prob;
	}
	
	public double getPercentageOfRules(String s)
	{
		if(s.contains("x")||s.contains("w") || s.contains("y")) return 1;
		if(s.length()==1) return 1;
		
		String rules[]= {"aa", "ee", "uu", "ii", "sh", "gj", "lj", "sh", "zh", "ch", "nj", "4a", "kj"};				
		int count=0;
		
		for(String rule : rules)
		{			
				if(s.contains(rule)) 
				{
					int i=s.length();
					while(i-2>=0)
					{
						//String test = s.substring(i-2,i);
						if(rule.equals(s.substring(i-2,i)))count++;
						i--;					
					}				
				}
		}
			
		return count/(double)(s.length()-1);
	}
		
}
