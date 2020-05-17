package chartEditor.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class ColorConfigurationTest {
	private ColorConfiguration colorConf = new ColorConfiguration();
	private String color0 = "#4D4D4D";
	private String color1 = "#F15854";
	private String color2 = "#F17CB0";
	@SuppressWarnings("unused")
	private ObservableList<String> colorchoices0;
	private ObservableList<String> colorchoices1;
	//private ObservableList<String> colorchoices2;
	
	public ColorConfigurationTest() {
		colorchoices0 = FXCollections.observableArrayList();
		
		colorchoices1 = FXCollections.observableArrayList();
		colorchoices1.addAll("#FF74E4","#FE72D2","#EA3749","#AA45FF","#BBAAFF");
		//colorchoices2 = FXCollections.observableArrayList();
	}
	
	@Test
	void getColorAssignedToID() {
		assertEquals(color0, ColorConfiguration.getColorFor(0));
		assertEquals(color1, ColorConfiguration.getColorFor(8));
		assertEquals(color0, ColorConfiguration.getColorFor(9));
		assertEquals(color2, ColorConfiguration.getColorFor(4));
		
	}
	
	@Test
	void testGetSetColourChoices() {
		colorConf.getColorChoices().setAll(colorchoices1);
		assertEquals("#FF74E4", colorConf.getColorAt(0));
		assertEquals("#BBAAFF", colorConf.getColorAt(4));
		assertEquals("#F15854", colorConf.getColorAt(8));
	}
	

	
}
