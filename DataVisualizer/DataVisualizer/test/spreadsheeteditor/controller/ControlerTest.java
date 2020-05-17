package spreadsheeteditor.controller;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import spreadsheeteditor.controller.DataVisualiserApplicationController;
import spreadsheeteditor.model.SpreadSheetEditorModel;
import spreadsheeteditor.view.SpreedsheetEditorPane;

class ControlerTest {

	
	@Test
	void TestControllerConstructor(){
		SpreadSheetEditorModel t = new SpreadSheetEditorModel();
		SpreedsheetEditorPane v = new SpreedsheetEditorPane();
		new DataVisualiserApplicationController(t, v);
		assertTrue(true);	
	}

	
}
