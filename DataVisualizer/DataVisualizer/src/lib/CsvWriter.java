package lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CsvWriter {
	private PrintWriter printWriter;
	
	
	public boolean open() {
		FileChooser filechooser = new FileChooser();
		File file = filechooser.showSaveDialog(new Stage());
		
		if (file != null) {
			try {
				this.printWriter = new PrintWriter(file);
				return true;
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
				return false;
			}
		}
		return false;
		
		
	}
	
	public void appendCsVString(String s) {
		s = s.trim();
		printWriter.append("\"");
		printWriter.append(s);
		printWriter.append("\"");
	}
	
	public void appendSeperator() {
		printWriter.append(",");
	}
	public void appendCsVInt(int i) {
		printWriter.append(String.valueOf(i));
	}
	
	public void appendCsVDouble(double d) {
		printWriter.append(String.valueOf(d));
	}
	
	public void close() {
		this.printWriter.close();
	}

	public void appendNewLine() {
		printWriter.append("\n");
		
	}

}
