package mx.com.sixdelta.stream.service;

public interface ReportingService {
	/**
	 * 
	 * Method definition to transform from a JSON
	 * to a EXCEL
	 * 
	 * @param data information to be transformed
	 * @return return the data transformed into data for EXCEL
	 */
	public byte[] transformJSONtoExcel(String data, String sheetName);
	/**
	 * 
	 * Method definition to transform from an Excel document
	 * to a JSON
	 * 
	 * @param data information to be transformed
	 * @param path : path for the document to save/create
	 * @param documentName: name for the document given by the user
	 * @return return the data transformed into data for EXCEL
	 */
	public byte[] transformExcelToJSON(byte[] data);
	
//	public String transformExcelToJSON(String data);
}
