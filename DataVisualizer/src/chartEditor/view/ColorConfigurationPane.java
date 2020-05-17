package chartEditor.view;

import java.util.List;
import java.util.stream.Collectors;

import chartEditor.model.ColorConfiguration;
import javafx.collections.ObservableList;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ColorConfigurationPane extends VBox{
	private GridPane g;
	private int categoryPointer;
	public ColorConfigurationPane() {
		this.g = new GridPane();
		this.categoryPointer = 0;
		this.getChildren().addAll(new Label("Color Configurations"), this.g);
	}
	
	public void addCatagory(String category) {
		g.add(new Label(category), 0, categoryPointer);
		g.add(new ColorPicker(Color.valueOf(ColorConfiguration.getColorFor(categoryPointer))), 1, categoryPointer);
		categoryPointer++;
	}
	public void clear() {
		g.getChildren().clear();
		
	}
	@SuppressWarnings("static-access")
	public void removeCategory() {
		if (categoryPointer >0) {
			categoryPointer--;
			g.getChildren().removeIf(x ->g.getRowIndex(x) == categoryPointer);
		}
		
		
		
	}
	public void setColorCatagories(ObservableList<String> catagories) {
		g.getChildren().clear();
		catagories.stream().forEach(x -> addCatagory(x));

		
	}
	
	@SuppressWarnings("static-access")
	public List<ColorPicker> getColors() {
		return this.g.getChildren().stream().filter(x -> this.g.getColumnIndex(x) ==1).map(x ->(ColorPicker) x).collect(Collectors.toList());
	}
	

}
