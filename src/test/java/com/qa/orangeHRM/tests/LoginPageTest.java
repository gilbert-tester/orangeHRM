package com.qa.orangeHRM.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.orangeHRM.base.BaseTest;
import com.qa.orangeHRM.listeners.TestAllureListener;
import com.qa.orangeHRM.pages.HomePage;
import com.qa.orangeHRM.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;



@Epic("Epic 100: Design Login Page Feature for OrangeHRM Application") //Allure report
@Feature("US 101: Design Login Page module with title, sign up and login form") //Allure report
@Listeners(TestAllureListener.class) //it will help to generate screenshot to the allure report 
public class LoginPageTest extends BaseTest{
//	WebDriver driver;
//	Properties prop;
//	BasePage basePage; //1. create object of BasePage.
//	LoginPage loginPage; //2. create object of LoginPage.
	

//	@Parameters("browser")
//	@BeforeTest
//	public void setUp (String browser) {
//		basePage = new BasePage();
//		prop = basePage.init_prop();
//		prop.setProperty("browser", browser);
//		driver = basePage.init_driver(prop);
//		loginPage = new LoginPage(driver);
//	}
	// all these moved to the BaseTest.java
	
	@Description ("Verify Login Page Title Test") //Allure report
	@Severity(SeverityLevel.NORMAL)//allure report
	@Test (priority = 2)
	public void verifyLoginPageTitle() {
		String titleLoginPage = loginPage.getLoginPageTitle();
		System.out.println("Login Page title is : "+titleLoginPage);
		Assert.assertEquals(titleLoginPage,Constants.LOGIN_PAGE_TITLE ,Constants.LOGIN_TITLE_ERROR_MESSEGE);
	}
	@Description ("Verify Forgot password Link Test") //Allure report
	@Severity(SeverityLevel.BLOCKER)
	@Test (priority = 1)
	public void verifyForgotPasswrodLink() {
		boolean ForgotPassLink = loginPage.isForgotPassLinkExist();
		System.out.println("Forgot password link is available = "+ForgotPassLink);
		Assert.assertTrue(ForgotPassLink, Constants.FORGOT_PASSWORD_LINK_ERROR_MESSEGE);
	}
	@Description ("Login Test") //Allure report
	@Test (priority = 3)
	public void loginTest() {
		
	HomePage homePage =	loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	
	String homePageHeader = homePage.getHomePageHeaderValue();
	System.out.println("Home page header value is : "+homePageHeader);
	Assert.assertEquals(homePageHeader, Constants.HomePage_HEADER);
	
	
	
	}
	
	
	//@AfterMethod
//	@AfterTest
//	public void tearDown() {  // all these moved to the BaseTest.java
//		driver.quit();
//	}
	
}
