package dictionary;

import java.io.Serializable;

public class DictionaryValue implements Serializable {

	private static final long serialVersionUID = 1L;
	double probability=0.5;
	int count;
	
	public DictionaryValue(int count)
	{
		this.count=count;
	}
	
	
	public String toString()
	{
		StringBuilder builder=new StringBuilder();
		builder.append("DictionaryValue [ ");
		builder.append(probability);
		builder.append(" ; ");
		builder.append(count);
		builder.append(" ]");
		return builder.toString();
	}
}
