package chartPresenter.view;

import chartEditor.view.ChartDisplayPane;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;


public class ChartPresenterRootPane extends BorderPane {
		

	
	private FlowPane chartsInView;
	private Button loadChartViewButton;
	public ChartPresenterRootPane() {
		this.setMinSize(680,480);	
		this.loadChartViewButton = new Button("+");
		this.chartsInView = new FlowPane();
		
		ScrollPane v = new ScrollPane();
		v.setContent(chartsInView);
		this.setCenter(v);
		this.setTop(this.loadChartViewButton);
	
	}
	
	public void addChartView(ChartDisplayPane c) {
		this.chartsInView.getChildren().add(c);
	}
	public Button getLoadChartViewButton() {
		return loadChartViewButton;
	}
	public FlowPane getChartsInView() {
		return chartsInView;
	}
	
	

}
