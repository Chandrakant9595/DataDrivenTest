package com.excel.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.excel.utility.XLS_Reader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Parameterised {

	public static WebDriver driver;
	
	public static void main(String[] args) {
		
		String pathOfExcelFile = "D:/Projects/DataDrivenFrameWork/src/main/java/com/excel/testData/TestData.xlsx"; 
		
		XLS_Reader reader = new XLS_Reader(pathOfExcelFile);
		
		int rowCount = reader.getRowCount("LoginDetails");

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://admin-demo.nopcommerce.com/login");
		
		//create column in excel sheet
		reader.addColumn("LoginDetails", "Status");
		
		for(int rowNo=2; rowNo<=rowCount; rowNo++) {
			//read data
			String email = reader.getCellData("LoginDetails", "Email", rowNo);
			System.out.println(email);
			
			//read data
			String password = reader.getCellData("LoginDetails", "Password", rowNo);
			System.out.println(password);
						
			//send data to webpage from excel
			driver.findElement(By.xpath("//input[@name='Email']")).clear();
			driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(email);

			//send data to webpage from excel
			driver.findElement(By.xpath("//input[@name='Password']")).clear();
			driver.findElement(By.xpath("//input[@name='Password']")).sendKeys(password);
			
			//write status in excel sheet
			reader.setCellData("LoginDetails", "Status", rowNo, "Pass");
		}
	}
}
