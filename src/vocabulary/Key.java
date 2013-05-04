package vocabulary;

public class Key 
{
	public char[] value;
	public int length;
	public int count;
	
	public void setValue(char[] w)
	{
		for(length=0; w[value[length]]!=0; length++)
		{
			value[value[length]]=w[value[length]];
		}
		value[value[length]]=w[value[length]];
	}
}
