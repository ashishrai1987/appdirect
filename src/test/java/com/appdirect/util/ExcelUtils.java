package com.appdirect.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelUtils {

	private File myFile;
	private FileInputStream myStream;
	private HSSFWorkbook myWorkbook;
	private static HSSFSheet mySheet;
	private static int numRows;
	private static int numCols;

	// column declarations
	// address


	public static int getNumRows(String sheetname) {
		try {
	
		ExcelUtils xl = new ExcelUtils(sheetname);
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return numRows;
	}

	public static int getNumCols() {
		return numCols;
	}

	public ExcelUtils(String sheetname) throws IOException {
		try{
		myFile = new File("data/User_list.xls");
		FileInputStream myStream = new FileInputStream(myFile);
		myWorkbook = new HSSFWorkbook(myStream);
		mySheet = myWorkbook.getSheet(sheetname);
		numRows = mySheet.getLastRowNum() + 1;
		numCols = mySheet.getRow(0).getLastCellNum();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static Object[][] get2DObjectArr() {

		Object[][] excelData = new Object[numRows][numCols];

		System.out.println("Populating Array");
		for (int i = 0; i < numRows; i++) {
			HSSFRow row = mySheet.getRow(i);
			for (int j = 0; j < numCols; j++) {
				HSSFCell cell = row.getCell(j);
				String value = cellToString(cell);
				excelData[i][j] = value;
			}
		}

		return excelData;
	}

	public static String[] getStringArrofRow(int row) {

		if (row < numRows) {
			String[] excelData = new String[numCols];

			
			HSSFRow one_row = mySheet.getRow(row);
			for (int j = 0; j < numCols; j++) {
				HSSFCell cell = one_row.getCell(j);
				String value = cellToString(cell);
				excelData[j] = value;
			}
			
			return excelData;

		} else {
			throw new ArrayIndexOutOfBoundsException(
					" row to return doesnt exist !!! please check !!");
		}
	}
	
	public String[] getRow(int row) {

		if (row < numRows) {
			String[] excelData = new String[numCols];

			
			HSSFRow one_row = mySheet.getRow(row);
			for (int j = 0; j < numCols; j++) {
				HSSFCell cell = one_row.getCell(j);
				String value = cellToString(cell);
				excelData[j] = value;
			}
			
			return excelData;

		} else {
			throw new ArrayIndexOutOfBoundsException(
					" row to return doesnt exist !!! please check !!");
		}
	}

	public static String cellToString(HSSFCell cell) {
		int type = cell.getCellType();
		String result;
		// Formulas can't be evaluated, so throw an Exception if one is
		// encountered
		if (type == HSSFCell.CELL_TYPE_FORMULA) {
			throw new RuntimeException(
					"Cannot process a formula. Please change field to result of formula.");
		}
		// If blanks are ever able to be evaluated by Apache POI, set them to
		// empty string
		else if (type == HSSFCell.CELL_TYPE_BLANK) {
			result = " ";
		}
		// Convert cell contents to String
		else {
			result = String.valueOf(cell)+"";
		}
		return result;
	}
	
	public String getCell(int row, int col){
		
		Row row1 = mySheet.getRow(row + 1);    
		Cell cell = row1.getCell(col); 
		
		return String.valueOf(cell);
	}	
	
   

}
