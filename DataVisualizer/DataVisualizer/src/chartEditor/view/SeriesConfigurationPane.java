package chartEditor.view;

import java.util.List;
import java.util.stream.Collectors;

import chartEditor.model.ChartType;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import javafx.scene.layout.VBox;

public class SeriesConfigurationPane extends VBox {
	private VBox dataPanes;
	private int seriesCount = 0;
	private Button addSeriesButton;
	private Button removeSeriesButton;
	private BooleanProperty seriesExists;
	private BooleanProperty allDataSelected;
	public SeriesConfigurationPane() {
		this.dataPanes = new VBox();
		this.addSeriesButton = new Button("add series");
		this.removeSeriesButton = new Button("remove series");
		this.seriesExists = new SimpleBooleanProperty(false);
		//this.allDataSelectedProperty = new SimpleBooleanProperty(false);
		this.getChildren().addAll(this.addSeriesButton, this.removeSeriesButton, this.dataPanes);
	
	}
	
	public void clear() {
		this.dataPanes.getChildren().clear();
		this.seriesCount = 0;
		this.seriesExists.setValue(false);
	}
	
	public void addSeries(ObservableList<String> list, ChartType c) {
		DataConfigurationPane dcp = new DataConfigurationPane();
		dcp.setNameConvention(c);
		dcp.populateChoiceBoxes(list);
		dcp.getxAxisChoice().getSelectionModel().select(0);
		dcp.getyAxisChoice().getSelectionModel().select(0);

		dataPanes.getChildren().add(dcp);
		seriesCount++;
		this.seriesExists.setValue(true);
	}
	
	public Button getAddSeriesButton() {
		return addSeriesButton;
	}
	
	public Button getRemoveSeriesButton() {
		return removeSeriesButton;
	}
	public void removeSeries() {
		if (seriesCount >0) {
			this.dataPanes.getChildren().remove(this.dataPanes.getChildren().size()-1);
			seriesCount--;
		}
		
		if (seriesCount ==0) {
			this.seriesExists.setValue(false);
		}
	}
	
	public List<DataConfigurationPane> getSeries() {
		return this.dataPanes.getChildren().stream().map(x -> (DataConfigurationPane) x).collect(Collectors.toList());
	}

	public void addSeries(DataConfigurationPane dp) {
		dataPanes.getChildren().add(dp);
		dp.getxAxisChoice().getSelectionModel().select(0);
		dp.getyAxisChoice().getSelectionModel().select(0);

		this.allDataSelected.setValue(false);
		seriesCount++;
		this.seriesExists.setValue(true);
	}

	public int getSeriesCount() {
		return seriesCount;
	}

	public BooleanProperty getSeriesExists() {
		return seriesExists;
	}
	
	public BooleanProperty getAllDataSelected() {
		return allDataSelected;
	}
	
}
