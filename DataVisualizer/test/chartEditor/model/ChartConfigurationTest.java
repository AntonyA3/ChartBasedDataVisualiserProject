package chartEditor.model;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.json.JSONException;import org.junit.jupiter.api.Test;

import chartEditor.model.ChartConfigurations;


class ChartConfigurationTest {
	private Object[][] sampleDataset0 = new Object[][] {{"hello", "world"}, {"person1", 10}, {"person2", 11}, {"person3", 32}};
	//private ChartConfigurations chartConf = new ChartConfigurations();
	
	@Test
	void testConstructor() {
	
		//ChartConfigurations c = new ChartConfigurations();
		/*//c.getAxisConfigurationX().getDefaultScaleFactor()
		c.getAxisConfigurationX().getLowerbound();
		c.getAxisConfigurationX().getUpperbound()
		c.getAxisConfigurationX().getMinscalefactor()
		c.getAxisConfigurationX().getMaxscalefactor()
		c.getAxisConfigurationX().isDefaultRange();
		c.getAxisConfigurationX().isDefaultScale()
		c.getAxisConfigurationX().isPannable()
		c.getAxisConfigurationX().isScalable()*/
	}
	@Test
	void testIsSetDefaultScaleFactor() {
		
	}
	@Test
	void testLoadChartConfiguration() {
		//empty file
		//incomplete chart data
		//inner incomplete data
		// invalid json file
		ChartConfigurations c =new ChartConfigurations(sampleDataset0);
			try {
				c.loadFromJson(new File("res/completely-empty-file.json"));
				assertTrue(false);
			} catch (FileNotFoundException | JSONException e) {
				assertTrue(true);
			}
		c = new ChartConfigurations(sampleDataset0);
		try {
			c.loadFromJson(new File("res/barChart.json"));
			
		} catch (FileNotFoundException e) {
			assertTrue(true);
		} catch (JSONException e) {
			assertTrue(false);
		}
		
		
	}
	
	
}
