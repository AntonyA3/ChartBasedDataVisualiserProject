package chartEditor.view;

import java.util.Collection;

import chartEditor.model.ChartType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class DataConfigurationPane extends VBox{
	private TextField title;
	private ChoiceBox<String> xAxisChoice;
	private ChoiceBox<String> yAxisChoice;
	private Label titleLabel;
	private Label xLabel;
	private Label yLabel;
	
	public DataConfigurationPane() {		
		this.titleLabel = new Label("title");
		this.xLabel = new Label("x");
		this.yLabel = new Label("y");
		this.title = new TextField();
		this.xAxisChoice = new ChoiceBox<>();
		this.yAxisChoice = new ChoiceBox<>();
		
		GridPane g = new GridPane();
		g.add(titleLabel, 0, 0);
		g.add(xLabel, 0, 1);
		g.add(yLabel, 0, 2);
		g.add(title, 1, 0);
		g.add(xAxisChoice, 1, 1);
		g.add(yAxisChoice, 1, 2);
		this.getChildren().add(g);
	}
	

	public void setNameConvention(ChartType c) {
		switch(c) {
		case SCATTERCHART:
			this.title.setVisible(true);
			this.titleLabel.setVisible(true);
			this.titleLabel.setText("series title");
			this.xLabel.setText("x");
			this.yLabel.setText("y");
			break;
		case BARCHART:
			this.titleLabel.setText("series title");
			this.title.setVisible(true);
			this.titleLabel.setVisible(true);
			this.xLabel.setText("catagory");
			this.yLabel.setText("frequency");
			break;
		case PIECHART:
			
			this.titleLabel.setText("series title");
			this.title.setVisible(false);
			this.titleLabel.setVisible(false);
			this.xLabel.setText("catagory");
			this.yLabel.setText("frequency");
			break;
		
		}

	}
	
	public ChoiceBox<String> getxAxisChoice() {
		return this.xAxisChoice;
	}
	
	public void populateXChoice( Collection<String> c) {
		xAxisChoice.getItems().setAll(c);
	}
	
	public void populateYChoice( Collection<String> c) {
		yAxisChoice.getItems().setAll(c);
	}

		
	public void populateChoiceBoxes(Collection<String> c) {

		xAxisChoice.getItems().setAll(c);
		yAxisChoice.getItems().setAll(c);
	}
	public TextField getSeriesTitleField() {
		return title;
	}
	
	public ChoiceBox<String> getyAxisChoice() {
		return this.yAxisChoice;
	}

}
