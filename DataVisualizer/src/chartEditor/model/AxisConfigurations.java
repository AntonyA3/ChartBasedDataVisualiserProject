package chartEditor.model;


import org.json.JSONObject;

public class AxisConfigurations {
	

	public static double LOWEST_SCALE = 0.01;
	
	public static final String KEY_SCALABLE = "scalable";
	public static final String KEY_PANNABLE = "pannable";
	public static final String KEY_MINSCALE = "min-scale-factor";
	public static final String KEY_MAXSCALE = "max-scale-factor";
	public static final String KEY_LOWER_BOUND = "lower-bound";
	public static final String KEY_UPPER_BOUND = "upper-bound";
	public static final String KEY_DEFAULTSCALE = "default-scale";
	public static final String KEY_DEFAULTRANGE = "default-range";
	public static final String KEY_DEFAULT_SCALEFACTOR = "default-scale-factor";
	
	private boolean defaultRange;
	private boolean scalable;
	private boolean pannable;
	private boolean defaultScale;
	private double defaultScaleFactor;
	private double maxscalefactor;
	private double minscalefactor;
	private double lowerbound;
	private double upperbound;
	
	
	public AxisConfigurations() {
		this.defaultRange = true;
		this.pannable = false;
		this.scalable = false;
		this.defaultScale = true;
		this.maxscalefactor = 1;
		this.minscalefactor = 1;
		this.lowerbound = 1;
		this.upperbound = 1;
	}
	
	
	public boolean isDefaultRange() {
		return defaultRange;
	}
	
	public boolean isScalable() {
		return scalable;
	}
	
	public double getMaxscalefactor() {
		return maxscalefactor;
	}
	public double getMinscalefactor() {
		return minscalefactor;
	}
	
	public double getLowerbound() {
		return lowerbound;
	}
	
	public double getUpperbound() {
		return upperbound;
	}
	
	public void setLowerbound(double lowerbound) {
		this.lowerbound = lowerbound;
	}
	
	public void setUpperbound(double upperbound) {
		this.upperbound = upperbound;
	}
	
	public void setMaxscalefactor(double maxscalefactor) {
		if (maxscalefactor < LOWEST_SCALE) {
			this.maxscalefactor = LOWEST_SCALE;
		}else {
			this.maxscalefactor = maxscalefactor;
		}
	}
	
	public void setMinscalefactor(double minscalefactor) {
		if (minscalefactor < LOWEST_SCALE) {
			this.minscalefactor = LOWEST_SCALE;
		}else {
			this.minscalefactor = minscalefactor;
		}
		
	}
	
	public void setDefaultRange(boolean defaultRange) {
		this.defaultRange = defaultRange;
	}
	
	public void setDefaultScale(boolean defaultScale) {
		this.defaultScale = defaultScale;
	}
	public void setScalable(boolean scalable) {
		this.scalable = scalable;
	}

	public void fromJson(JSONObject axisConf) {
		scalable = axisConf.optBoolean(KEY_SCALABLE, false);
		pannable = axisConf.optBoolean(KEY_PANNABLE,false);
		minscalefactor = axisConf.optDouble(KEY_MINSCALE, 1);
		maxscalefactor = axisConf.optDouble(KEY_MAXSCALE, 1);
		defaultScale = axisConf.optBoolean(KEY_DEFAULTSCALE, true);
		lowerbound = axisConf.optDouble(KEY_LOWER_BOUND, 1);
		upperbound = axisConf.optDouble(KEY_UPPER_BOUND,1);
	}

	/*
	 * jsonobj1 = new JSONObject();
	jsonobj1.put(AxisConfigurations.KEY_SCALABLE, false);
	jsonobj1.put(AxisConfigurations.KEY_PANNABLE, false);
	jsonobj1.put(AxisConfigurations.KEY_MINSCALE, 1);
	jsonobj1.put(AxisConfigurations.KEY_MAXSCALE, 1);
	jsonobj1.put(AxisConfigurations.KEY_DEFAULTSCALE, true);
	jsonobj1.put(AxisConfigurations.KEY_LOWER_BOUND, 1);
	jsonobj1.put(AxisConfigurations.KEY_UPPER_BOUND, 1);
	
	}
	 * 
	 */

	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put(KEY_SCALABLE, this.scalable);
		obj.put(KEY_PANNABLE, this.pannable);
		obj.put(KEY_DEFAULTSCALE, this.defaultScale);
		obj.put(KEY_MINSCALE, this.minscalefactor);
		obj.put(KEY_MAXSCALE, this.maxscalefactor);
		obj.put(KEY_LOWER_BOUND, this.lowerbound);
		obj.put(KEY_UPPER_BOUND, this.upperbound);
		return obj;
	}
	
	public boolean isDefaultScale() {
		return defaultScale;
	}
	
	public boolean isPannable() {
		return pannable;
	}


	public void setPannable(boolean b) {
		this.pannable = b;
		
	}
	
	public void setDefaultScaleFactor(double defaultScaleFactor) {
		this.defaultScaleFactor = defaultScaleFactor;
	}

	public double getDefaultScaleFactor() {
		return defaultScaleFactor;
	}
}
