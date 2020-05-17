package chartEditor.view;

import chartEditor.model.SortMode;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SortingConfigurationPane extends VBox {
	
	private ChoiceBox<SortMode> defaultSortMode;
	private CheckBox allowSortByAccendingFrequency;
	private CheckBox allowSortByDescendingFrequency;
	private CheckBox allowSortByCatagoryNameAccending;
	private CheckBox allowSortByCatagoryNameDecending;
	public SortingConfigurationPane() {
		defaultSortMode = new ChoiceBox<>();
		this.defaultSortMode.getItems().setAll((SortMode.values()));
		this.allowSortByAccendingFrequency = new CheckBox("Sort By Frequency Min to Max");
		this.allowSortByDescendingFrequency = new CheckBox("Sort By Frequency Max to Min");
		this.allowSortByCatagoryNameAccending = new CheckBox("Sort By Catagory Name A-Z");
		this.allowSortByCatagoryNameDecending = new CheckBox("Sort By Catagory Name Z-A");

		this.getChildren().addAll(new Label("sort configurations"),defaultSortMode, allowSortByAccendingFrequency, allowSortByDescendingFrequency, 
				allowSortByCatagoryNameAccending, allowSortByCatagoryNameDecending);
		
	}
	
	public ChoiceBox<SortMode> getDefaultSortMode() {
		return defaultSortMode;
	}
	
	public CheckBox getAllowSortByAccendingFrequency() {
		return allowSortByAccendingFrequency;
	}
	
	public CheckBox getAllowSortByDescendingFrequency() {
		return allowSortByDescendingFrequency;
	}

	public CheckBox getAllowSortByCatagoryNameAccending() {
		return allowSortByCatagoryNameAccending;
	}
	
	public CheckBox getAllowSortByCatagoryNameDecending() {
		return allowSortByCatagoryNameDecending;
	}
}
