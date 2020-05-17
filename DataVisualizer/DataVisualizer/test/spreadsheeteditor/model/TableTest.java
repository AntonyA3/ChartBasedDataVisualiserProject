package spreadsheeteditor.model;
/*package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

class TableTest {


	
	@Test
	void TestConstruction(){
		DataVisualiserModel t = new DataVisualiserModel();
		
		assertEquals("test constructor height", 1, t.getHeight());
		assertEquals("test constructor height", 1, t.getWidth());
	}
	
	@Test
	void TestGetSetColumnNameValid() {
		DataVisualiserModel t = new DataVisualiserModel();
		t.setWidth(5);
		
		String s = "hello world";
		t.setColumnName(2,s );
		assertTrue(s.equals(t.getColumnName(2)), "vaild column name");
		
	}
	@Test
	void TestGetSetColumnNameInvalid(){
		DataVisualiserModel t = new DataVisualiserModel();
		String s = "hello world";
		t.setColumnName(10,s );
		assertEquals( "invalid column name", null, t.getColumnName(10));
	}
	
	@Test
	void TestGetSetColumnNameBoundary(){
		DataVisualiserModel t = new DataVisualiserModel();
		t.setWidth(5);
		
		String s = "hello world";
		t.setColumnName(4,s );
		assertTrue(t.getColumnName(4).equals("hello world"), "lower bound column name");
		
		t.setColumnName(0,s );
		assertTrue(t.getColumnName(0).equals("hello world"), "upper bound column name");
		
		t.setColumnName(5,s);
		assertEquals("lowest point of failure column name",null, t.getColumnName(5));
	}
	
	
	@Test
	void testValidGetSetDataAt(){
		DataVisualiserModel t = new DataVisualiserModel();
		t.setWidth(5);
		t.setHeight(5);
		
		String s = new String("foo bar");

		t.setDataAt(2, 2, s);
		assertTrue(t.getDataAt(2, 2).equals(s), "valid get set data at");
	}
	
	
	@Test
	void testInvalidGetSetDataAt(){
		DataVisualiserModel t = new DataVisualiserModel();
		t.setWidth(5);
		t.setHeight(5);
		
		String s = new String("foo bar");
		String bgS = new String(new char[100]);
		
		t.setDataAt(10, 10, s);
		assertEquals("valid get set data at", null, t.getDataAt(10, 10));
		
		Object oldValue = t.getDataAt(2, 2);
		t.setDataAt(2, 2, bgS);
		assertEquals( "valid get set data at", oldValue, t.getDataAt(2, 2));
	}
	
	@Test
	void testBoundaryGetSetDataAt(){
		DataVisualiserModel t = new DataVisualiserModel();
		t.setWidth(5);
		t.setHeight(5);
		
		String s = new String("foo bar");
		String bgS = new String(new char[50]);
		String tbgS = new String(new char[51]);
		String smS = new String();
		
		t.setDataAt(0, 0, s);
		assertEquals( "lowest natural number for x, y", s ,t.getDataAt(0, 0));
		
		t.setDataAt(4, 4, s);
		assertEquals("upper bound for x, y",s, t.getDataAt(4, 4));;
		
		
		t.setDataAt(5, 5, s);
		assertEquals("failure boundary for x and y", null, t.getDataAt(5, 5));
		
		
		t.setDataAt(2, 2, bgS);
		assertEquals("upper pass boundary for data", bgS, t.getDataAt(2, 2));
		
		String initialdata = (String) t.getDataAt(2, 2);
		t.setDataAt(2, 2, tbgS);
		assertEquals( "lowest point of failure boundary for data",initialdata,t.getDataAt(2, 2));
		
		
		t.setDataAt(2, 2, smS);
		assertEquals("lowest natural string size is valid for data",smS,t.getDataAt(2,2) );
		
	}
	
	@Test
	void TestValidGetSetDataAtRow(){
		DataVisualiserModel t = new DataVisualiserModel();
		t.setWidth(5);
		t.setHeight(5);
		int row = 2;
		for(int i = 0; i < t.getWidth(); i++) {
			t.setDataAt(i,row,"hello"+i);
		}
		
		for(int i = 0; i < t.getWidth(); i++) {
			assertTrue(t.getDataAt(i, row).equals("hello" + i), "test valid getSetDataAtRow");
		};
		
		

	}
	
	@Test
	void TestInvalidGetSetDataAtRow(){
		DataVisualiserModel t = new DataVisualiserModel();
		t.setWidth(5);
		t.setHeight(5);
		
		int row = 10;
		for(int i = 0; i < t.getWidth(); i++) {
			t.setDataAt(i,row,"hello"+i);
		}
		
		for(int i = 0; i < t.getWidth(); i++) {
			assertEquals("test invalid getSetDataAtRow", null,t.getDataAt(i, row));
		};
		

	}
	@Test
	void TestBoundaryGetDataAtRow(){
		DataVisualiserModel t = new DataVisualiserModel();
		t.setWidth(5);
		t.setHeight(5);
		
		int firstRow = 0;
		int finalRow = 4;
		int lowestFalseRow = 5;
		int notNaturalRow = -1;
		
		for(int i = 0; i < t.getWidth(); i++) {
			t.setDataAt(i,firstRow,"hello"+i);
		}
		
		for(int i = 0; i < t.getWidth(); i++) {
			assertTrue(t.getDataAt(i, firstRow).equals("hello" + i), "test valid getSetDataAtRow");
		};
		//
		for(int i = 0; i < t.getWidth(); i++) {
			t.setDataAt(i,finalRow,"hello"+i);
		}
		
		for(int i = 0; i < t.getWidth(); i++) {
			assertTrue(t.getDataAt(i, finalRow).equals("hello" + i), "test valid getSetDataAtRow");
		};
		//
		
		for(int i = 0; i < t.getWidth(); i++) {
			t.setDataAt(i,finalRow,"hello"+i);
		}
		
		for(int i = 0; i < t.getWidth(); i++) {
			assertEquals("test lowest invalid getSetDataAtRow", null, t.getDataAt(i, lowestFalseRow));

		};
		
		for(int i = 0; i < t.getWidth(); i++) {
			t.setDataAt(i,notNaturalRow,"hello"+i);
		}
		
		for(int i = 0; i < t.getWidth(); i++) {
			assertEquals("test lowest invalid getSetDataAtRow", null,t.getDataAt(i, notNaturalRow));

		};


	}
	@Test
	void TestValidGetDataAtColumn(){
		DataVisualiserModel t = new DataVisualiserModel();
		t.setWidth(5);
		t.setHeight(5);

		
		int column = 2;
		for(int i = 0; i < t.getWidth(); i++) {
			t.setDataAt(column,i,"hello"+i);
		}
		
		for(int i = 0; i < t.getWidth(); i++) {
			assertTrue(t.getDataAt(column, i).equals("hello" + i), "test valid getSetDataAtColumn");
		};
	}
	@Test
	void TestInvalidGetDataAtColumn(){
		DataVisualiserModel t = new DataVisualiserModel();
		t.setWidth(5);
		t.setHeight(5);

		int column = 10;
		for(int i = 0; i < t.getWidth(); i++) {
			t.setDataAt(column,i,"hello"+i);
		}
		
		for(int i = 0; i < t.getWidth(); i++) {
			assertEquals("test invalid getSetDataAtColumn", null,t.getDataAt(column, i));
		};
		

	}
	@Test
	void TestBoundaryGetDataAtColumn(){
		DataVisualiserModel t = new DataVisualiserModel();
		t.setWidth(5);
		t.setHeight(5);

		int firstColumn = 0;
		int finalColumn = 4;
		int lowestFalseColumn = 5;
		int notNaturalColumn = -1;
		
		for(int i = 0; i < t.getWidth(); i++) {
			t.setDataAt(firstColumn,i,"hello"+i);
		}
		
		for(int i = 0; i < t.getWidth(); i++) {
			assertTrue(t.getDataAt(firstColumn, i).equals("hello" + i), "test valid getSetDataAtRow");
		};
		//
		for(int i = 0; i < t.getWidth(); i++) {
			t.setDataAt(finalColumn,i,"hello"+i);
		}
		
		for(int i = 0; i < t.getWidth(); i++) {
			assertTrue(t.getDataAt(finalColumn, i).equals("hello" + i), "test valid getSetDataAtRow");
		};
		//
		
		for(int i = 0; i < t.getWidth(); i++) {
			t.setDataAt(lowestFalseColumn,i,"hello"+i);
		}
		
		for(int i = 0; i < t.getWidth(); i++) {
			assertEquals("test lowest invalid getSetDataAtRow", null,t.getDataAt(lowestFalseColumn, i));

		};
		
		for(int i = 0; i < t.getWidth(); i++) {
			t.setDataAt(notNaturalColumn,i,"hello"+i);
		}
		
		for(int i = 0; i < t.getWidth(); i++) {
			assertEquals("test lowest invalid getSetDataAtRow",null,t.getDataAt(notNaturalColumn, i) );

		};
	}
	@Test
	void TestValidGetSetHeight(){
		DataVisualiserModel t = new DataVisualiserModel();
		int height = 5;
		
		

	}
	@Test
	void TestInvalidGetSetHeight(){
		DataVisualiserModel t = new DataVisualiserModel();
		int height = -10;
	}
	
	@Test
	void TestBoundaryGetSetHeight(){
		DataVisualiserModel t = new DataVisualiserModel();
		int tooLowHeight = 0;
		int minimumHeight = 1;
		int maximumHeight = 1000;
		int tooMuchHeight = 1001;
		

		
		t.setHeight(minimumHeight);
		assertEquals("lowest valid width",minimumHeight, t.getHeight());
		
		t.setHeight(maximumHeight);
		assertEquals("highest valid width",maximumHeight, t.getHeight());
		
		
		int initialHeight = t.getHeight();
		t.setHeight(tooLowHeight);
		assertEquals("invalid an 0 or below",initialHeight, t.getHeight());


		initialHeight = t.getHeight();
		t.setHeight(tooMuchHeight);
		assertEquals("invalid at greater than 1000",initialHeight, t.getHeight());

	}
	@Test
	void TestValidGetSetWidth(){
		DataVisualiserModel t = new DataVisualiserModel();
		int width = 5;
	
		t.setWidth(width);
		assertEquals("Valid width",5, t.getWidth());

	}
	@Test
	void TestInvalidGetSetWidth(){
		DataVisualiserModel t = new DataVisualiserModel();
		int width = -10;
		int initialWidth = t.getWidth();
		
		t.setWidth(width);
		assertEquals("Valid width",initialWidth, t.getWidth());

	}
	@Test
	void TestBoundaryGetSetWidth(){
		DataVisualiserModel t = new DataVisualiserModel();
		int tooLowWidth = 0;
		int minimumWidth = 1;
		int maximumWidth = 1000;
		int tooMuchWidth = 1001;
		
		t.setWidth(minimumWidth);
		assertEquals("lowest valid width",minimumWidth, t.getWidth());
		
		t.setWidth(maximumWidth);
		assertEquals("highest valid width",maximumWidth, t.getWidth());
		
		
		int initialWidth = t.getWidth();
		t.setWidth(tooLowWidth);
		assertEquals("invalid an 0 or below",initialWidth, t.getWidth());


		initialWidth = t.getWidth();
		t.setWidth(tooMuchWidth);
		assertEquals("invalid at greater than 1000",initialWidth, t.getWidth());



	}
	
	@Test
	void TestIncrementHeight(){
		DataVisualiserModel t = new DataVisualiserModel();
		
		t.setHeight(10);
		t.incrementHeight();
		assertEquals(11, t.getHeight());
		
		t.setHeight(1000);
		t.incrementHeight();
		assertEquals(1000, t.getHeight());
		

	}
	@Test
	void TestDecrementHeight(){
		DataVisualiserModel t = new DataVisualiserModel();
		
		t.setHeight(10);
		t.decrementHeight();
		assertEquals(9, t.getHeight());
		
		t.setHeight(1);
		t.decrementHeight();
		assertEquals(1, t.getHeight());

	}
	@Test
	void TestIncrementWidth(){
		DataVisualiserModel t = new DataVisualiserModel();
		
		t.setWidth(10);
		t.incrementWidth();
		assertEquals(11, t.getWidth());
		
		t.setWidth(1000);
		t.incrementWidth();
		assertEquals(1000, t.getWidth());
	
	}
	@Test
	void TestDecrementWidth(){
		DataVisualiserModel t = new DataVisualiserModel();

		t.setWidth(10);
		t.decrementWidth();
		assertEquals(9, t.getWidth());

		t.setWidth(1);
		t.decrementWidth();
		assertEquals(1, t.getWidth());





	}


}*/
