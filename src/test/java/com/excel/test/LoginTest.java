package com.excel.test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.excel.utility.XLS_Reader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {

	public static WebDriver driver;
	
	public static void main(String[] args) {
		String pathOfExcelFile = "D:/Projects/DataDrivenFrameWork/src/main/java/com/excel/testData/TestData.xlsx"; 
		
		XLS_Reader reader = new XLS_Reader(pathOfExcelFile);
		
		//read data
		String userName = reader.getCellData("LoginDetails", "Email", 2);
		System.out.println(userName);
		
		//read data
		String password = reader.getCellData("LoginDetails", "Password", 2);
		System.out.println(password);
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://admin-demo.nopcommerce.com/login");
		
		//send data to webpage from excel
		driver.findElement(By.xpath("//input[@name='Email']")).clear();
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(userName);

		//send data to webpage from excel
		driver.findElement(By.xpath("//input[@name='Password']")).clear();
		driver.findElement(By.xpath("//input[@name='Password']")).sendKeys(password);
		
		
	}

}
