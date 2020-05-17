package spreadsheeteditor.model;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;

import java.util.stream.Collectors;



import javafx.stage.FileChooser;
import lib.CsvConversionUtility;

import javafx.stage.Stage;

public class SpreadSheetEditorModel {	
	private Object[][] grid;
	private int rowCount;
	private int columnCount;
	
	public SpreadSheetEditorModel() {
		this.grid = new Object[0][0];
		this.rowCount = 0;
		this.columnCount = 0;
	}
	

	public Object[][] getGrid() {
		return this.grid;
	}

	public void setGrid(Object[][] grid) {
		this.grid = grid;
		this.rowCount = grid.length;
		this.columnCount = 0;
		if (rowCount >=1) {
			this.columnCount = grid[0].length;
		}
	}
	
	public void clear() {
		this.grid = new Object[0][0];
		this.rowCount = 0;
		this.columnCount = 0;
	}
	public void loadFromCsV(File file) throws IOException, IncompatibleCSVFormatException {
		boolean failed = false;
		BufferedReader reader = new BufferedReader(new FileReader(file));		
		List<String> lines = reader.lines().collect(Collectors.toList());
		List<String[]> g = lines.stream().map(x -> x.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1)).collect(Collectors.toList());
		reader.close();
		int rc = g.size();
		int cc = 0;
		if (rc > 0) {
			cc = g.get(0).length;
		}
		
		
		failed = g.stream().map(x -> x.length).distinct().count() > 1;
	
		
		if (!failed) {
			this.rowCount = rc;
			this.columnCount = cc;
			this.grid = new Object[this.rowCount][this.columnCount];

			for (int row = 0; row < this.rowCount; row++) {
				for (int column = 0; column <this.columnCount; column++) {

					if (g.get(row)[column].isEmpty()) {
						this.grid[row][column] = null;
					}else if (g.get(row)[column].matches("\\d+(\\.\\d{1,2})?")) {
						this.grid[row][column] = Double.valueOf(g.get(row)[column]);
					}else {
						this.grid[row][column] = CsvConversionUtility.getStringValue(g.get(row)[column]);
					}
				}
			}

		}else {
			throw new IncompatibleCSVFormatException();
		}
	}
	
	public void saveGrid() throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Spreadsheet");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Spreadsheet", "*.csv"),
				new FileChooser.ExtensionFilter("All Files", "*.*"));
		
		File file = fileChooser.showSaveDialog(new Stage());
		if (file != null) {
			saveToCsv(file);
		}
		
	}
	
	public void loadGrid() throws IOException, IncompatibleCSVFormatException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Spreadsheet");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Spreadsheet", "*.csv"),
				new FileChooser.ExtensionFilter("All Files", "*.*"));
		File file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {
			loadFromCsV(file);
		}
	}
	
	public void saveToCsv(File file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for (int row = 0; row < this.rowCount; row++) {
			for (int column = 0; column < this.columnCount; column++) {
				Object cell = this.grid[row][column];	
				writer.append(CsvConversionUtility.toCsV(cell));
				
				if (column != this.grid[row].length - 1) {
					writer.append(CsvConversionUtility.getValueDelimetre());
				}
			}
			if (row != this.grid.length - 1) {
				writer.append(CsvConversionUtility.getRowDelimetre());	
			}
				
		}

		writer.close();

	}
	
	public int getRowCount() {
		return rowCount;
	}
	
	public int getColumnCount() {
		return columnCount;
	}

	public Object getDataAt(int row, int column) {
		return grid[row][column];
	}
	
	public void setDataAt(int row, int column, Object data) {
		this.grid[row][column] = data;
	}
	
	
}
