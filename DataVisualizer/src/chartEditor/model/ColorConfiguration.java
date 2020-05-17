package chartEditor.model;

import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ColorConfiguration {
	private static String[] defaultcolorScheme = new String[] {"#4D4D4D","#5DA5DA","#FAA43A", "#60BD68", "#F17CB0","#B2912F","#B276B2","#DECF3F","#F15854"};
	private ObservableList<String> colorChoices;
	
	public ColorConfiguration() {
		colorChoices = FXCollections.observableArrayList();
	}
	
	public ObservableList<String> getColorChoices() {
		return colorChoices;
	}

	public static String getColorFor(int id) {
		return defaultcolorScheme[id % defaultcolorScheme.length];
	}

	public void fromJson(JSONObject obj) {
		
		JSONArray col = obj.optJSONArray("color-choices");
		System.out.println(col);
		if (col == null) {
			this.colorChoices.clear();
		}else {
			this.colorChoices.setAll(col.toList().stream().map(x -> (String) x).collect(Collectors.toList()));
		}
	}
	
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		
		obj.put("color-choices", this.colorChoices);
		return obj;
		
	}

	public String getColorAt(int i) {
		if (i >= colorChoices.size()) {
			return getColorFor(i);
		}else {
			return colorChoices.get(i);
		}
		
	}
	

	
	
}
