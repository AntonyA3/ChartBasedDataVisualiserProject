package chartEditor.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;

import javafx.scene.layout.VBox;

public class ChartEditorRootPane extends VBox{
	 
	private SplitPane splitPane;
	private ChartConfigurePane chartConfigurationView;
	private ChartDisplayPane chartPreviewView;
	private MenuItem saveButton;
	private MenuItem loadButton;
	public ChartEditorRootPane() {
		this.setMinSize(680,480);		
		this.chartConfigurationView = new ChartConfigurePane();
		ScrollPane sc = new ScrollPane();
		sc.setContent(this.chartConfigurationView);
		this.chartPreviewView = new ChartDisplayPane();
		this.splitPane = new SplitPane();
		this.splitPane.getItems().setAll(sc, chartPreviewView);
	
		MenuBar topMenu = new MenuBar();
		Menu fileMenu = new Menu("file");
		topMenu.getMenus().add(fileMenu);
		this.saveButton = new MenuItem("save");
		fileMenu.getItems().add(saveButton);
		this.loadButton = new MenuItem("load");
		fileMenu.getItems().add(loadButton);
		this.getChildren().addAll(topMenu, this.splitPane);
	}
	
	public ChartConfigurePane getChartConfigurationView() {
		return chartConfigurationView;
	}
	
	public ChartDisplayPane getChartPreviewView() {
		return chartPreviewView;
	}
	
	public MenuItem getSaveButton() {
		return saveButton;
	}
	
	public MenuItem getLoadButton() {
		return loadButton;
	}

}
