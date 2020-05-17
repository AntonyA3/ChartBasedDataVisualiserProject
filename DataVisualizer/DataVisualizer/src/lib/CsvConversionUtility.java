package lib;

public final class CsvConversionUtility {

	public static String toCsvString(String s) {
		return "\"" + s + "\"";
	}
	
	public static String toCsvNumber(Object cell) {
		return String.valueOf(cell);
		
	}
	
	public static String getValueDelimetre() {
		return ",";
	}
	
	public static String getRowDelimetre() {
		return "\n";
	}
	
	public static String getValueType(String value) {
		if (value.endsWith("\"") && value.startsWith("\"")) {
			return "string";
		}
		else if (value.matches("((\\d+)((\\.\\d{1,2})?))$")){
			return "double";
		}else {
			return "null";
		}
		
	}

	public static String getStringValue(String string) {
		
		return string.substring(1, string.length()-1);
	}
	
	public static double getDoubleValue(String n) {
		return Double.valueOf(n);
	}

	public static CharSequence toCsvNull() {
		return "";
	}

	public static CharSequence toCsV(Object cell) {
		if (cell == null) {
			return "";
		}
		else if (cell instanceof String) {
			return "\"" + String.valueOf(cell) + "\"" ;

		}else if (cell instanceof Float || cell instanceof Double || cell instanceof Integer || cell instanceof Long) {
			return String.valueOf(cell);
		}
		return null;
	}
}
