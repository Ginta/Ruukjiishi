package methods_test;

import method_tuner.Tuner;

import org.junit.Test;

public class MethodTuner 
{
	@Test
	public void test1() throws Exception
	{
		Tuner r = new Tuner("test files/atpazisanasTests.txt", "test files/atpazisanasKategorijas.txt");
		r.writeFile("test files/atpazisanasRez.txt");		
	}

}
