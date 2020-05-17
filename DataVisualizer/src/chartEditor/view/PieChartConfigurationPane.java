package chartEditor.view;



import chartEditor.model.ChartConfigurations;
import chartEditor.model.ChartType;

import javafx.scene.control.Button;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PieChartConfigurationPane extends VBox {
	private DataConfigurationPane dataPane;
	private Button generateChartButton;
	private BasicConfigurationPane basicConfigurations;
	private ColorConfigurationPane colorConfigurations;
	private SortingConfigurationPane sortConfigurations;
	public PieChartConfigurationPane() {
		this.basicConfigurations = new BasicConfigurationPane();
		this.dataPane = new DataConfigurationPane();
		this.dataPane.setNameConvention(ChartType.PIECHART);
		this.colorConfigurations = new ColorConfigurationPane();
		this.sortConfigurations = new SortingConfigurationPane();
		this.generateChartButton = new Button("Generate");
		this.getChildren().addAll(basicConfigurations, dataPane, colorConfigurations, sortConfigurations, generateChartButton);
	}
	

	
	public Button getGenerateChartButton() {
		return generateChartButton;
	}
	
	public BasicConfigurationPane getBasicConfigurations() {
		return basicConfigurations;
	}
	
	public DataConfigurationPane getDataPane() {
		return dataPane;
	}
	public ColorConfigurationPane getColorConfigurations() {
		return colorConfigurations;
	}
	
	public SortingConfigurationPane getSortConfigurations() {
		return sortConfigurations;
	}



	public void setPieConfigurations(ChartConfigurations model) {
		this.basicConfigurations.getTitleField().setText(model.getBasicConfiguration().getChartTitle());
		this.dataPane.populateChoiceBoxes(model.getBasicConfiguration().getHeaders());
		this.dataPane.getxAxisChoice().getSelectionModel().select((model.getSeriesConfigurationsArray().get(0).getxAxisIndex()));
		this.dataPane.getyAxisChoice().getSelectionModel().select((model.getSeriesConfigurationsArray().get(0).getyAxisIndex()));

		for (int i = 0; i < this.colorConfigurations.getColors().size(); i++) {
			this.colorConfigurations.getColors().get(i).setValue(Color.valueOf(model.getColorConfiguration().getColorAt(i)));
		}
		
		this.sortConfigurations.getDefaultSortMode().setValue(model.getSortingConfiguration().getSortingMode());
		this.sortConfigurations.getAllowSortByAccendingFrequency().setSelected(model.getSortingConfiguration().isSortByFrequencyAccending());
		this.sortConfigurations.getAllowSortByDescendingFrequency().setSelected(model.getSortingConfiguration().isSortByFrequencyDecending());
		this.sortConfigurations.getAllowSortByCatagoryNameAccending().setSelected(model.getSortingConfiguration().isSortByNameAccending());
		this.sortConfigurations.getAllowSortByCatagoryNameDecending().setSelected(model.getSortingConfiguration().isSortByNameDecending());
	}
	
	
}
