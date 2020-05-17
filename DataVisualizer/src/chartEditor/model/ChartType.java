package chartEditor.model;

public enum ChartType {
BARCHART{
	@Override
	public String toString() {
		return "Bar Chart";
	}
}, 
PIECHART{
	@Override
	public String toString() {
		return "Pie Chart";
	}
}, 
SCATTERCHART{
	@Override
	public String toString() {
		return "Scatter Chart";
	}
}


}



