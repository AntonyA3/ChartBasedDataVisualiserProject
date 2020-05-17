package chartEditor.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.stream.Collectors;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ChartConfigurations {

	private BasicConfiguration basicConfiguration;
	private ArrayList<SeriesConfigurations> seriesConfigurationsArray;
	private SortingConfiguration sortingConfiguration;
	private ColorConfiguration colorConfiguration;
	private AxisConfigurations axisConfigurationY;
	private AxisConfigurations axisConfigurationX;
	
	public ChartConfigurations() {
		this(new Object[0][0]);
	}
	public ChartConfigurations(Object[][] data) {
		this.basicConfiguration = new BasicConfiguration();
		this.basicConfiguration.setDataset(data);
		this.seriesConfigurationsArray = new ArrayList<>();
		this.sortingConfiguration = new SortingConfiguration();
		this.colorConfiguration = new ColorConfiguration();
		this.axisConfigurationX = new AxisConfigurations();
		this.axisConfigurationY = new AxisConfigurations();
	}

	public JSONObject toJSon() {
		JSONObject obj = new JSONObject();
		obj.put("basic-configuration", this.basicConfiguration.toJson());
		obj.put("series-configuration-array", this.seriesConfigurationsArray.stream().map(x -> x.toJSon()).collect(Collectors.toList()));
		obj.put("sorting-configuration", this.sortingConfiguration.toJson());
		obj.put("color-configuration", this.colorConfiguration.toJson());
		obj.put("x-axis-configuration", this.axisConfigurationX.toJson());
		obj.put("y-axis-configuration", this.axisConfigurationY.toJson());
		return obj;
	}
	
	public void fromJSon(JSONObject obj) {
		JSONObject basic = obj.optJSONObject("basic-configuration");
		if (basic == null) {
			this.basicConfiguration = new BasicConfiguration();
		}else {
			this.basicConfiguration.fromJson(basic);
		}
		
		JSONArray series = obj.optJSONArray("series-configuration-array");
		if (series == null) {
			this.seriesConfigurationsArray.clear();
		}else {
			this.seriesConfigurationsArray.clear();
			series.forEach(x ->{
				SeriesConfigurations s = new SeriesConfigurations();
				s.fromJSon((JSONObject) x);
				this.seriesConfigurationsArray.add(s);
			});
		}
		
		JSONObject sorting = obj.optJSONObject("sorting-configuration");
		if (sorting == null) {
			this.sortingConfiguration = new SortingConfiguration();
		}else {
			this.sortingConfiguration.fromJson(sorting);
		}
		JSONObject color = obj.optJSONObject("color-configuration");
		if (color == null) {
			this.colorConfiguration = new ColorConfiguration();
		}else {
			this.colorConfiguration.fromJson(color);
		}
		
		JSONObject xAxisConf = obj.optJSONObject("x-axis-configuration");
		if (xAxisConf == null) {
			this.axisConfigurationX = new AxisConfigurations();
		}else {
			this.axisConfigurationX.fromJson(xAxisConf);
		}
		
		JSONObject yAxisConf = obj.optJSONObject("y-axis-configuration");
		if (xAxisConf == null) {
			this.axisConfigurationY = new AxisConfigurations();
		}else {
			this.axisConfigurationY.fromJson(yAxisConf);
		}
	}
	

	
	public void saveTojson(File file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.append(this.toJSon().toString());
		writer.close();
		
	}
	public void saveChart() throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Chart");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Chart", "*.json"),
				new FileChooser.ExtensionFilter("All Files", "*.*"));
		
		File file = fileChooser.showSaveDialog(new Stage());
		if (file != null) {
			saveTojson(file);
		}
		
	}
	
	public void loadChart() throws IOException, JSONException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load Chart");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Chart", "*.json"),
				new FileChooser.ExtensionFilter("All Files", "*.*"));
		
		File file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {
			loadFromJson(file);
		}
		
	}
	
	public void loadFromJson(File file) throws FileNotFoundException, JSONException {
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		String data = reader.lines().collect(Collectors.joining(" "));
		if (data.isEmpty()) {
			throw new JSONException("empty file invalid");

		}else {
			try {
				@SuppressWarnings("unused")
				JSONObject jsonObject = new JSONObject(data);
			}catch(JSONException e) {
				throw new JSONException("loading failed");
			}finally {
						this.fromJSon(new JSONObject(data));

			}

		}
		
		
	}



	
	public ArrayList<SeriesConfigurations> getSeriesConfigurationsArray() {
		return seriesConfigurationsArray;
	}
	
	public BasicConfiguration getBasicConfiguration() {
		return basicConfiguration;
	}
	
	public SortingConfiguration getSortingConfiguration() {
		return sortingConfiguration;
	}
	
	public ColorConfiguration getColorConfiguration() {
		return colorConfiguration;
	}

	public AxisConfigurations getAxisConfigurationY() {
		return this.axisConfigurationY;
	}

	public AxisConfigurations getAxisConfigurationX() {
		return this.axisConfigurationX;
	}

}
