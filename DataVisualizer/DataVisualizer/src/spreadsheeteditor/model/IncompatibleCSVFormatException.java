package spreadsheeteditor.model;

public class IncompatibleCSVFormatException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IncompatibleCSVFormatException() {
		super("File loaded is incompatible with CSV format");
	}

}
