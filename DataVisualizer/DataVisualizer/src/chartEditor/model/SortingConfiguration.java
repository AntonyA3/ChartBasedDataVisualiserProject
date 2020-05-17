package chartEditor.model;


import org.json.JSONObject;


public class SortingConfiguration {

	
	private SortMode sortingMode;
	private boolean sortByFrequencyAccending;
	private boolean sortByFrequencyDecending;
	private boolean sortByNameAccending;
	private boolean sortByNameDecending;
	private boolean live;
	public SortingConfiguration() {
		this.sortingMode = SortMode.FrequencyAccending;
		this.sortByFrequencyAccending = false;
		this.sortByFrequencyDecending = false;
		this.sortByNameAccending = false;
		this.sortByNameDecending = false;
		setlive();
	}
	

	public SortMode getSortingMode() {
		return sortingMode;
	}

	
	public void setSortingMode(SortMode sortingMode) {
		this.sortingMode = sortingMode;
	}
	
	public void setSortByFrequencyAccending(boolean sortByFrequencyAccending) {
		this.sortByFrequencyAccending = sortByFrequencyAccending;
		setlive();
	}
	
	public void setSortByFrequencyDecending(boolean sortByFrequencyDecending) {
		this.sortByFrequencyDecending = sortByFrequencyDecending;
		setlive();
	}
	
	public boolean isSortByFrequencyAccending() {
		return sortByFrequencyAccending;
	}
	
	public boolean isSortByFrequencyDecending() {
		return sortByFrequencyDecending;
	}

	private void setlive() {
		live = this.sortByFrequencyAccending || this.sortByFrequencyDecending
				|| this.sortByNameAccending || this.sortByNameDecending;
	}
	
	public void setSortByNameDecending(boolean sortByNameDecending) {
		this.sortByNameDecending = sortByNameDecending;
		setlive();

	}
	
	public void setSortByNameAccending(boolean sortByNameAccending) {
		this.sortByNameAccending = sortByNameAccending;
		setlive();

	}
	
	public boolean isSortByNameAccending() {
		return sortByNameAccending;
	}
	
	public boolean isSortByNameDecending() {
		return sortByNameDecending;
	}
	public boolean isLive() {
		return live;
	}


	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("sorting-mode", this.sortingMode);
		obj.put("sort-by-frequency-accending", this.sortByFrequencyAccending);
		obj.put("sort-by-frequency-decending", this.sortByFrequencyDecending);
		obj.put("sort-by-name-accending", this.sortByNameAccending);
		obj.put("sort-by-name-decending", this.sortByNameDecending);
		return obj;
	}
	
	public void fromJson(JSONObject obj) {
		this.sortingMode = obj.optEnum(SortMode.class, "sorting-mode", SortMode.FrequencyAccending);
		this.sortByFrequencyAccending = obj.optBoolean("sort-by-frequency-accending", false);
		this.sortByFrequencyDecending = obj.optBoolean("sort-by-frequency-decending", false);
		this.sortByNameAccending = obj.optBoolean("sort-by-name-accending", false);
		this.sortByNameDecending = obj.optBoolean("sort-by-name-decending",false);
		this.setlive();
	}

}
