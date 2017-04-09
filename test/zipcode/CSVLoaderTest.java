package zipcode;

import static org.junit.Assert.*;

import org.junit.Test;
import org.zipcode.loader.CSVLoader;

public class CSVLoaderTest {

	@Test
	public void test() {
		CSVLoader loader = new CSVLoader();
		
		String[] locations = loader.loader("locations.csv");
		
		for(String location : locations) {
			System.out.println(location.toString());
		}
	}

}
