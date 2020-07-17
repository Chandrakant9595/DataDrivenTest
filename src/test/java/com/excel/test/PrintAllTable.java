package com.excel.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.excel.utility.XLS_Reader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PrintAllTable {

	public static WebDriver driver;
	
	String pathOfExcelFile = "D:/Projects/DataDrivenFrameWork/src/main/java/com/excel/testData/TestData.xlsx";
	XLS_Reader reader = new XLS_Reader(pathOfExcelFile);
	
	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.w3schools.com/html/html_tables.asp");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void printAllTable() {

		//*[@id="customers"]/tbody/tr[2]/td[1]
		//*[@id="customers"]/tbody/tr[3]/td[1]
		//*[@id="customers"]/tbody/tr[4]/td[1]

		//*[@id="customers"]/tbody/tr[2]/td[2]
		//*[@id="customers"]/tbody/tr[3]/td[2]
		
		//*[@id="customers"]/tbody/tr[2]/td[3]
		//*[@id="customers"]/tbody/tr[3]/td[3]
		
		//company
		String beforeXpath_Company = "//*[@id='customers']/tbody/tr[";
		String afterXpath_Company = "]/td[1]";
		
		//contact
		String beforeXpath_Contact = "//*[@id='customers']/tbody/tr[";
		String afterXpath_Contact = "]/td[2]";
		
		//Country
		String beforeXpath_Country = "//*[@id='customers']/tbody/tr[";
		String afterXpath_Country = "]/td[3]";
		
				
		
		
		//add new sheet in excel if it not present
		if(!reader.isSheetExist("WriteData")) {
				reader.addSheet("WriteData");
				
				//add columns in sheet
				reader.addColumn("WriteData", "Company Name");
				reader.addColumn("WriteData", "Contact Name");
				reader.addColumn("WriteData", "Country Name");
		}
		
		
		//count rows of table
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='customers']//tr"));
		int rowCount = rows.size();
		
		for(int i = 2; i<=rowCount; i++) {
			//company
			String actualXpath_Company = beforeXpath_Company+i+afterXpath_Company;
			String companyName = driver.findElement(By.xpath(actualXpath_Company)).getText();
			System.out.println(companyName);
			reader.setCellData("WriteData", "Company Name", i, companyName);
			
			//Contact
			String actualXpath_Contact = beforeXpath_Contact+i+afterXpath_Contact;
			String contactName = driver.findElement(By.xpath(actualXpath_Contact)).getText();
			System.out.println(contactName);
			reader.setCellData("WriteData", "Contact Name", i, contactName);
			
			//Country
			String actualXpath_Country = beforeXpath_Country+i+afterXpath_Country;
			String countryName = driver.findElement(By.xpath(actualXpath_Country)).getText();
			System.out.println(countryName);
			reader.setCellData("WriteData", "Country Name", i, countryName);
		}
	}
}





