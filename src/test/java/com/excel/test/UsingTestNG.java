package com.excel.test;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.excel.utility.Util;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UsingTestNG {

	public static WebDriver driver;
	
	String sheetName = "LoginDetails";
	
	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://admin-demo.nopcommerce.com/login");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	@DataProvider
	public Object[][] getDataFromExcel() throws InvalidFormatException {
		Object data [][] = Util.getTestData(sheetName);
		return data;
	}
	
	
	@Test(dataProvider="getDataFromExcel")
	public void LoginTest(String email, String password) {
		driver.findElement(By.xpath("//input[@name='Email']")).clear();
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(email);

		driver.findElement(By.xpath("//input[@name='Password']")).clear();
		driver.findElement(By.xpath("//input[@name='Password']")).sendKeys(password);
	}
}
