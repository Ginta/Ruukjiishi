package splitter;

import java.util.ArrayList;

import common.Word;

public class Splitter {
	
	enum State {IN_WORD,NOWHERE};
	
	public ArrayList<Word> process(String str)
	{
		
		ArrayList<Word>tokens=new ArrayList<Word>();
		
		State state = State.NOWHERE;
		char c;
		int wordStart=0;
		
		for (int i = 0; i < str.length(); i++) 
		{
			c=str.charAt(i);
			
			switch(state)
			{
			case NOWHERE:
				if(Character.isAlphabetic(c) || Character.isDigit(c))
				{
					wordStart=i;
					state=State.IN_WORD;
				}
				break;
			case IN_WORD:
				if(!(Character.isAlphabetic(c) || Character.isDigit(c)))
				{
					if(i-wordStart>1)
					{
						tokens.add(new Word(str.substring(wordStart, i)));
					}
					state=State.NOWHERE;
				}
				break;
			}
			
		}
		
		if(state==State.IN_WORD && str.length()-wordStart>1)
		{
			tokens.add(new Word(str.substring(wordStart, str.length())));
		}
		
		
		return tokens;
	}
}
