package chartEditor.model;

import org.json.JSONObject;

public class SeriesConfigurations {
	private String seriesTitle;
	private int xAxisIndex;
	private int yAxisIndex;
	
	public SeriesConfigurations() {
		this.seriesTitle ="";
		this.xAxisIndex = 0;
		this.yAxisIndex = 0;
	}
	public int getxAxisIndex() {
		return xAxisIndex;
	}
	public int getyAxisIndex() {
		return yAxisIndex;
	}
	
	public String getSeriesTitle() {
		return seriesTitle;
	}
	
	public void setyAxisIndex(int yAxisIndex) {
		if (yAxisIndex >= 0) {
			this.yAxisIndex = yAxisIndex;
		}
		
	}
	
	public void setxAxisIndex(int xAxisIndex) {
		if (xAxisIndex >= 0) {
			this.xAxisIndex = xAxisIndex;
		}
		
	}
	
	public void setSeriesTitle(String seriesTitle) {
		this.seriesTitle = seriesTitle;
	}
	public void fromJSon(JSONObject obj) {
		this.seriesTitle = obj.optString("series-title", "");
		this.xAxisIndex = obj.getInt("x-axis-index");
		this.yAxisIndex = obj.getInt("y-axis-index");
	}
	
	public JSONObject toJSon() {
		JSONObject obj = new JSONObject();
		obj.put("series-title", this.seriesTitle);
		obj.put("x-axis-index", this.xAxisIndex);
		obj.put("y-axis-index", this.yAxisIndex);
		return obj;
	}
	
	
	
}
