package chartEditor.view;


import chartEditor.model.ChartType;
import javafx.collections.ObservableList;

import javafx.scene.control.ChoiceBox;

import javafx.scene.layout.VBox;

public class ChartConfigurePane extends VBox {
	
	private ChoiceBox<ChartType> chartTypeSelector;
	private VBox chartEditor;
	
	public ChartConfigurePane() {
		
		this.chartTypeSelector = new ChoiceBox<>();
		this.chartEditor = new VBox();
		
		this.getChildren().addAll(chartTypeSelector, chartEditor);
		
		
	}
	
	public void setChartTypeSelector(ObservableList<ChartType> items) {
		this.chartTypeSelector.getItems().setAll(items);
	}

	public VBox getChartEditor() {
		return chartEditor;
	}
	
	public ChoiceBox<ChartType> getChartTypeSelector() {
		return chartTypeSelector;
	}
	
	

}
