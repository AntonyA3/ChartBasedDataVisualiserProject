package spreadsheeteditor.controller;



import java.io.IOException;





import chartEditor.controller.ChartEditorController;
import chartEditor.model.ChartConfigurations;
import chartEditor.view.ChartEditorRootPane;
import chartPresenter.controller.ChartPresentationController;
import chartPresenter.view.ChartPresenterRootPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.control.Alert;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import spreadsheeteditor.model.IncompatibleCSVFormatException;
import spreadsheeteditor.model.SpreadSheetEditorModel;
import spreadsheeteditor.view.SpreedsheetEditorPane;

public class DataVisualiserApplicationController {
	private SpreadSheetEditorModel model;
	private SpreedsheetEditorPane view;


	public DataVisualiserApplicationController(SpreadSheetEditorModel model, SpreedsheetEditorPane view) {
		this.model = model;
		this.view = view;
		this.view.getUpdateTableButton().setOnMouseClicked(new OpenChartGenerator());
		this.view.getPresentationViewButton().setOnMouseClicked(new OpenPresentationView());
		this.view.getSaveButton().setOnAction(new SaveSpreadSheetEvent());
		this.view.getLoadButon().setOnAction(new LoadSpreadSheetEvent());

	}

	
	
	private class OpenPresentationView implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent event) {

			Stage stage = new Stage();
			ChartPresenterRootPane view2 = new ChartPresenterRootPane();
			new ChartPresentationController(view2);
			stage.setScene(new Scene(view2));
			stage.show();
		}
	}
	private class OpenChartGenerator  implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent event) {
			model.setGrid(view.getGrid());
			
			if (model.getRowCount() > 1) {
				Stage stage = new Stage();
				ChartEditorRootPane chartOpeningView = new ChartEditorRootPane();
				ChartConfigurations model2 = new ChartConfigurations(model.getGrid());

				new ChartEditorController(chartOpeningView, model2);
				stage.setScene(new Scene(chartOpeningView));
				stage.show();
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Unable to Create Chart");
				alert.setContentText("Dataset Too Small must contain a header row");
				
				alert.showAndWait();
			}
			
		}
	}

	private class SaveSpreadSheetEvent implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			try {
				model.setGrid(view.getGrid());
				model.saveGrid();
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("File Saving Error");
				alert.setContentText("File format appears to be invalid");
				alert.showAndWait();
			}
		}
	}

	private class LoadSpreadSheetEvent implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			try {
				model.loadGrid();
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("File Loading Error");
				alert.setContentText("File format appears to be invalid");
				alert.showAndWait();
			}catch (IncompatibleCSVFormatException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("File Loading Error");
				alert.setContentText("File format appears to be invalid");
				alert.showAndWait();
			}finally {
				view.setGrid(model);

			}
		}
	}
}