package mx.com.sixdelta.stream.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.sixdelta.stream.bean.ExceptionPath;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReportingServiceImpl implements ReportingService {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ReportingServiceImpl.class);

	@Override
	public byte[] transformJSONtoExcel(String data, String sheetName) {

//		Byte array to return final data
		byte[] dataTransformedAsBytes;
//		Add if necessary [] this are needed to correct run through JSON
		String firstCharacter = data.substring(0, 1);
		if (!firstCharacter.equals("[")) {
			data = "[" + data + "]";
		}
//		Start of the main method
		
//		Reading data and creating Inputstream to read it through JsonNode so it receive the XSSFWorkbook Object
		try (InputStream newData = new ByteArrayInputStream(data.getBytes())) {

			JsonNode jsonTree = new ObjectMapper().readTree(newData);

			if (!jsonTree.isNull()) {

				log.info("Data recieved: " + jsonTree);
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet(sheetName);
//		Iterating through elements of the JSON tree
				Iterator<JsonNode> elements = jsonTree.elements();
				log.info("Elements iterator" + elements.toString());
				int rowCount = 0;
//		Iterating through rows
				while (elements.hasNext()) {
					JsonNode element = elements.next();
					int colCount = 0;
					log.info("Element alone: " + element.toString());
					if (!element.isEmpty()) {
						if (rowCount == 0) {
							Row row = sheet.createRow(rowCount++);
							int headerCount = 0;
							Iterator<String> fieldNames = element.fieldNames();
//		Iterating through cells
							while (fieldNames.hasNext()) {
								String value = fieldNames.next();
								log.info("The header value is: " + value);
								Cell cell = row.createCell(headerCount++);
								cell.setCellValue(value);
							}
						}

						Row row = sheet.createRow(rowCount++);
						Iterator<JsonNode> values = element.elements();
						while (values.hasNext()) {
							String value = values.next().asText();
							log.info("The content value is: " + value);
							Cell cell = row.createCell(colCount++);
							cell.setCellValue(value);
						}
					}
				}

//		Transforming Workbook object to byte array
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				try {
					workbook.write(bos);
					workbook.close();
					{
						bos.close();
					}
					dataTransformedAsBytes = bos.toByteArray();

				} catch (IOException e) {
					throw new ExceptionPath("Error during the creation of the temporary File" + e.getMessage());
				}

			} else {
				throw new ExceptionPath("String received is not on correct JSON format");
			}

		} catch (Exception e) {
			throw new ExceptionPath("Data received is not legible by the method getBytes() /n" + e.getMessage());
		}
//		returning final data as byte array
		return dataTransformedAsBytes;
	}

	public String transformExcelToJSON(String data) {
//		Replaces for path to be InputStream friendly
		data = data.replace("\\\\", "\\");
		data = data.replace("\"", "");
		data = data.replace("\\", "/");
//		String to store final data
		String dataUnformated = "";

		try (FileInputStream excelData = new FileInputStream(data)) {
			// InputStream newData = new ByteArrayInputStream(data.getBytes());
			// Create the workbook Object to work with the ByteArrayInputStream data

			XSSFWorkbook workbook = new XSSFWorkbook(excelData);

			int totalSheetNumber = workbook.getNumberOfSheets();

			for (int index = 0; index < totalSheetNumber; index++) {

				// Get current sheet.
				Sheet sheet = workbook.getSheetAt(index);

				// Get sheet name
				// String sheetName = sheet.getSheetName();

				// Get Array Data from current sheet
				List<List<String>> currentSheetData = getSheetDataList(sheet);

				// Generate String with JSON Format
				dataUnformated = getStringFromList(currentSheetData);

			}
			workbook.close();

		} catch (IOException e) {
			throw new ExceptionPath("Error while reading data from the String received: " + e.getMessage());
		}
		// Json data return
		return dataUnformated;
	}

	private static List<List<String>> getSheetDataList(Sheet sheet) {

		List<List<String>> dataFromSheet = new ArrayList<List<String>>();
		int firstRowNum = sheet.getFirstRowNum();
		int lastRowNum = sheet.getLastRowNum();

		if (lastRowNum > 0) {
			for (int index = firstRowNum; index < lastRowNum - 1; index++) {

				List<String> rowDataList = new ArrayList<String>();
				Row currentRow = sheet.getRow(index);

				int firstCellNum = currentRow.getFirstCellNum();
				int lastCellNum = currentRow.getLastCellNum();

				for (int data = firstCellNum; data < lastCellNum; data++) {

					Cell cell = currentRow.getCell(data);
					rowDataList.add(cell.toString());
				}
				dataFromSheet.add(rowDataList);
			}
		}
		return dataFromSheet;
	}

	private static String getStringFromList(List<List<String>> dataString) {
		StringBuffer strBfr = new StringBuffer();

		strBfr.append("[");

		if (dataString != null) {
			// Getting the headers
			List<String> headers = dataString.get(0);
			// Looping through every each data column
			for (int index = 1; index < dataString.size(); index++) {
				strBfr.append("{");
				for (int columnData = 0; columnData < headers.size(); columnData++) {
					// Assigning headers for each Node
					strBfr.append("\"" + headers.get(columnData) + "\":");
					// Assigning data for each node considering if it's the last data node
					if (columnData == dataString.get(index).size() - 1) {
						// log.info(" " + dataString.get(index).size());
						strBfr.append("\"" + dataString.get(index).get(columnData) + "\"\n");
					} else
						strBfr.append("\"" + dataString.get(index).get(columnData) + "\", \n");
				}
				if (index == dataString.size() - 1) {
					strBfr.append("}");
				} else
					strBfr.append("},");
			}

		}
		strBfr.append("]");
		return strBfr.toString();
	}
}
