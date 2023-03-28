package cine.info.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import cine.info.enums.FileDirectory;
import cine.info.enums.FrameworkConstants;

public class DataProviderUtils {

	private static Workbook workbook;

	private DataProviderUtils() {
		throw new IllegalStateException("Utility class");
	}

	/* Eager Initialization */
	static {
		try {
			workbook = WorkbookFactory.create(new FileInputStream(FileDirectory.TEST_DATA_FILE.getDirectory()));
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String[] getExcelData(String testCaseId) {
		Sheet sheet = workbook.getSheet(testCaseId);
		int rowCount = sheet.getLastRowNum();
		List<String> data = new ArrayList<>(); // Creating list to store elements
		for (int i = 1; i <= rowCount; i++) { // Starting from 1 as 0th row contains headers
			Row row = sheet.getRow(i);
			Cell enableCell = row.getCell(0);
			Cell movieCell = row.getCell(1);
			if (enableCell.toString().equalsIgnoreCase(FrameworkConstants.YES.name())) {
				data.add(movieCell.getStringCellValue()); // Adding value directly to list
			}
		}
		return data.toArray(new String[0]); // Converting list to array and returning
	}

	// public static Object[][] getExcelData(String sheetName) {
	// Sheet sheet = workbook.getSheet(sheetName);
	// Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	// // System.out.println(sheet.getLastRowNum() + "--------" +
	// // sheet.getRow(0).getLastCellNum());
	// for (int i = 0; i < sheet.getLastRowNum(); i++) {
	// for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
	// data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
	// // System.out.println(data[i][k]);
	// }
	// }
	// return data;
	// }

}
