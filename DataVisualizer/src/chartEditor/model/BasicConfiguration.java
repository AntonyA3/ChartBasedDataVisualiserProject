package chartEditor.model;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BasicConfiguration {
	
	private ChartType chartType;
	private String chartTitle;
	private Object[][] dataset;
	
	public BasicConfiguration() {
		this.chartType = ChartType.SCATTERCHART;
		this.chartTitle = "";
		this.dataset = new Object[0][0];
	}
	
	public ChartType getChartType() {
		return chartType;
	}
	
	public String getChartTitle() {
		return chartTitle;
	}
	
	public void setChartTitle(String chartTitle) {
		this.chartTitle = chartTitle;
	}
	
	public void setChartType(ChartType chartType) {
		this.chartType = chartType;
	}
	
	public ObservableList<String> getHeaders(){
		ObservableList<String> value = FXCollections.observableArrayList();
		if (dataset.length > 0) {
			for (int i = 0; i < dataset[0].length; i++) {
				value.add(String.valueOf(dataset[0][i]));	
			}		
		}


		return value;
	}

	public ObservableList<String> getCatagoryAt (int columnIndex){
		ObservableList<Object> data = getDataAt(columnIndex);
		ObservableList<String> catagories = FXCollections.observableArrayList();
		toString();
		data.stream().map(x -> String.valueOf(x)).forEach(x -> catagories.add(x));
		return catagories;
	}
	
	public ObservableList<Double> getFrequencyAt (int columnIndex){
		ObservableList<Object> data = getDataAt(columnIndex);
		ObservableList<Double> frequencies = FXCollections.observableArrayList();
		data.stream().map(x ->  String.valueOf(x)).forEach(x ->{
			Double n;
			try {
				n = Double.valueOf(x);
			}catch (NumberFormatException e) {
				n = Double.valueOf(0);
			}
			frequencies.add(n);
		});
		return frequencies;
	}
	
	private ObservableList<Object> getDataAt(int columnIndex){
		ObservableList<Object> data = FXCollections.observableArrayList();
		for (int i = 1; i< dataset.length; i++) {
			 data.add(dataset[i][columnIndex]);
		}

		return data;
	}

	public void setDataset(Object[][] dataset) {
		this.dataset = dataset;
	}
	
	public Object[][] getDataset() {
		return dataset;
	}
	
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		
		obj.put("chart-title", this.chartTitle);
		obj.put("chart-type", this.chartType);
		JSONArray dataArray = new JSONArray(this.dataset);
		obj.put("dataset", dataArray);
		return obj;
		
	}

	public void fromJson(JSONObject obj) {
		ChartType chartType = obj.optEnum(ChartType.class, "chart-type");
		if (chartType == null) {
			this.chartType = ChartType.SCATTERCHART;
		}else {
			this.chartType = chartType;
		}
		
		String title = obj.optString("chart-title");
		if (title == null) {
			this.chartTitle = "";
		}else {
			this.chartTitle = title;
		}
		
		JSONArray array = obj.optJSONArray("dataset");
		if (array == null) {
			this.dataset = new Object[0][0];
		}else {
			int rows = obj.optJSONArray("dataset").length();
			int columns = 0;
			if (rows != 0) {
				columns = obj.optJSONArray("dataset").getJSONArray(0).length();
			}
			
			this.dataset = new Object[rows][columns];
			for (int r = 0; r < rows; r++) {
				for (int c = 0; c < columns; c++) {
					this.dataset[r][c] = obj.getJSONArray("dataset").getJSONArray(r).get(c);
				}
			}
		}


		
		
	}
}
