package com.excel.test;

import org.openqa.selenium.WebDriver;

import com.excel.utility.XLS_Reader;

public class OtherPeration {

	public static WebDriver driver;
	
	public static void main(String[] args) {
	
		String pathOfExcelFile = "D:/Projects/DataDrivenFrameWork/src/main/java/com/excel/testData/TestData.xlsx"; 
		
		XLS_Reader reader = new XLS_Reader(pathOfExcelFile);
		
		//add sheet
		if(!reader.isSheetExist("Result")) {
			reader.addSheet("Result");
		}
		
		//get column count
		int columnCount = reader.getColumnCount("LoginDetails");
		System.out.println(columnCount);
		
		//get row count
		int rowCount = reader.getRowCount("LoginDetails");
		System.out.println(rowCount);

		
	}

}
