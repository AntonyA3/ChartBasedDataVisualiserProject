package chartEditor.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SortingConfigurationTest {

	private SortingConfiguration sortingConf = new SortingConfiguration();
	
	@Test
	void testConstructor() {
		SortingConfiguration sc = new SortingConfiguration();
		assertFalse(sc.isSortByFrequencyAccending());
		assertFalse(sc.isSortByFrequencyDecending());
		assertFalse(sc.isSortByNameAccending());
		assertFalse(sc.isSortByNameDecending());
		assertEquals(SortMode.FrequencyAccending, sc.getSortingMode());
	}
	
	@Test
	void testGetSetSortMode() {
		sortingConf.setSortingMode(SortMode.FrequencyDeccending);
		assertEquals(SortMode.FrequencyDeccending, sortingConf.getSortingMode());
	}
	
	@Test
	void testGetSetSortByFrequencyAccending() {
		sortingConf.setSortByFrequencyAccending(true);
		assertTrue(sortingConf.isSortByFrequencyAccending());
		
		sortingConf.setSortByFrequencyAccending(false);
		assertFalse(sortingConf.isSortByFrequencyAccending());
		
	}
	
	@Test
	void testGetSetSortByFrequencyDecending() {
		sortingConf.setSortByFrequencyDecending(true);
		assertTrue(sortingConf.isSortByFrequencyDecending());
		
		sortingConf.setSortByFrequencyDecending(false);
		assertFalse(sortingConf.isSortByFrequencyDecending());

		
	}
	
	@Test
	void testGetSetSortByNameAccending() {
		
		sortingConf.setSortByNameAccending(true);
		assertTrue(sortingConf.isSortByNameAccending());
		
		sortingConf.setSortByNameAccending(false);
		assertFalse(sortingConf.isSortByNameAccending());
		
	}
	@Test
	void testGetSetSortByNameDecending() {
		
		sortingConf.setSortByNameDecending(true);
		assertTrue(sortingConf.isSortByNameDecending());
		
		sortingConf.setSortByNameDecending(false);
		assertFalse(sortingConf.isSortByNameDecending());
		
	}
	
	@Test
	void testIsLive() {
		sortingConf.setSortByFrequencyAccending(false);
		sortingConf.setSortByFrequencyDecending(false);
		sortingConf.setSortByNameAccending(false);
		sortingConf.setSortByNameDecending(false);
		assertFalse(sortingConf.isLive());
		
		sortingConf.setSortByFrequencyAccending(false);
		sortingConf.setSortByFrequencyDecending(false);
		sortingConf.setSortByNameAccending(true);
		sortingConf.setSortByNameDecending(true);
		assertTrue(sortingConf.isLive());
		
		sortingConf.setSortByFrequencyAccending(true);
		sortingConf.setSortByFrequencyDecending(true);
		sortingConf.setSortByNameAccending(true);
		sortingConf.setSortByNameDecending(true);
		assertTrue(sortingConf.isLive());
	}
		
}


