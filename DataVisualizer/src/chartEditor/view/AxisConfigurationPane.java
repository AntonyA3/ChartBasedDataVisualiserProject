package chartEditor.view;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class AxisConfigurationPane extends VBox {
	private CheckBox pannable;
	private CheckBox scalable;
	private TextField maxscalefactor;
	private TextField minscalefactor;
	private TextField lowerbound;
	private TextField upperbound;
	private BooleanProperty validScaleRangeProperty;
	
	public AxisConfigurationPane() {
		this.validScaleRangeProperty = new SimpleBooleanProperty();
		this.pannable = new CheckBox("Data Panning Available");
		this.setPadding(new Insets(10));
		this.scalable = new CheckBox("Allow Scaling");
		this.lowerbound = new TextField();
		this.upperbound= new TextField();
		this.maxscalefactor = new TextField();
		this.minscalefactor = new TextField();
		
		
		minscalefactor.setPromptText("Default: 0.9");
		minscalefactor.textProperty().addListener((u,o,n) ->{
			if(!n.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
		
				minscalefactor.setText(o);
			}	

		});
		
		maxscalefactor.setPromptText("Default: 1.1");
		maxscalefactor.textProperty().addListener((u,o,n) ->{
			if(!n.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
				
				maxscalefactor.setText(o);
			}	

		});
		
		lowerbound.setPromptText("Default: Automatic");
		lowerbound.textProperty().addListener((u,o,n) ->{
			if(!n.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
		
				lowerbound.setText(o);
			}	
		});
		
		upperbound.setPromptText("Default: Automatic");
		upperbound.textProperty().addListener((u,o,n) ->{
			if(!n.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
		
				upperbound.setText(o);
			}	
		});
		

		GridPane g = new GridPane();
		
		g.add(this.pannable, 0, 0);
		
		g.add(new Label("Lower Bound"), 0, 1);
		g.add(this.lowerbound, 1, 1);
		
		g.add(new Label("Upper Bound"), 0, 2);
		g.add(this.upperbound, 1, 2);
		
		g.add(this.scalable, 0, 3);
		g.add(new Label("Minimum Scale Factor"), 0, 4);
		g.add(this.minscalefactor, 1, 4);
		g.add(new Label("Maximum Scale Factor"), 0, 5);
		g.add(this.maxscalefactor, 1, 5);
		
		
		this.getChildren().addAll(g);
	}
	public TextField getLowerbound() {
		return lowerbound;
	}
	
	public TextField getMaxscalefactor() {
		return maxscalefactor;
	}
	
	public TextField getMinscalefactor() {
		return minscalefactor;
	}
	
	public CheckBox getPannable() {
		return pannable;
	}
	
	public CheckBox getScalable() {
		return scalable;
	}
	
	
	public TextField getUpperbound() {
		return upperbound;
	}
	
	public BooleanProperty getValidScaleRangeProperty() {
		return validScaleRangeProperty;
	}

}
