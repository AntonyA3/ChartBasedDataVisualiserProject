package chartEditor.model;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

class AxisConfigurationTest {
	private AxisConfigurations axConf = new AxisConfigurations();
	private double zero = 0;
	private double number0 = 100;
	private double number1 = 0.4;
	private double negative0 = -100;
	private double negative1 = -0.4;
	private JSONObject jsonobj0 = new JSONObject();
	private JSONObject jsonobj1;
	private JSONObject jsonobj2;

	public AxisConfigurationTest() {
		jsonobj1 = new JSONObject();
		jsonobj1.put(AxisConfigurations.KEY_SCALABLE, false);
		jsonobj1.put(AxisConfigurations.KEY_PANNABLE, false);
		jsonobj1.put(AxisConfigurations.KEY_DEFAULTSCALE, true);
		jsonobj1.put(AxisConfigurations.KEY_MINSCALE, 1);
		jsonobj1.put(AxisConfigurations.KEY_MAXSCALE, 1);
		jsonobj1.put(AxisConfigurations.KEY_LOWER_BOUND, 1);
		jsonobj1.put(AxisConfigurations.KEY_UPPER_BOUND, 1);

		jsonobj2 = new JSONObject();
		jsonobj2.put(AxisConfigurations.KEY_SCALABLE, true);
		jsonobj2.put(AxisConfigurations.KEY_PANNABLE, false);
		jsonobj2.put(AxisConfigurations.KEY_DEFAULTSCALE, true);
		jsonobj2.put(AxisConfigurations.KEY_MINSCALE, 1);
		/*jsonobj2.put(AxisConfigurations.KEY_MAXSCALE, 1);
		jsonobj2.put(AxisConfigurations.KEY_LOWER_BOUND, 1);
		jsonobj2.put(AxisConfigurations.KEY_UPPER_BOUND, 1);*/
	}

	
	/*//c.getAxisConfigurationX().getDefaultScaleFactor()
	c.getAxisConfigurationX().getLowerbound();
	c.getAxisConfigurationX().getUpperbound()
	c.getAxisConfigurationX().getMinscalefactor()
	c.getAxisConfigurationX().getMaxscalefactor()
	c.getAxisConfigurationX().isDefaultRange();
	c.getAxisConfigurationX().isDefaultScale()
	c.getAxisConfigurationX().isPannable()
	c.getAxisConfigurationX().isScalable()*/


	@Test
	void testGetSetUpperBound() {
		axConf.setUpperbound(zero);
		assertEquals(0,	axConf.getUpperbound());
		axConf.setUpperbound(number0);
		assertEquals(100, axConf.getUpperbound());
		axConf.setUpperbound(number1);
		assertEquals(0.4, axConf.getUpperbound());
	}
	
	@Test
	void testGetSetLowerBound() {
		axConf.setLowerbound(zero);
		assertEquals(0,	axConf.getLowerbound());
		axConf.setLowerbound(number0);
		assertEquals(100, axConf.getLowerbound());
		axConf.setLowerbound(number1);
		assertEquals(0.4, axConf.getLowerbound());
		
	}
	
	@Test
	void testGetSetMinScale() {
		axConf.setMinscalefactor(negative0);
		assertEquals(AxisConfigurations.LOWEST_SCALE, axConf.getMinscalefactor());
		
		axConf.setMinscalefactor(negative1);
		assertEquals(AxisConfigurations.LOWEST_SCALE, axConf.getMinscalefactor());
		
		axConf.setMinscalefactor(number0);
		assertEquals(number0, axConf.getMinscalefactor());
		
		axConf.setMinscalefactor(number1);
		assertEquals(number1, axConf.getMinscalefactor());
		
		axConf.setMinscalefactor(zero);
		assertEquals(AxisConfigurations.LOWEST_SCALE, axConf.getMinscalefactor());
		
		
	}
	
	@Test
	void testGetSetMaxScale() {
		axConf.setMaxscalefactor(negative0);
		assertEquals(AxisConfigurations.LOWEST_SCALE, axConf.getMaxscalefactor());
		
		axConf.setMaxscalefactor(negative1);
		assertEquals(AxisConfigurations.LOWEST_SCALE, axConf.getMaxscalefactor());
		
		axConf.setMaxscalefactor(number0);
		assertEquals(number0, axConf.getMaxscalefactor());
		
		axConf.setMaxscalefactor(number1);
		assertEquals(number1, axConf.getMaxscalefactor());
		
		axConf.setMaxscalefactor(zero);
		assertEquals(AxisConfigurations.LOWEST_SCALE, axConf.getMaxscalefactor());
		
	}
	
	@Test
	void testIsDefaultRange() {
		axConf.setDefaultRange(true);
		assertTrue(axConf.isDefaultRange());
		axConf.setDefaultRange(false);
		assertFalse(axConf.isDefaultRange());
	}

	@Test
	void testIsDefaultScale() {
		axConf.setDefaultScale(true);
		assertTrue(axConf.isDefaultScale());
		axConf.setDefaultScale(false);
		assertFalse(axConf.isDefaultScale());
	}
	@Test
	void testIsSetScalable() {
		axConf.setScalable(true);
		assertTrue(axConf.isScalable());
		axConf.setScalable(false);
		assertFalse(axConf.isScalable());
	}
	@Test
	void testIsSetPannable() {
		axConf.setPannable(true);
		assertTrue(axConf.isPannable());
		axConf.setPannable(false);
		assertFalse(axConf.isPannable());
	}
	
	@Test
	void testWriteToJson() {
		axConf = new AxisConfigurations();
		assertEquals(jsonobj1.toString(), axConf.toJson().toString());		
	}
	
	@Test
	void testGenerateFromJson() {
	
		axConf.fromJson(jsonobj0);
		assertTrue(axConf.isDefaultRange());
		assertFalse(axConf.isPannable());
		assertFalse(axConf.isScalable());
		assertTrue(axConf.isDefaultScale());
		assertEquals(1, axConf.getMaxscalefactor());
		assertEquals(1, axConf.getMinscalefactor());
		assertEquals(1, axConf.getLowerbound());
		assertEquals(1, axConf.getUpperbound());
		
		axConf = new AxisConfigurations();
		axConf.fromJson(jsonobj2);
		assertTrue(axConf.isDefaultRange());
		assertFalse(axConf.isPannable());
		assertFalse(axConf.isScalable());
		assertFalse(axConf.isDefaultScale());
		assertEquals(1, axConf.getMaxscalefactor());
		assertEquals(1, axConf.getMinscalefactor());
		assertEquals(1, axConf.getLowerbound());
		assertEquals(1, axConf.getUpperbound());
		
		jsonobj1 = new JSONObject();
		jsonobj1.put(AxisConfigurations.KEY_SCALABLE, false);
		jsonobj1.put(AxisConfigurations.KEY_PANNABLE, false);
		jsonobj1.put(AxisConfigurations.KEY_DEFAULTSCALE, true);
		jsonobj1.put(AxisConfigurations.KEY_MINSCALE, 1);
		jsonobj1.put(AxisConfigurations.KEY_MAXSCALE, 1);
		jsonobj1.put(AxisConfigurations.KEY_LOWER_BOUND, 1);
		jsonobj1.put(AxisConfigurations.KEY_UPPER_BOUND, 1);

		jsonobj2 = new JSONObject();
		jsonobj2.put(AxisConfigurations.KEY_SCALABLE, false);
		jsonobj2.put(AxisConfigurations.KEY_PANNABLE, false);
		jsonobj2.put(AxisConfigurations.KEY_DEFAULTSCALE, false);
		jsonobj2.put(AxisConfigurations.KEY_MINSCALE, 1);
		/*jsonobj2.put(AxisConfigurations.KEY_MAXSCALE, 1);
		jsonobj2.put(AxisConfigurations.KEY_LOWER_BOUND, 1);
		jsonobj2.put(AxisConfigurations.KEY_UPPER_BOUND, 1);*/

	}
	
}
