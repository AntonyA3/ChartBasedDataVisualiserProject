package chartEditor.view;


import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

import chartEditor.model.AxisConfigurations;
import chartEditor.model.ChartConfigurations;
import chartEditor.model.ColorConfiguration;
import chartEditor.model.SortMode;
import chartEditor.model.SortingConfiguration;
import javafx.collections.ObservableList;

import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ChartDisplayPane extends HBox {
	
	private Chart chart;
	private HBox chartHolder;
	private double yAxisScaleFactor;
	private double yAxisOffset;
	private double xAxisScaleFactor;
	private double xAxisOffset;
	private BorderPane chartView;
	public ChartDisplayPane() {
		
		this.yAxisScaleFactor = 1;
		this.xAxisScaleFactor = 1;
		this.yAxisOffset = 0;
		this.xAxisOffset = 0;
		this.chartHolder = new HBox();
		new HBox();
		new HBox();
		new HBox();
		this.chart = new PieChart();
		this.chartHolder.getChildren().add(this.chart);
		new HBox();
		this.chartView = new BorderPane();
		this.getChildren().setAll(this.chartView);
	}

	public void setChartAsPieChart(ChartConfigurations m) {
		this.chart = new PieChart();

		((PieChart) this.chart).setStartAngle(90);
		this.chart.setTitle(m.getBasicConfiguration().getChartTitle());

		ObservableList<String> catagory = m.getBasicConfiguration().getCatagoryAt(m.getSeriesConfigurationsArray().get(0).getxAxisIndex());
		ObservableList<Double> dataY = m.getBasicConfiguration().getFrequencyAt(m.getSeriesConfigurationsArray().get(0).getyAxisIndex());

		for (int i = 0; i < catagory.size(); i++) {
			((PieChart) this.chart).getData().add(new PieChart.Data(catagory.get(i), dataY.get(i)));
		}
		this.chartView.setCenter(this.chart);

	}
	
	

	public void setBarChartColorConfiguration(ChartConfigurations m) {
		ObservableList<String> colors = m.getColorConfiguration().getColorChoices();
		for (int i = 0; i < colors.size(); i++) {
			@SuppressWarnings("unchecked")
			Set<Node> n = ((BarChart<String, Number>)this.chart).lookupAll(".series"+ i +".chart-bar");
			for (Node node : n) {
				node.setStyle("-fx-background-color:"+ colors.get(i)+";");
			}			
		}
	}
	
	private void setAxisConfiguration(AxisConfigurations axConf, NumberAxis ax, boolean isYaxis) {
		HBox axisView = new HBox();
		if (axConf.isDefaultRange()) {
			ax.setAutoRanging(false);
			axConf.setUpperbound(ax.getUpperBound());
			axConf.setLowerbound(ax.getLowerBound());
		}else {
			ax.setAutoRanging(false);
			ax.setUpperBound(axConf.getUpperbound());
			ax.setLowerBound(axConf.getLowerbound());
		}

		if (axConf.isDefaultScale()) {
			axConf.setDefaultScaleFactor(1);

		}else {
			if (isYaxis) {
				this.yAxisScaleFactor = axConf.getDefaultScaleFactor();
			}else {
				this.xAxisScaleFactor = axConf.getDefaultScaleFactor();
			}
		}

		
		if (axConf.isPannable()) {
			
			Button negative = new Button("V");
			negative.setOnMouseClicked(e->{
				ax.setAutoRanging(false);
				if (isYaxis) {
					yAxisOffset -= Math.abs( (axConf.getUpperbound()-axConf.getLowerbound())/10) * yAxisScaleFactor;				
					double midpoint = axConf.getLowerbound() +((axConf.getUpperbound() - axConf.getLowerbound())/2);	
					ax.setLowerBound(axConf.getLowerbound() + (1-yAxisScaleFactor)*(midpoint - axConf.getLowerbound())+yAxisOffset);
					ax.setUpperBound(axConf.getUpperbound() - (1- yAxisScaleFactor)*(axConf.getUpperbound() - midpoint )+yAxisOffset);
				}else {
					xAxisOffset -= Math.abs( (axConf.getUpperbound()-axConf.getLowerbound())/10) * xAxisScaleFactor;
					double midpoint = axConf.getLowerbound() +((axConf.getUpperbound() - axConf.getLowerbound())/2);	
					ax.setLowerBound(axConf.getLowerbound() + (1-xAxisScaleFactor)*(midpoint - axConf.getLowerbound())+xAxisOffset);
					ax.setUpperBound(axConf.getUpperbound() - (1- xAxisScaleFactor)*(axConf.getUpperbound() - midpoint )+xAxisOffset);
				}	
			});
			
			Button positive = new Button("^");
			positive.setOnMouseClicked(e->{
				ax.setAutoRanging(false);
				if (isYaxis) {
					yAxisOffset += Math.abs( (axConf.getUpperbound()-axConf.getLowerbound())/10) * yAxisScaleFactor;
					double midpoint = axConf.getLowerbound() +((axConf.getUpperbound() - axConf.getLowerbound())/2);	
					ax.setLowerBound(axConf.getLowerbound() + (1-yAxisScaleFactor)*(midpoint - axConf.getLowerbound())+yAxisOffset);
					ax.setUpperBound(axConf.getUpperbound() - (1- yAxisScaleFactor)*(axConf.getUpperbound() - midpoint )+yAxisOffset );
				}else {
					xAxisOffset += Math.abs( (axConf.getUpperbound()-axConf.getLowerbound())/10) * xAxisScaleFactor;
					double midpoint = axConf.getLowerbound() +((axConf.getUpperbound() - axConf.getLowerbound())/2);	
					ax.setLowerBound(axConf.getLowerbound() + (1-xAxisScaleFactor)*(midpoint - axConf.getLowerbound())+xAxisOffset);
					ax.setUpperBound(axConf.getUpperbound() - (1- xAxisScaleFactor)*(axConf.getUpperbound() - midpoint )+xAxisOffset);
				}	
			});
			axisView.getChildren().add(negative);
			axisView.getChildren().add(positive);
			
		}
		
		if (axConf.isScalable()) {
			Slider slider = new Slider(axConf.getMinscalefactor(), axConf.getMaxscalefactor(), 1);			
			slider.valueProperty().addListener((o,l,v) ->{		
				double sc = slider.getValue();
				ax.setAutoRanging(false);
				if (isYaxis) {
					yAxisScaleFactor = sc;
					double midpoint = axConf.getLowerbound() +((axConf.getUpperbound() - axConf.getLowerbound())/2);	
					ax.setLowerBound(axConf.getLowerbound() + (1- yAxisScaleFactor)*(midpoint - axConf.getLowerbound())+yAxisOffset);
					ax.setUpperBound(axConf.getUpperbound() - (1- yAxisScaleFactor)*(axConf.getUpperbound() - midpoint )+yAxisOffset);
				}else {
					xAxisScaleFactor = sc;
					double midpoint = axConf.getLowerbound() +((axConf.getUpperbound() - axConf.getLowerbound())/2);	
					ax.setLowerBound(axConf.getLowerbound() + (1-xAxisScaleFactor)*(midpoint - axConf.getLowerbound())+xAxisOffset);
					ax.setUpperBound(axConf.getUpperbound() - (1- xAxisScaleFactor)*(axConf.getUpperbound() - midpoint )+xAxisOffset);
				}

			});
			axisView.getChildren().add(slider);
			
		}		
		if (isYaxis) {
			this.chartView.setLeft(axisView);
		}else {
			this.chartView.setBottom(axisView);
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void setBarChartAxisConfiguration(AxisConfigurations axConf) {
		this.setAxisConfiguration(axConf, (NumberAxis)((BarChart<String, Number>) this.chart).getYAxis(), true);
	}
	
	@SuppressWarnings("unchecked")
	public void setScatterChartAxisCongiguratopn(AxisConfigurations axXConf, AxisConfigurations axYConf) {
		this.setAxisConfiguration(axXConf, (NumberAxis)((ScatterChart<Number, Number>) this.chart).getXAxis(), false);
		this.setAxisConfiguration(axYConf, (NumberAxis)((ScatterChart<Number, Number>) this.chart).getYAxis(), true);		
	}
	
	
	@SuppressWarnings("unchecked")
	public void setChartAsBarChart(ChartConfigurations m) {
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();      
		
		this.chart = new BarChart<>(xAxis, yAxis);
		((BarChart<String, Number>) this.chart).setTitle(m.getBasicConfiguration().getChartTitle());
		m.getSeriesConfigurationsArray().stream().forEach(x->{
			ObservableList<String> catagory = m.getBasicConfiguration().getCatagoryAt(x.getxAxisIndex());
			ObservableList<Double> dataY = m.getBasicConfiguration().getFrequencyAt(x.getyAxisIndex());

			@SuppressWarnings("rawtypes")
			XYChart.Series series = new XYChart.Series();
			series.setName(x.getSeriesTitle());
			for (int i = 0; i < catagory.size(); i++) {
				series.getData().add(new XYChart.Data<String, Number>(catagory.get(i), dataY.get(i)));
			}


			((BarChart<String, Number>) this.chart).getData().add(series);

		});
		this.chartView.setCenter(this.chart);
		//this.chartHolder.getChildren().setAll(this.chart);

	}
	
	@SuppressWarnings("unchecked")
	public void setChartAsScatterChart(ChartConfigurations m) {
	        final NumberAxis xAxis = new NumberAxis();
	        final NumberAxis yAxis = new NumberAxis();        
	    this.chart = new ScatterChart<Number,Number>(xAxis, yAxis);

		((ScatterChart<Number, Number>) this.chart).setTitle(m.getBasicConfiguration().getChartTitle());
		m.getSeriesConfigurationsArray().stream().forEach(x ->{
			ObservableList<Double> dataX = m.getBasicConfiguration().getFrequencyAt(x.getxAxisIndex());
			ObservableList<Double> dataY = m.getBasicConfiguration().getFrequencyAt(x.getyAxisIndex());

	        @SuppressWarnings("rawtypes")
			XYChart.Series series = new XYChart.Series();
	        series.setName(x.getSeriesTitle());
			for (int i = 0; i < dataX.size(); i++) {
				series.getData().add(new XYChart.Data<Number, Number>(dataX.get(i), dataY.get(i)));
			}
			
			((ScatterChart<Number, Number>) this.chart).getData().add(series);
		});
		
	
		this.chartView.setCenter(this.chart);
		//this.chartHolder.getChildren().setAll(this.chart);

	}
	
	public void setScatterChartColorConfigurations(ChartConfigurations m) {
		ObservableList<String> colors = m.getColorConfiguration().getColorChoices();
		for (int i = 0; i < colors.size(); i++) {
			@SuppressWarnings("unchecked")
			Set<Node> n = ((ScatterChart<Number, Number>)this.chart).lookupAll(".default-color"+ i +".chart-symbol");
			for (Node node : n) {
				node.setStyle("-fx-background-color:"+ colors.get(i)+";");
			}
			
		}
 
	}
	
	public void setPieChartColors (ColorConfiguration m) {
		ObservableList<String> colors = m.getColorChoices();
		for (int i = 0; i < colors.size(); i++) {
			Node n = ((PieChart)this.chart).lookup(".data"+ i +".chart-pie");
			System.out.println(colors.get(i));
			n.setStyle("-fx-pie-color:"+ colors.get(i)+";");
		}
		
	}
	
	public void setPieChartSorting(SortingConfiguration sortingConfiguration) {
		ChoiceBox<SortMode> sortBox = new ChoiceBox<>();
		if (sortingConfiguration.isSortByFrequencyAccending()) {
			sortBox.getItems().add(SortMode.FrequencyAccending);
		}
		if (sortingConfiguration.isSortByFrequencyDecending()) {
			sortBox.getItems().add(SortMode.FrequencyDeccending);
		}
		if (sortingConfiguration.isSortByNameAccending()) {
			sortBox.getItems().add(SortMode.NameAccending);
		}
		if (sortingConfiguration.isSortByNameDecending()) {
			sortBox.getItems().add(SortMode.NameDeccending);
		}
		sortBox.getSelectionModel().selectedItemProperty().addListener((o,ol,nw)->{
			switch(nw) {
			case FrequencyAccending:
				((PieChart) this.chart).getData().sort(Comparator.comparing(PieChart.Data::getPieValue));
				break;
			case FrequencyDeccending:
				((PieChart) this.chart).getData().sort(Comparator.comparing(PieChart.Data::getPieValue).reversed());
				break;
			case NameAccending:
				((PieChart) this.chart).getData().sort(Comparator.comparing(PieChart.Data::getName));
				break;
			case NameDeccending:
				((PieChart) this.chart).getData().sort(Comparator.comparing(PieChart.Data::getName).reversed());
				break;
			}
		});
		
	this.chartView.setTop(sortBox);
	}
	public void setScatterChartColor(ColorConfiguration colorConfiguration) {
		ObservableList<String> colors = colorConfiguration.getColorChoices();
		for (int i = 0; i < colors.size(); i++) {
			
			@SuppressWarnings("unchecked")
			Set<Node> n = ((ScatterChart<Number, Number>)this.chart).lookupAll(".default-color"+ i +".chart-symbol");
			for (Node node : n) {
				node.setStyle("-fx-background-color:"+ colors.get(i)+";");
			}
			
		}
		
	}
	@SuppressWarnings("rawtypes")
	public void setBarChartSorting(SortingConfiguration sortingConfiguration) {
		ChoiceBox<SortMode> sortBox = new ChoiceBox<>();
		if (sortingConfiguration.isSortByFrequencyAccending()) {
			sortBox.getItems().add(SortMode.FrequencyAccending);
		}
		if (sortingConfiguration.isSortByFrequencyDecending()) {
			sortBox.getItems().add(SortMode.FrequencyDeccending);
		}
		if (sortingConfiguration.isSortByNameAccending()) {
			sortBox.getItems().add(SortMode.NameAccending);
		}
		if (sortingConfiguration.isSortByNameDecending()) {
			sortBox.getItems().add(SortMode.NameDeccending);
		}

		

		sortBox.getSelectionModel().selectedItemProperty().addListener((o,ol,nw)->{
			ObservableList<String> data = ((CategoryAxis)((BarChart) this.chart).getXAxis()).getCategories();
			@SuppressWarnings({ "unchecked" })
			XYChart.Series<String,Number> first = (XYChart.Series)((ObservableList<XYChart.Series>)((BarChart) this.chart).getData()).get(0);
			
			
			
			
			switch(nw) {			

			case FrequencyAccending:
				data.setAll(first.getData().stream().sorted(Comparator.comparing(XYChart.Data<String,Number>::getYValue,Comparator.comparing(Number::doubleValue)))
						.map(x -> x.getXValue()).collect(Collectors.toList()));
				break;
			case FrequencyDeccending:
				data.setAll(first.getData().stream().sorted(Comparator.comparing(XYChart.Data<String,Number>::getYValue,Comparator.comparing(Number::doubleValue).reversed()))
						.map(x -> x.getXValue()).collect(Collectors.toList()));		
				break;
			case NameAccending:
				data = data.sorted(Comparator.naturalOrder());
				break;
			case NameDeccending:
				data = data.sorted(Comparator.reverseOrder());
				break;
			}
			((CategoryAxis)((BarChart) this.chart).getXAxis()).setCategories(data);
			((CategoryAxis)((BarChart) this.chart).getXAxis()).setAutoRanging(true);

		});
		sortBox.setValue(sortingConfiguration.getSortingMode());
		
	
		
	this.chartView.setTop(sortBox);
		
	}
}
