package com.supermarket.utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	XSSFWorkbook wb;
	XSSFSheet sh;
	XSSFRow row;
	XSSFCell cell;

	public void setExcelFile(String workBookName, String sheetName) {
		try {
			String path = System.getProperty("user.dir") + "\\src\\main\\resources\\ExcelFiles\\" + workBookName
					+ ".xlsx";
			File src = new File(path);
			FileInputStream fi = new FileInputStream(src);
			wb = new XSSFWorkbook(fi);
			sh = wb.getSheet(sheetName);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public String get_CellData(int rowNum, int colNum) {
		row = sh.getRow(rowNum);
		cell = row.getCell(colNum);

		switch (cell.getCellType()) {
		case STRING: {
			String x;
			x = cell.getStringCellValue();
			return x;
		}
		case NUMERIC: {
			long d = (long) cell.getNumericCellValue();
			return String.valueOf(d);
		}

		default:
			return null;

		}

	}
}
