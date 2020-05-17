package spreadsheeteditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import spreadsheeteditor.controller.DataVisualiserApplicationController;
import spreadsheeteditor.model.SpreadSheetEditorModel;
import spreadsheeteditor.view.SpreedsheetEditorPane;

public class Main extends Application {
	private static int MIN_WINDOW_WIDTH = 320;
	private static int MIN_WINDOW_HEIGHT = 240;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		SpreedsheetEditorPane view = new SpreedsheetEditorPane();
		new DataVisualiserApplicationController(new SpreadSheetEditorModel(),  view);
		
		stage.setMinWidth(MIN_WINDOW_WIDTH);
		stage.setMinHeight(MIN_WINDOW_HEIGHT);
		stage.setScene(new Scene(view));
		stage.show();
		
		
	}
	
	@Override
	public void init() throws Exception {
	
		
	}

}