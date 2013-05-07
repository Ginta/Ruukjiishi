package convert_test;
import org.junit.Test;
import convert.Convertor;

public class TransliteratorTests {
			
	@Test
	public void test1() throws Exception
	{
		Convertor r = new Convertor();
		
		//System.out.println(r.getResults("ruukjiishi"));
		//System.out.println(r.getResults("pareizs"));
		//System.out.println(r.getResults("vārdiņš"));
		//System.out.println(r.getResults("vaardiņsh"));
		System.out.println(r.getResults("bu4a"));
		System.out.println(r.getResults("bu4inja"));
		System.out.println(r.getResults("2dien"));
		System.out.println(r.getResults("2ais"));
		System.out.println(r.getResults("5diena"));		
	}
}



