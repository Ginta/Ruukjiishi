/**
 * 
 */
package vocabulary;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 
 *
 */
public class BinaryTrie 
{
	RandomAccessFile file;
	String filename;
	BinaryTrieNode tmp;
	
	public void readNth(int n)// ielasa klases maini
	{
		try{
		file.seek(n*tmp.sizeOfTmp);
		byte[] b = new byte[(int)tmp.sizeOfTmp];
		file.readFully(b);
		tmp.setValues(b);
		}
		catch (IOException e){
			System.out.println("Error in readNth precedure");
		}
	}
	
	
	public void writeNth(BinaryTrieNode node, int n)//ieraksta padoto nodi
	{
		try{
		file.seek(n*node.sizeOfTmp);
		file.write(node.getValues());
		}
		catch (IOException e){
			System.out.println("Error in writeNth precedure");
		}		
	}
	
	public BinaryTrie(String n)
	{
		try{
		this.filename=n;
		this.file = new RandomAccessFile(this.filename, "rw");
		}
		
		catch (IOException e){
			System.out.println("Cannot open vocabulary file");
		}
	}
		
	public void addKey(Key key)
	{
		int next=0,prev=-1;
		int length=key.length;
		boolean foundIt;
		int i;
		
	}
	
	public long fileLength()
	{
		try{
			return file.length()/tmp.sizeOfTmp;
		}
		catch (IOException e)
		{
			System.out.println("Cannot state file length");
			return 0;
		}
	}
	

}
