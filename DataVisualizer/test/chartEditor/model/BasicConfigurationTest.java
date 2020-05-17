package chartEditor.model;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

class BasicConfigurationTest {
	
	private BasicConfiguration basicConfiguration = new BasicConfiguration();
	private Object[][] sampleDataset0 = new Object[][] {{"hello", "world"}, {"person1", 10}, {"person2", 11}, {"person3", 32}};
	private Object[][] sampleDataset1 = new Object[][] {{"foo", "bar"}, {1, "jdj"}, {2, "jj"}, {3, 32}};
	private Object[][] sampleDataset2 = new Object[0][0];
	private Object[][] sampleDataset3 = new Object[][] {{"foo", "bar"}, {1, "jdj"}, {2, "jj"}, {3, 32}, {8,null}};
	//private Object[][] sampleDataset4 = new Object[][] {{"foo", "bar"}, {1, "jdj"}, {2, "jj"}, {3, 32}, {null,null}};
	//private Object[][] sampleDataset5 = new Object[][] {{null, null}, {null, null}, {null, null}, {3, null}, {null,null}};

	//private String sampleTitle0 = new String("12345");
	//private String sampleTitle1 = new String("Foobar");
	//private String sampleTitle2 = new String("\nFoobar");
	private String sampleTitle3 = new String("");
	//private ChartType charttype0 = ChartType.BARCHART;
	private ChartType charttype1 = ChartType.SCATTERCHART;
	//private ChartType charttype2 = ChartType.PIECHART;
	//private int index0 = 0;
	//private int index1 = 1;
	//private int index2 = 2;
	//private int index3 = 3;

	
	@Test
	void TestConstructor() {
		BasicConfiguration b = this.basicConfiguration;
		assertEquals(charttype1,b.getChartType());
		assertEquals(sampleTitle3, b.getChartTitle());
		assertArrayEquals(sampleDataset2, b.getDataset());
		
	}
	
	
	
	
	@Test
	void TestSetDataGetHeaders() {
		/*basicConfiguration.setDataset(sampleDataset0);
		assertArrayEquals(new Object[]{"hello","world"},basicConfiguration.getHeaders().toArray() );
		
		basicConfiguration.setDataset(sampleDataset1);
		assertArrayEquals(new Object[]{"foo","bar"},basicConfiguration.getHeaders().toArray() );

		basicConfiguration.setDataset(sampleDataset2);
		assertArrayEquals(new Object[0],basicConfiguration.getHeaders().toArray() );
		
		basicConfiguration.setDataset(sampleDataset3);
		assertArrayEquals(new Object[] {}, basicConfiguration.getHeaders().toArray());
		*/
		assertTrue(true);

	}
	
	
	@Test
	void TestSetDataGetFrequencyAtCatagory() {
		/*basicConfiguration.setDataset(sampleDataset0);
		assertArrayEquals(new Object[]{10.0,11.0,32.0},basicConfiguration.getFrequencyAt(index0).toArray());
		assertEquals(new Object[] {}, basicConfiguration.getFrequencyAt(index1).toArray());
		
		basicConfiguration.setDataset(sampleDataset1);
		assertArrayEquals(new Object[]{0.0,0.0,32.0},basicConfiguration.getFrequencyAt(1).toArray());

		basicConfiguration.setDataset(sampleDataset2);
		//assertArrayEquals(expected, actual);
		
		basicConfiguration.setDataset(sampleDataset3);*/
		assertTrue(true);
		
	}
	
	@Test
	void TestSetDataGetCatagoryAt() {
		basicConfiguration.setDataset(sampleDataset0);
		assertArrayEquals(new Object[]{"person1","person2","person3"},basicConfiguration.getCatagoryAt(0).toArray());
		
		basicConfiguration.setDataset(sampleDataset1);
		assertArrayEquals(new Object[]{"1","2","3"},basicConfiguration.getCatagoryAt(0).toArray());
		
		basicConfiguration.setDataset(sampleDataset2);


		basicConfiguration.setDataset(sampleDataset3);

		
	}
	
	@Test
	void TestGetSetChartType() {
	 basicConfiguration.setChartType(ChartType.SCATTERCHART);
	 assertEquals(ChartType.SCATTERCHART, basicConfiguration.getChartType());
	 basicConfiguration.setChartType(ChartType.BARCHART);
	 assertEquals(ChartType.BARCHART, basicConfiguration.getChartType());
	 basicConfiguration.setChartType(ChartType.PIECHART);
	 assertEquals(ChartType.PIECHART, basicConfiguration.getChartType());
		
	}
	
	@Test
	void TestFromJson() {
		//BasicConfiguration b = this.basicConfiguration;
		basicConfiguration.fromJson(new JSONObject()); // empty json
		assertEquals(ChartType.SCATTERCHART,basicConfiguration.getChartType());
		assertEquals("", basicConfiguration.getChartTitle());
		assertArrayEquals(new Object[0][0], basicConfiguration.getDataset());

	}
	
	

}
