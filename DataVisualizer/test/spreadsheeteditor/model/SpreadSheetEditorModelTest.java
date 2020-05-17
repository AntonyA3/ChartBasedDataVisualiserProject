package spreadsheeteditor.model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import java.io.IOException;
import java.util.LinkedList;



import org.junit.jupiter.api.Test;


class SpreadSheetEditorModelTest {
	private static Object[][] standardGrid = new Object[][] {{"hello",1.0},{"442", "f42"}};
	//private static Object[][] emptyGrid = new Object[0][0];


	@Test
	void testConstructor() {
		SpreadSheetEditorModel model = new SpreadSheetEditorModel();
		//for all inputs; rowcount = 0, columncount = 0, getdataat 0,0 throws exception
		assertEquals(0,model.getRowCount());
		assertEquals(0, model.getColumnCount());
		boolean failed = false;
		try {
			model.getDataAt(0, 0);
		}catch(Exception e) {
			failed = true;
		}finally {
			assertTrue(failed, "failed as expected");
		}
		

	}
	

	
	@Test
	void testGetSetGrid() {
		/* for all inputs, the rowcount should equal the input rowcount,
		the columncount should equal the input grid columncount,
		the data of the input grid should always be the same as that of the output grid
		
		boundary inputs: Object[0][0], Object[n][n] unpopulated; boundInputN
		valid inputs: Object[n][n] {populated} Object [n][r]{populated} validInputN
		invalid inputs: megaArrays of objects, other datatypes invalidInput
		
		 */
		SpreadSheetEditorModel model = new SpreadSheetEditorModel();
		Object[][] boundInput0 = new Object[0][0];
		Object[][] boundInput1 = new Object[2][2];
		Object[][] validInput0 = new Object[][] {{"hello",1.0},{"442", "f42"}};
		Object[][] validInput1 = new Object[][] {{"hello",1.0,"city"},{"442", "f42","city"}};
	
		LinkedList<Object[][]> validInputList = new LinkedList<>();
		validInputList.add(boundInput0);
		validInputList.add(boundInput1);
		validInputList.add(validInput0);
		validInputList.add(validInput1);
		
		validInputList.stream().forEach(input ->{
			model.setGrid(input);
			assertEquals(input.length, model.getRowCount());
			if (input.length > 0) {
				assertEquals(input[0].length, model.getColumnCount());
			}else {
				assertEquals(0, model.getColumnCount());
			}
			for (int row = 0; row < input.length; row++ ) {
				for (int column = 0; column < input.length; column++ ) {
					assertEquals(input[row][column], model.getDataAt(row, column));
				}
			}
		});
		

	}
	
	@Test
	void testGetSetDataAt() {
		SpreadSheetEditorModel model = new SpreadSheetEditorModel();
		/*
		 * When using a 2*2 array,
		 * validInputs = setdata at (1,1, data);
		 * boundaryInputs = setsata at (0,0, data), (1,1), null for data
		 * failed inputs = setdata at (2,2,data), verylong string for data
		 */	
		//int validRows = 1;
		//int validColumns =1;
		//int invalidRow = 2;
		//int invalidColumn = 2;
		String validSampleData = "hello";
		String boundarySampleData = null;
		model.setGrid(standardGrid);
		//normal
		model.setDataAt(0, 0, validSampleData);
		assertEquals(validSampleData, model.getDataAt(0, 0));
		//boundary
		model.setDataAt(0, 0, boundarySampleData);
		assertEquals(boundarySampleData, model.getDataAt(0, 0));
		//normal
		model.setDataAt(0, 1, validSampleData);
		assertEquals(validSampleData, model.getDataAt(0, 1));
		//invalid
		boolean failed = false;
		try {
			model.setDataAt(2, 0, validSampleData);
		}catch (Exception e) {
			failed = true;
		}finally {
			assert(failed);
		}

		//invalid
		failed = false;
		try {
			model.setDataAt(0, 2, validSampleData);
		}catch (Exception e) {
			failed = true;
		}finally {
			assert(failed);
		}

	}

	/*@Test
	void testColumnAndRowCountAfterSet() {
		DataVisualiserModel model = new DataVisualiserModel();
		Object[][] grid = new Object[2][3];
		
		grid[0][0] = "hello";
		grid[0][1] = 1.0;
		grid[1][0] = "442";
		grid[1][1] ="f42";
		grid[1][2] = "yes"; 
		
		model.setGrid(grid);
		
		assertEquals(model., model.getRowCount());
		assertEquals(, model.getColumnCount());
		
		
	}*/
	
	@Test
	void TestSaveToCSV() {
		/* test save to csv, this will test. It will save a grid that has been inserted into
		 * the class. It will then read the file to test whether it is stored in the right format.
		 * 
		 * validInput: a csv file * intended output, a string that is in csvformatIn a file
		 * invalidInput a non csvFile
		 * boundaryInput empty grid * should output file that is empty*/
		SpreadSheetEditorModel model = new SpreadSheetEditorModel();
		Object[][] sg = new Object[][] {{"hello",1.0},{"442", "f42"}};
		model.setGrid(sg);
		File file = new File("res/spreadsheet-save-tests.csv");
		
		try {
			model.saveToCsv(file);
			assertTrue(true);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}		
		
		
		sg = new Object[0][0];
		model.setGrid(sg);
		file = new File("res/spreadsheet-save-tests.csv");
		
		try {
			model.saveToCsv(file);
			assertTrue(true);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}		
		
		
	}
	
	@Test
	void TestLoadFile() { 
		/*
		 * Test the csv file being read, the file format has certian rules
		 * https://stackabuse.com/reading-and-writing-csv-files-with-node-js/
		 * an 
		 * invalid file won't change the state of the model, it will instead throw an exception
		 *valid file will change the state of the class
		 */
		SpreadSheetEditorModel model = new SpreadSheetEditorModel();


		
		File validfile0 = new File("res/valid-csv-file.csv");
		File boundaryfile0 = new File("res/completely-empty-file.csv");
		File boundaryFile1 = new File("res/csv-file-of-nulls.csv");
		File boundaryFile2 = new File("res/syntax-confused-csv-file.csv");
		File invalidFile0 = new File("res/invalid-csv-file.csv");
		

		try {
			model.loadFromCsV(validfile0);
			assertEquals(model.getRowCount(), 4);
			assertEquals(model.getColumnCount(), 2);
			assertEquals(model.getDataAt(0, 0), "hello");
			assertEquals(model.getDataAt(3, 1), 23.42);
		} catch (IOException e) {
			assertTrue(false);
		} catch (IncompatibleCSVFormatException e) {
			assertTrue(false);
			e.printStackTrace();
		}	
		
		try {
			model.loadFromCsV(boundaryfile0);
			assertEquals(0, model.getRowCount());
			assertEquals(0,model.getColumnCount());
		} catch (IOException e) {
			assertTrue(false);
			e.printStackTrace();
		} catch (IncompatibleCSVFormatException e) {
			assertTrue(false);
			e.printStackTrace();
		}

		try {
			model.loadFromCsV(boundaryFile1);	
			assertEquals(5, model.getRowCount());
			assertEquals(13, model.getColumnCount());
			for (int row = 0; row< model.getRowCount(); row++) {
				for (int column = 0; column< model.getColumnCount(); column++) {
					assertEquals(model.getDataAt(row, column), null);
				}
			}
		} catch (IOException e) {
			assertTrue(false);
		} catch (IncompatibleCSVFormatException e) {
			assertTrue(false);
			e.printStackTrace();
		}
		
		try {
			model.loadFromCsV(boundaryFile2);	
			assertEquals(4, model.getRowCount());
			assertEquals(2, model.getColumnCount());
			assertEquals("\"hello\"", model.getDataAt(3, 0));
			assertEquals(",", model.getDataAt(3, 1));
			assertEquals("(1,2)", model.getDataAt(1, 0));

		} catch (IOException | IncompatibleCSVFormatException e) {
			assertTrue(false);
		} 
		
		try {
			model.loadFromCsV(invalidFile0);
		} catch (IOException e) {
			assertTrue(false);
		} catch (IncompatibleCSVFormatException e) {
			assertTrue(true);
			assertEquals(4, model.getRowCount());
			assertEquals(2, model.getColumnCount());
			assertEquals("\"hello\"", model.getDataAt(3, 0));
			assertEquals(",", model.getDataAt(3, 1));
			assertEquals("(1,2)", model.getDataAt(1, 0));
		}

	}

}
