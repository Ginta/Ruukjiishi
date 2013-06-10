package common;

import convert.Convertor;
import methods.TranslitProcessor;

public class SharedObjects {
	private static TranslitProcessor translitProcessor=null;
	private static Convertor convertor = null;
	
	public static TranslitProcessor getTranslitProcessor()
	{
		if(translitProcessor==null)
		{
			translitProcessor=new TranslitProcessor();
		}
		
		return translitProcessor;
	}
	
	public static Convertor getConvertor() throws Exception
	{
		if(convertor==null)
		{
			convertor=new Convertor();
		}
		
		return convertor;
	}
}
