package dictionary;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import jdbm.PrimaryHashMap;
import jdbm.RecordManager;
import jdbm.RecordManagerFactory;

public class Dictionary {
	
	public PrimaryHashMap<String,DictionaryValue> map;
	public RecordManager recMan;
	
	public Dictionary()
	{
		try
		{
			recMan = RecordManagerFactory.createRecordManager("models/javaDB/dictionary");
			map = recMan.hashMap("dictionary"); 
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public DictionaryValue get(String key)
	{
		return map.get(key);
	}
	
	public void addDataFromFile(String filename)
	{
		String line;
		String[] parts;
		
		FileInputStream fstream;
		try {
			fstream = new FileInputStream(filename);
		
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			while((line = br.readLine()) != null)
			{
				parts=line.split("\t");
				map.put(parts[0], new DictionaryValue(Integer.parseInt(parts[1])));
			}
			recMan.commit();
			br.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
