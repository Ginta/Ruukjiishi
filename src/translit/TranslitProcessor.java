 package translit;

import java.io.*;
import java.lang.Process;
import java.util.regex.Pattern;

import common.Comment;
import common.Word;

public class TranslitProcessor {
	public Comment processString(String comment)
	{
		Comment returnValue=new Comment(comment);
		this.analyzeWordList(returnValue);
		this.calculateCumulativeResult(returnValue);
		
		return returnValue;
	}
	
	private void calculateCumulativeResult(Comment comment)
	{
		comment.percentageOfTranslitRules=comment.percentageOfTranslitRules/(double)comment.wordsTotal;
		comment.ngramTranslitProbability=comment.ngramTranslitProbability/(double)comment.wordsTotal;
		comment.wordsInTranslitDictionary=comment.wordsInTranslitDictionary/(double)comment.wordsTotal;
		
		if(comment.ngramTranslitProbability>=0.505)
		{
			comment.cumulativeProbabilty=1;
		}
		
		if(comment.ngramTranslitProbability<=0.485)
		{
			comment.cumulativeProbabilty=0;
		}
		

		
		if((comment.ngramTranslitProbability>0.485 && comment.ngramTranslitProbability<0.505))
		{
			comment.cumulativeProbabilty=0.5;
			
			if(comment.percentageOfTranslitRules>0.1 || comment.wordsInTranslitDictionary>0.95)
			{
				comment.cumulativeProbabilty=1;				
			}
		}
		
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
		boolean result=false;
		
		if(!Pattern.matches("[a-zA-Z0-9]+", s))
		{
			return false;
		}
		
		try 
        {
        	String line = null;
        	Runtime runtime = Runtime.getRuntime();  
		        	
			Process process = runtime.exec("misc/BinaryTrie "+s.toLowerCase());
			BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
            line = buf.readLine(); 
            int skaits=Integer.parseInt(line);
            
           if(skaits>0)
           {
        	   result=true;
           }
         
                              
		} 
        
        catch (IOException e) 
        {
			e.printStackTrace();
		}  
		
		return result;
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
        	String line = null;
        	Runtime runtime = Runtime.getRuntime();  

    		BufferedWriter ngram_tmp = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("misc/ngram_tmp.txt"),"UTF-8"));
    		
        	StringBuilder sb = new StringBuilder();

    		for (char c: s.toCharArray()) {
    		   sb.append(c).append(" ");
    		}
    		
    		sb.setLength(sb.length() > 0 ? sb.length() - 1 : 0);
    		
    		ngram_tmp.write(sb.toString());
    		ngram_tmp.close();
    		
        	
			Process process = runtime.exec("misc/ngram -order 3 -ppl misc/ngram_tmp.txt -lm misc/translits_dubletie.model");
			BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
            line = buf.readLine();
            line = buf.readLine(); 
            double translits=Double.parseDouble(line.replaceAll(".*logprob\\= (\\-?[0-9]+\\.?[0-9]+).*", "$1"));
            
            process = runtime.exec("misc/ngram -order 3 -ppl misc/ngram_tmp.txt -lm misc/latviesu_dubletie.model");
			buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
            line = buf.readLine();
            line = buf.readLine(); 
            double latv=Double.parseDouble(line.replaceAll(".*logprob\\= (\\-?[0-9]+\\.?[0-9]+).*", "$1"));
            
            prob = (translits-latv)/100 + 0.5;
         
                              
		} 
        
        catch (IOException e) 
        {
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
						String test = s.substring(i-2,i);
						if(rule.equals(s.substring(i-2,i)))count++;
						i--;					
					}				
				}
		}
			
		return count/(double)(s.length()-1);
	}
		
}
