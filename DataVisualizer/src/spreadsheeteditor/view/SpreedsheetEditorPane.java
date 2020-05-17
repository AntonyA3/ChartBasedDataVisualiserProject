package spreadsheeteditor.view;


import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import spreadsheeteditor.model.SpreadSheetEditorModel;

public class SpreedsheetEditorPane extends BorderPane {
	private static int FIXED_ROW_COUNT = 100;
	private static int FIXED_COLUMM_COUNT = 100;
	private static String NULL_REPRESENTATION = "";
	
	private SpreadsheetView spreedsheetView;
	private Button updateTableButton;
	private Button presentationViewButton;
	private MenuItem saveButton;
	private MenuItem loadButton;
	
	public SpreedsheetEditorPane() {
		this.spreedsheetView = new SpreadsheetView();
		this.updateTableButton = new Button("Update Table");
		this.presentationViewButton = new Button("To Presentation View");
		MenuBar menuBar = new MenuBar();
		Menu filemenu = new Menu("File");
		menuBar.getMenus().add(filemenu);
		this.saveButton = new MenuItem("save");
		this.loadButton = new MenuItem("load");
		filemenu.getItems().addAll(this.saveButton, this.loadButton);
		this.setTop(menuBar);
		this.setCenter(spreedsheetView);
		VBox bottom  = new VBox();
		bottom.getChildren().addAll(updateTableButton, presentationViewButton);
		this.setBottom(bottom);
		
		ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();
		for (int row = 0; row <FIXED_ROW_COUNT; row++) {
			ObservableList<SpreadsheetCell> list = FXCollections.observableArrayList();
			for (int column = 0; column < FIXED_COLUMM_COUNT; column++) {
					list.add(SpreadsheetCellType.STRING.createCell(row, column, 1, 1, NULL_REPRESENTATION));
			}
			rows.add(list);
		}
		spreedsheetView.getGrid().getRows().setAll(rows);

				
	
	}
	
	public MenuItem getSaveButton() {
		return saveButton;
	}
	
	public MenuItem getLoadButon() {
		return loadButton;
	}
	
	public SpreadsheetView getSpreedsheetView() {
		return spreedsheetView;
	}
	
	public Button getUpdateTableButton() {
		return updateTableButton;
	}
	
	public Object[][] getGrid() {
		int largestRow = -1;
		int largestColumn = -1;
		
		for (int row = 0; row <this.spreedsheetView.getGrid().getRowCount(); row++) {
			for (int column = 0; column < this.spreedsheetView.getGrid().getColumnCount(); column++) {
				Object item = this.spreedsheetView.getGrid().getRows().get(row).get(column).getItem();
				if (!NULL_REPRESENTATION.equals(item) && item != null) {
					largestColumn = column;
					largestRow = row;
				}
			}
		}
		
		Object[][] data = new Object[largestRow+1][largestColumn+1];
		for (int row = 0; row <= largestRow; row++) {
			for (int column = 0; column <= largestColumn; column++) {
				data[row][column] = this.spreedsheetView.getGrid().getRows().get(row).get(column).getItem();
			}
		}
		return data;
	}
	
	public Button getPresentationViewButton() {
		return presentationViewButton;
	}
	
	public void setGrid(SpreadSheetEditorModel model) {
		int rc = Math.min(model.getRowCount(),FIXED_ROW_COUNT);
		int cc = Math.min(model.getColumnCount(), FIXED_COLUMM_COUNT);
		
		for (int row = 0; row <rc; row++) {
			for (int column = 0; column < cc; column++) {
				Object item = model.getDataAt(row,column);
				if (item == null) {
					spreedsheetView.getGrid().setCellValue(row, column, NULL_REPRESENTATION);
				}else {
					spreedsheetView.getGrid().setCellValue(row, column, item);
	
				}
			}
		}

	}
	

	
}
