package chartEditor.controller;

import java.io.IOException;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONException;



import chartEditor.model.ChartConfigurations;
import chartEditor.model.ChartType;
import chartEditor.model.SeriesConfigurations;
import chartEditor.view.BarChartConfigurationPane;
import chartEditor.view.ChartConfigurePane;
import chartEditor.view.ChartDisplayPane;
import chartEditor.view.ChartEditorRootPane;
import chartEditor.view.PieChartConfigurationPane;
import chartEditor.view.ScatterChartConfigurationPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Alert;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;


public class ChartEditorController {

	@SuppressWarnings("unused")
	private ChartEditorRootPane view;
	private ChartDisplayPane chartPreviewView;
	private ChartConfigurePane chartConfigurationView;
	private ChartConfigurations model; 
	private PieChartConfigurationPane pieChartConfigurationView;
	private BarChartConfigurationPane barChartConfigurationView;
	private ScatterChartConfigurationPane scatterChartConfigurationView;
	
	public ChartEditorController(ChartEditorRootPane view, ChartConfigurations chartConfigurations) {
		this.view = view;
		this.chartPreviewView = view.getChartPreviewView();
		this.chartConfigurationView = view.getChartConfigurationView();
		this.pieChartConfigurationView = new PieChartConfigurationPane();
		this.barChartConfigurationView = new BarChartConfigurationPane();
		this.scatterChartConfigurationView = new ScatterChartConfigurationPane();
		this.model = chartConfigurations;

		chartConfigurationView.getChartTypeSelector().getSelectionModel().selectedItemProperty().addListener(new ChartTypeSwapListener());
		chartConfigurationView.getChartTypeSelector().getItems().setAll(ChartType.values());
		
		pieChartConfigurationView.getDataPane().populateChoiceBoxes(model.getBasicConfiguration().getHeaders());
		pieChartConfigurationView.getDataPane().getxAxisChoice().getSelectionModel().selectedIndexProperty().addListener(new CatagoryChangedListener());
		pieChartConfigurationView.getGenerateChartButton().setOnMouseClicked(new PieChartGenerationEvent());

		barChartConfigurationView.getSeriesConfigurationPane().getAddSeriesButton().setOnMouseClicked(new AddSeriesBarChartEvent());
		barChartConfigurationView.getGenerateChartButton().setOnMouseClicked(new BarChartGenerationEvent());
		barChartConfigurationView.getSeriesConfigurationPane().getRemoveSeriesButton().setOnMouseClicked(new RemoveSeriesFromBarChartEvent());
	
		scatterChartConfigurationView.getSeriesConfigurationPane().getAddSeriesButton().setOnMouseClicked(new AddSeriesScatterChartEvent());
		scatterChartConfigurationView.getSeriesConfigurationPane().getRemoveSeriesButton().setOnMouseClicked(new RemoveSeriesFromScatterChartEvent());
		scatterChartConfigurationView.getGenerateChartButton().setOnMouseClicked(new ScatterChartGenerationEvent());

		
		view.getSaveButton().setOnAction(new SaveChartEvent());
		view.getLoadButton().setOnAction(new LoadChartEvent());
	}
	
	private class LoadChartEvent implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			try {
				model.loadChart();
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setContentText("File format appears to not exist");
				alert.showAndWait();
			}catch (JSONException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("File Loading Error");
				alert.setContentText("File format appears to be invalid");
				alert.showAndWait();
			}
			finally {
				chartConfigurationView.getChartTypeSelector().setValue(model.getBasicConfiguration().getChartType());
				switch (model.getBasicConfiguration().getChartType()) {
				case BARCHART:
					
					barChartConfigurationView.setBarConfigurations(model);
					generateBarChart();
					
					break;
				case PIECHART:
					pieChartConfigurationView.setPieConfigurations(model);
					generatePieChart();
					break;
				case SCATTERCHART:
					scatterChartConfigurationView.setScatterConfigurations(model);
					generateScatterChart();
					break;
				}
			}
			
		}
		
	}

	private class PieChartGenerationEvent implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent arg0) {
			generatePieChart();
			
		}
		
	}
	
	private void generateBarChart() {
		model.getBasicConfiguration().setChartType(ChartType.BARCHART);
		model.getBasicConfiguration().setChartTitle(barChartConfigurationView.getBasicConfigurations().getTitleField().getText());
		
		List<SeriesConfigurations> l = barChartConfigurationView.getSeriesConfigurationPane().getSeries().stream().map(x ->{
			SeriesConfigurations s = new SeriesConfigurations();
			s.setSeriesTitle(x.getSeriesTitleField().getText());
			s.setxAxisIndex(x.getxAxisChoice().getSelectionModel().getSelectedIndex());
			s.setyAxisIndex(x.getyAxisChoice().getSelectionModel().getSelectedIndex());
			return s;

		}).collect(Collectors.toList());
		model.getSeriesConfigurationsArray().clear();
		model.getSeriesConfigurationsArray().addAll(l);
		
		model.getColorConfiguration().getColorChoices().clear();
		barChartConfigurationView.getColorConfigurationPane().getColors().stream().map(x -> String.valueOf(x.getValue())).forEach(x->{
			model.getColorConfiguration().getColorChoices().add("#" + x.substring(2));
		});
		
		model.getSortingConfiguration().setSortingMode(barChartConfigurationView.getSortingConfigurationPane().getDefaultSortMode().getValue());
		model.getSortingConfiguration().setSortByFrequencyAccending(barChartConfigurationView.getSortingConfigurationPane().getAllowSortByAccendingFrequency().isSelected());
		model.getSortingConfiguration().setSortByFrequencyDecending(barChartConfigurationView.getSortingConfigurationPane().getAllowSortByDescendingFrequency().isSelected());
		model.getSortingConfiguration().setSortByNameAccending(barChartConfigurationView.getSortingConfigurationPane().getAllowSortByCatagoryNameAccending().isSelected());
		model.getSortingConfiguration().setSortByNameDecending(barChartConfigurationView.getSortingConfigurationPane().getAllowSortByCatagoryNameDecending().isSelected());
		
		
		
		if (barChartConfigurationView.getAxisConfigurationPane().getLowerbound().getText().length()==0 ||
				barChartConfigurationView.getAxisConfigurationPane().getUpperbound().getText().length() == 0) {
			model.getAxisConfigurationY().setDefaultRange(true);
		}else {
			model.getAxisConfigurationY().setDefaultRange(false);
			model.getAxisConfigurationY().setLowerbound(Double.valueOf(barChartConfigurationView.getAxisConfigurationPane().getLowerbound().getText()));
			model.getAxisConfigurationY().setUpperbound(Double.valueOf(barChartConfigurationView.getAxisConfigurationPane().getUpperbound().getText()));
		}
		
		model.getAxisConfigurationY().setPannable(barChartConfigurationView.getAxisConfigurationPane().getPannable().isSelected());
		model.getAxisConfigurationY().setScalable(barChartConfigurationView.getAxisConfigurationPane().getScalable().isSelected());
		
		if (barChartConfigurationView.getAxisConfigurationPane().getMinscalefactor().getText().length()==0 ||
				barChartConfigurationView.getAxisConfigurationPane().getMaxscalefactor().getText().length() == 0) {
			model.getAxisConfigurationY().setDefaultScale(true);
			model.getAxisConfigurationY().setMinscalefactor(0.9);
			model.getAxisConfigurationY().setMaxscalefactor(1.1);
		}else {
			model.getAxisConfigurationY().setMinscalefactor(Double.valueOf(barChartConfigurationView.getAxisConfigurationPane().getMinscalefactor().getText()));
			model.getAxisConfigurationY().setMaxscalefactor(Double.valueOf(barChartConfigurationView.getAxisConfigurationPane().getMaxscalefactor().getText()));
		}	

		chartPreviewView.setChartAsBarChart(model);
		
		if (model.getSortingConfiguration().isLive()) {
			chartPreviewView.setBarChartSorting(model.getSortingConfiguration());
		}
		chartPreviewView.setBarChartColorConfiguration(model);
		
		chartPreviewView.setBarChartAxisConfiguration(model.getAxisConfigurationY());
	}
	
	private void generateScatterChart() {
		model.getBasicConfiguration().setChartType(ChartType.SCATTERCHART);
		model.getBasicConfiguration().setChartTitle(scatterChartConfigurationView.getBasicConfigurations().getTitleField().getText());
		
		List<SeriesConfigurations> lol = scatterChartConfigurationView.getSeriesConfigurationPane().getSeries().stream().map(x ->{
			SeriesConfigurations s = new SeriesConfigurations();
			s.setSeriesTitle(x.getSeriesTitleField().getText());
			s.setxAxisIndex(x.getxAxisChoice().getSelectionModel().getSelectedIndex());
			s.setyAxisIndex(x.getyAxisChoice().getSelectionModel().getSelectedIndex());
			return s;

		}).collect(Collectors.toList());
		model.getSeriesConfigurationsArray().clear();
		model.getSeriesConfigurationsArray().addAll(lol);

		/*
		 * 
		 */

		if (scatterChartConfigurationView.getAxisConfigurationPaneX().getLowerbound().getText().length()==0 ||
				scatterChartConfigurationView.getAxisConfigurationPaneX().getUpperbound().getText().length() == 0) {
			model.getAxisConfigurationX().setDefaultRange(true);
		}else {
			model.getAxisConfigurationX().setDefaultRange(false);
			model.getAxisConfigurationX().setLowerbound(Double.valueOf(scatterChartConfigurationView.getAxisConfigurationPaneX().getLowerbound().getText()));
			model.getAxisConfigurationX().setUpperbound(Double.valueOf(scatterChartConfigurationView.getAxisConfigurationPaneX().getUpperbound().getText()));
		}
		
		model.getAxisConfigurationX().setPannable(scatterChartConfigurationView.getAxisConfigurationPaneX().getPannable().isSelected());
		model.getAxisConfigurationX().setScalable(scatterChartConfigurationView.getAxisConfigurationPaneX().getScalable().isSelected());
		
		if (scatterChartConfigurationView.getAxisConfigurationPaneX().getMinscalefactor().getText().length()==0 ||
				scatterChartConfigurationView.getAxisConfigurationPaneX().getMaxscalefactor().getText().length() == 0) {
			model.getAxisConfigurationX().setDefaultScale(true);
			model.getAxisConfigurationX().setMinscalefactor(0.9);
			model.getAxisConfigurationX().setMaxscalefactor(1.1);
		}else {
			model.getAxisConfigurationX().setMinscalefactor(Double.valueOf(scatterChartConfigurationView.getAxisConfigurationPaneX().getMinscalefactor().getText()));
			model.getAxisConfigurationX().setMaxscalefactor(Double.valueOf(scatterChartConfigurationView.getAxisConfigurationPaneX().getMaxscalefactor().getText()));
		}	
		/*
		 */
		if (scatterChartConfigurationView.getAxisConfigurationPaneY().getLowerbound().getText().length()==0 ||
				scatterChartConfigurationView.getAxisConfigurationPaneY().getUpperbound().getText().length() == 0) {
			model.getAxisConfigurationY().setDefaultRange(true);
		}else {
			model.getAxisConfigurationY().setDefaultRange(false);
			model.getAxisConfigurationY().setLowerbound(Double.valueOf(scatterChartConfigurationView.getAxisConfigurationPaneY().getLowerbound().getText()));
			model.getAxisConfigurationY().setUpperbound(Double.valueOf(scatterChartConfigurationView.getAxisConfigurationPaneY().getUpperbound().getText()));
		}
		
		model.getAxisConfigurationY().setPannable(scatterChartConfigurationView.getAxisConfigurationPaneY().getPannable().isSelected());
		model.getAxisConfigurationY().setScalable(scatterChartConfigurationView.getAxisConfigurationPaneY().getScalable().isSelected());
		
		if (scatterChartConfigurationView.getAxisConfigurationPaneY().getMinscalefactor().getText().length()==0 ||
				scatterChartConfigurationView.getAxisConfigurationPaneY().getMaxscalefactor().getText().length() == 0) {
			model.getAxisConfigurationY().setDefaultScale(true);
			model.getAxisConfigurationY().setMinscalefactor(0.9);
			model.getAxisConfigurationY().setMaxscalefactor(1.1);
		}else {
			model.getAxisConfigurationY().setMinscalefactor(Double.valueOf(scatterChartConfigurationView.getAxisConfigurationPaneY().getMinscalefactor().getText()));
			model.getAxisConfigurationY().setMaxscalefactor(Double.valueOf(scatterChartConfigurationView.getAxisConfigurationPaneY().getMaxscalefactor().getText()));
		}	
		/*
		 * 
		 * 
		 * 
		 * 
		 * */
		model.getColorConfiguration().getColorChoices().clear();
		scatterChartConfigurationView.getColorConfigurationPane().getColors().stream().map(x -> String.valueOf(x.getValue())).forEach(x->{
			model.getColorConfiguration().getColorChoices().add("#" + x.substring(2));
		});
		
		chartPreviewView.setChartAsScatterChart(model);
		chartPreviewView.setScatterChartColor(model.getColorConfiguration());
		chartPreviewView.setScatterChartAxisCongiguratopn(model.getAxisConfigurationX(), model.getAxisConfigurationY());
	}
	
	private void generatePieChart() {
		model.getBasicConfiguration().setChartType(ChartType.PIECHART);
		model.getBasicConfiguration().setChartTitle(pieChartConfigurationView.getBasicConfigurations().getTitleField().getText());
		
		SeriesConfigurations s = new SeriesConfigurations();
		s.setxAxisIndex(pieChartConfigurationView.getDataPane().getxAxisChoice().getSelectionModel().getSelectedIndex());
		s.setyAxisIndex(pieChartConfigurationView.getDataPane().getyAxisChoice().getSelectionModel().getSelectedIndex());
		
		model.getSeriesConfigurationsArray().clear();
		model.getSeriesConfigurationsArray().add(s);		
		
		model.getSortingConfiguration().setSortingMode(pieChartConfigurationView.getSortConfigurations().getDefaultSortMode().getValue());
		model.getSortingConfiguration().setSortByFrequencyAccending(pieChartConfigurationView.getSortConfigurations().getAllowSortByAccendingFrequency().isSelected());
		model.getSortingConfiguration().setSortByFrequencyDecending(pieChartConfigurationView.getSortConfigurations().getAllowSortByDescendingFrequency().isSelected());
		model.getSortingConfiguration().setSortByNameAccending(pieChartConfigurationView.getSortConfigurations().getAllowSortByCatagoryNameAccending().isSelected());
		model.getSortingConfiguration().setSortByNameDecending(pieChartConfigurationView.getSortConfigurations().getAllowSortByCatagoryNameDecending().isSelected());
		
		model.getColorConfiguration().getColorChoices().clear();
		pieChartConfigurationView.getColorConfigurations().getColors().stream().map(x -> String.valueOf(x.getValue())).forEach(x->{
			model.getColorConfiguration().getColorChoices().add("#" + x.substring(2));
		});

		chartPreviewView.setChartAsPieChart(model);
		chartPreviewView.setPieChartColors(model.getColorConfiguration());
		if (model.getSortingConfiguration().isLive()) {
			chartPreviewView.setPieChartSorting(model.getSortingConfiguration());
		}
	}
	private class ScatterChartGenerationEvent implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event) {
			generateScatterChart();
		}
		
	}
	private class SaveChartEvent implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			try {
				model.saveChart();
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.showAndWait();
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.showAndWait();
			}
			
		}
	}
	private class BarChartGenerationEvent implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event) {
			generateBarChart();
		}
		
	}
	
	private class ChartTypeSwapListener implements ChangeListener<ChartType>{
		@Override
		public void changed(ObservableValue<? extends ChartType> observable, ChartType oldValue, ChartType newValue) {	
			switch (newValue) {
			case PIECHART:
				chartConfigurationView.getChartEditor().getChildren().setAll(pieChartConfigurationView);
				break;
			case BARCHART:
				chartConfigurationView.getChartEditor().getChildren().setAll(barChartConfigurationView);
				break;
			case SCATTERCHART:
				chartConfigurationView.getChartEditor().getChildren().setAll(scatterChartConfigurationView);
				break;
				

			}
	
		}

	}
	
	public class AddSeriesScatterChartEvent implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event) {
			System.out.println("attempted to add series to scatter chart");
			scatterChartConfigurationView.getSeriesConfigurationPane().addSeries(model.getBasicConfiguration().getHeaders(), ChartType.SCATTERCHART);
			scatterChartConfigurationView.getColorConfigurationPane().addCatagory("seriescolor");
		}
		
	}
	
	private class RemoveSeriesFromScatterChartEvent implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent arg0) {
			scatterChartConfigurationView.getSeriesConfigurationPane().removeSeries();
			scatterChartConfigurationView.getColorConfigurationPane().removeCategory();
			
		}
		
	}
	
	public class AddSeriesBarChartEvent implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent event) {
			barChartConfigurationView.getSeriesConfigurationPane().addSeries(model.getBasicConfiguration().getHeaders(), ChartType.BARCHART);
			barChartConfigurationView.getColorConfigurationPane().addCatagory("seriescolor");
		}
	}
	
	
	
	private class RemoveSeriesFromBarChartEvent implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent arg0) {
			barChartConfigurationView.getSeriesConfigurationPane().removeSeries();
			barChartConfigurationView.getColorConfigurationPane().removeCategory();
			
		}
		
	}
	
	public class CatagoryChangedListener implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			if (pieChartConfigurationView.getDataPane().getxAxisChoice().getSelectionModel().getSelectedIndex() != -1) {
				pieChartConfigurationView.getColorConfigurations().setColorCatagories(
					model.getBasicConfiguration().getCatagoryAt(pieChartConfigurationView.getDataPane().getxAxisChoice().getSelectionModel().getSelectedIndex()));
			
			}
			
		}

	}
	
}
