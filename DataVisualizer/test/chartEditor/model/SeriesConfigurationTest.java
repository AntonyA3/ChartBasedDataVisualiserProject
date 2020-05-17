package chartEditor.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chartEditor.model.SeriesConfigurations;

class SeriesConfigurationTest {
	private SeriesConfigurations seriesConfig = new SeriesConfigurations();
	private String title0 = "title";
	private String title1 = "hello";
	
	@Test
	void testConstructor() {
		SeriesConfigurations config = new SeriesConfigurations();
		assertEquals("", config.getSeriesTitle());
		assertEquals(0, config.getxAxisIndex());
		assertEquals(0, config.getyAxisIndex());
		
	}	

	@Test
	void testSetGetTitle() {
		seriesConfig.setSeriesTitle(title0);
		assertEquals("title", seriesConfig.getSeriesTitle());
		
		seriesConfig.setSeriesTitle(title1);
		assertEquals("hello", seriesConfig.getSeriesTitle());
		
	}
	
	@Test
	void testSetGetXAxisIndex() {
		seriesConfig.setxAxisIndex(0);
		assertEquals(0, seriesConfig.getxAxisIndex() );
		
		seriesConfig.setxAxisIndex(2);
		assertEquals(2, seriesConfig.getxAxisIndex() );
		
		seriesConfig.setxAxisIndex(-1);
		assertEquals(2, seriesConfig.getxAxisIndex());
	}
	
	@Test
	void testSetGetYAxisIndex() {
		seriesConfig.setyAxisIndex(0);
		assertEquals(0, seriesConfig.getyAxisIndex() );
		
		seriesConfig.setyAxisIndex(2);
		assertEquals(2, seriesConfig.getyAxisIndex() );
		
		seriesConfig.setyAxisIndex(-1);
		assertEquals(2, seriesConfig.getyAxisIndex());
	}
}

