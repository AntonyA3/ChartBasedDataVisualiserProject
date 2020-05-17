package chartEditor.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class BasicConfigurationPane extends VBox {
	private TextField titleField;
	public BasicConfigurationPane() {
		this.titleField = new TextField();
		
		GridPane g = new GridPane();
		g.add(new Label("title"), 0,0);
		g.add(titleField, 1, 0);
		this.getChildren().add(g);
		

	}
	
	public TextField getTitleField() {
		return titleField;
	}
	
	

}
