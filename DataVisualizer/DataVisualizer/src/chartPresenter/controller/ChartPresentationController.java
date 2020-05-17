package chartPresenter.controller;

import java.io.IOException;

import org.json.JSONException;

import chartEditor.model.ChartConfigurations;
import chartEditor.view.ChartDisplayPane;
import chartPresenter.view.ChartPresenterRootPane;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ChartPresentationController {

	private ChartPresenterRootPane view;

	public ChartPresentationController(ChartPresenterRootPane view) {
		this.view = view;
		
		this.view.getLoadChartViewButton().setOnMouseClicked(new AddNewChartToView());
		
	}
	
	private class AddNewChartToView implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			ChartConfigurations c = new ChartConfigurations(new Object[0][0]);
			ChartDisplayPane display = new ChartDisplayPane();
			Button b = new Button("-");
			
			b.setOnMouseClicked(e ->{
				view.getChartsInView().getChildren().remove(b.getParent());				
			});
			VBox displayHolder = new VBox();
			displayHolder.getChildren().add(b);

			try {
				c.loadChart();
				switch (c.getBasicConfiguration().getChartType()) {
				case BARCHART:
					
					display = new ChartDisplayPane();
					display.setChartAsBarChart(c);
					display.setBarChartColorConfiguration(c);
					if (c.getSortingConfiguration().isLive()) {
						display.setBarChartSorting(c.getSortingConfiguration());
					}
					
					display.setBarChartAxisConfiguration(c.getAxisConfigurationY());
					
					displayHolder.getChildren().add(display);
					view.getChartsInView().getChildren().add(displayHolder);
					break;
				case PIECHART:
					display = new ChartDisplayPane();
					display.setChartAsPieChart(c);
					display.setPieChartColors(c.getColorConfiguration());
					if (c.getSortingConfiguration().isLive()) {
						display.setPieChartSorting(c.getSortingConfiguration());
					}
					
					displayHolder.getChildren().add(display);
					view.getChartsInView().getChildren().add(displayHolder);
					break;
				case SCATTERCHART:
					display = new ChartDisplayPane();
					display.setChartAsScatterChart(c);
					display.setScatterChartColor(c.getColorConfiguration());
					display.setScatterChartAxisCongiguratopn(c.getAxisConfigurationX(), c.getAxisConfigurationY());
					
					displayHolder.getChildren().add(display);
					view.getChartsInView().getChildren().add(displayHolder);

					break;
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}
		
	}
}
