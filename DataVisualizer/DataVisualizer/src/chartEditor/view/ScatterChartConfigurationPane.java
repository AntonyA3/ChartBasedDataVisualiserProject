package chartEditor.view;





import chartEditor.model.ChartConfigurations;
import chartEditor.model.ChartType;

import javafx.scene.control.Button;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ScatterChartConfigurationPane extends VBox{

	private BasicConfigurationPane basicConfigurations;
	private SeriesConfigurationPane seriesConfigurationPane;
	private ColorConfigurationPane colorConfigurationPane;
	private Button generateChartButton;
	private AxisConfigurationPane axisConfigurationPaneX;
	private AxisConfigurationPane axisConfigurationPaneY;
	
	public ScatterChartConfigurationPane() {
		
		this.basicConfigurations = new BasicConfigurationPane();
		this.seriesConfigurationPane = new SeriesConfigurationPane();
		this.colorConfigurationPane = new ColorConfigurationPane();
		this.axisConfigurationPaneX = new AxisConfigurationPane();
		this.axisConfigurationPaneY = new AxisConfigurationPane();
		this.generateChartButton = new Button("generate");
		this.generateChartButton.disableProperty().bind(this.seriesConfigurationPane.getSeriesExists().not());
		this.getChildren().addAll(basicConfigurations, seriesConfigurationPane, colorConfigurationPane,axisConfigurationPaneX, axisConfigurationPaneY, generateChartButton);
		
	}
	
	public BasicConfigurationPane getBasicConfigurations() {
		return basicConfigurations;
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

	public void setScatterConfigurations(ChartConfigurations model) {
		this.basicConfigurations.getTitleField().setText(model.getBasicConfiguration().getChartTitle());
		this.seriesConfigurationPane.clear();
		model.getSeriesConfigurationsArray().forEach(x ->{
			DataConfigurationPane dp = new DataConfigurationPane();
			dp.setNameConvention(ChartType.SCATTERCHART);
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

		
	}
	
	public AxisConfigurationPane getAxisConfigurationPaneX() {
		return axisConfigurationPaneX;
	}
	
	public AxisConfigurationPane getAxisConfigurationPaneY() {
		return axisConfigurationPaneY;
	}
	
	

}
