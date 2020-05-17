package chartEditor.view;


import chartEditor.model.ChartConfigurations;
import chartEditor.model.ChartType;

import javafx.scene.control.Button;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class BarChartConfigurationPane extends VBox {


	private BasicConfigurationPane basicConfigurations;
	private SeriesConfigurationPane seriesConfigurationPane;
	private ColorConfigurationPane colorConfigurationPane;
	private SortingConfigurationPane sortingConfigurationPane;
	private AxisConfigurationPane axisConfigurationPane;
	private Button generateChartButton;

	public BarChartConfigurationPane() {
		this.basicConfigurations = new BasicConfigurationPane();
		this.seriesConfigurationPane = new SeriesConfigurationPane();
		this.colorConfigurationPane = new ColorConfigurationPane();
		this.sortingConfigurationPane = new SortingConfigurationPane();
		this.axisConfigurationPane = new AxisConfigurationPane();
		this.generateChartButton = new Button("generate");
		
		this.generateChartButton.disableProperty().bind((this.seriesConfigurationPane.getSeriesExists()).not());
		this.getChildren().addAll(basicConfigurations, seriesConfigurationPane, colorConfigurationPane, sortingConfigurationPane, axisConfigurationPane, generateChartButton);
		
	}
	
	public BasicConfigurationPane getBasicConfigurations() {
		return basicConfigurations;
	}
	
	public SortingConfigurationPane getSortingConfigurationPane() {
		return sortingConfigurationPane;
	}
	
	public ColorConfigurationPane getColorConfigurationPane() {
		return colorConfigurationPane;
	}
	
	public SeriesConfigurationPane getSeriesConfigurationPane() {
		return seriesConfigurationPane;
	}
	
	public Button getGenerateChartButton() {
		return generateChartButton;
	}


	public void setBarConfigurations(ChartConfigurations model) {
		this.basicConfigurations.getTitleField().setText(model.getBasicConfiguration().getChartTitle());
		this.seriesConfigurationPane.clear();
		model.getSeriesConfigurationsArray().forEach(x ->{
			DataConfigurationPane dp = new DataConfigurationPane();
			dp.setNameConvention(ChartType.BARCHART);
			dp.getSeriesTitleField().setText(x.getSeriesTitle());
			dp.populateChoiceBoxes(model.getBasicConfiguration().getHeaders());
			dp.getxAxisChoice().getSelectionModel().select(x.getxAxisIndex());
			dp.getyAxisChoice().getSelectionModel().select(x.getyAxisIndex());
			seriesConfigurationPane.addSeries(dp);
			this.colorConfigurationPane.addCatagory("seriescolor");

		});
		
		for (int i = 0; i < this.colorConfigurationPane.getColors().size(); i++) {
			this.colorConfigurationPane.getColors().get(i).setValue(Color.valueOf(model.getColorConfiguration().getColorAt(i)));
		}
		
		this.sortingConfigurationPane.getDefaultSortMode().setValue(model.getSortingConfiguration().getSortingMode());
		this.sortingConfigurationPane.getAllowSortByAccendingFrequency().setSelected(model.getSortingConfiguration().isSortByFrequencyAccending());
		this.sortingConfigurationPane.getAllowSortByDescendingFrequency().setSelected(model.getSortingConfiguration().isSortByFrequencyDecending());
		this.sortingConfigurationPane.getAllowSortByCatagoryNameAccending().setSelected(model.getSortingConfiguration().isSortByNameAccending());
		this.sortingConfigurationPane.getAllowSortByCatagoryNameDecending().setSelected(model.getSortingConfiguration().isSortByNameDecending());
		
	}

	public AxisConfigurationPane getAxisConfigurationPane() {
		return axisConfigurationPane;
	}

}
