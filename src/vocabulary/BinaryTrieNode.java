/**
 * 
 */
package vocabulary;

/**
 * 
 *
 */
public class BinaryTrieNode 
{
	public boolean canEnd;
	public int firstChild;
	public int nextSibling;
	public long sizeOfTmp; //konstante BinaryTrieNode objekta izmēram
	
	public BinaryTrieNode()
	{
		firstChild=-1;
		nextSibling=-1;
		canEnd=false;
	}
	
	public void setValues(byte[] b)
	{
		
	}
	
	public byte[] getValues()
	{
		byte[] b = new byte[(int)this.sizeOfTmp];
		return b;
	}
}
