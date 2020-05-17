package lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CsvReader {

	private BufferedReader reader;
	private int rowNumber;
	public boolean read() {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(new Stage());
		this.rowNumber = -1;
		 
		try {
			this.reader = new BufferedReader(new FileReader(file));
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}

	}
	public ObservableList<ObservableList<SpreadsheetCell>> readAllRecords(){
		ObservableList<ObservableList<SpreadsheetCell>>  rows = FXCollections.observableArrayList();

		Stream<String> grid = reader.lines();
		grid.forEach(x -> {
			rowNumber +=1;
			String[] data = x.split(",",-1);
			ObservableList<SpreadsheetCell> record = FXCollections.observableArrayList();

			for (int i = 0; i < data.length; i++) {
				if (data[i].startsWith("\"") && data[i].endsWith("\"")) {
					String newData = data[i].substring(1, data[i].length()-1);
					record.add(SpreadsheetCellType.STRING.createCell(rowNumber, i, 1, 1, newData));
				}
				else{
					if (!data[i].isEmpty()) {
						Integer newData =  Integer.parseInt(data[i]);
						record.add(SpreadsheetCellType.INTEGER.createCell(rowNumber, i, 1, 1, newData));
					}else {
						record.add(SpreadsheetCellType.INTEGER.createCell(rowNumber, i, 1, 1, null));

					}
					
				}
					
				
			}
			rows.add(record);
			
		});
		
		return rows;
		
	}
	
	
	
	
	public void close() throws IOException {
		reader.close();
	}
}
