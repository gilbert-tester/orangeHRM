package com.qa.orangeHRM.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.orangeHRM.pages.HomePage;
import com.qa.orangeHRM.pages.LoginPage;
import com.qa.orangeHRM.pages.PimPage;

public class BaseTest {
	
	WebDriver driver;
	protected Properties prop;
	protected BasePage basePage; //1. create object of BasePage.
	protected LoginPage loginPage; //2. create object of LoginPage.
	protected	HomePage homePage; //3. create object of HomePage.
	protected	PimPage pimPage; // 4. Object of PimPage
	
@Parameters({"browser"})	
	@BeforeTest
	public void setUp (String browserName) {
		basePage = new BasePage();
		prop = basePage.init_prop();
		prop.setProperty("browser", browserName);
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
	
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	
	

}
