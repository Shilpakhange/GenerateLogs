package com.test;

import static org.testng.Assert.assertEquals;
import org.apache.log4j.BasicConfigurator;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

	// what is log? //Capturing info/activities at the time of program execution

	// types of logs:
//	1. info
//	2. warn
//	3.debug
//	4.fatal

	// How to generate the logs?--Use Apache log4j Api
	// How it works?-It reads log4j configuration from log4j.properties file
	// where to create the log4j properties file-->inside the resources folder

	WebDriver driver;
	Logger log = Logger.getLogger(LoginTest.class);

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Shilpa Khandge\\Downloads\\chromedriver_win32 (3)\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		log.info("----------Chrome opened-------------");
		driver.get("https://classic.crmpro.com/index.html");
		log.warn("-----------Application launched-------------");

	}

	@Test
	public void FreeCRMTitleTest() {
		String title = driver.getTitle();
		assertEquals(title, "CRMPRO - CRM software for customer relationship management, sales, and support.",
				"title not matched");
	}

	@Test
	public void FreeCRMlogoTest() {
		boolean b = driver.findElement(By.xpath("//a[@class='navbar-brand']//img[@class='img-responsive']"))
				.isDisplayed();
		assertEquals(b, true);

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
