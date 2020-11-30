package com.qa.orangeHRM.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.orangeHRM.base.BaseTest;
import com.qa.orangeHRM.utils.Constants;

public class HomePageTest extends BaseTest{
	
	
	@BeforeClass
	public void homePageSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	
//	WebDriver driver;
//	Properties prop;
//	BasePage basePage; //1. create object of BasePage.
//	LoginPage loginPage; //2. create object of LoginPage.
//	HomePage homePage; //3. create object of HomePage.
	
	
	//@BeforeMethod
//	@BeforeTest
//	public void setUp () {
//		basePage = new BasePage();
//		prop = basePage.init_prop();
//		driver = basePage.init_driver(prop);
//		loginPage = new LoginPage(driver);
//		homePage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim()); // if you not call homePage as reference = .. it will show nullpointerexception error
//		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	}
	
	
	@Test (priority = 1)
	public void verifyHomePageTitle() {
		
		String titleHomePage = homePage.getHomePageTitle();
		System.out.println("Home Page title is : "+titleHomePage);
		Assert.assertEquals(titleHomePage, Constants.HomePage_PAGE_TITLE,Constants.HOME_PAGE_TITLE_ERROR_MESSEGE);
	}
	
	@Test(priority = 2)
	public void HomePageHeaderTest() {
		
		String headerTest = homePage.getHomePageHeaderValue();
		System.out.println("Home page header : " + headerTest);
		Assert.assertEquals(headerTest, Constants.HomePage_HEADER, Constants.HOME_PAGE_HEADER_ERROR_MESSAGE);
	}
	
	@Test(priority = 3)
	public void UserAccountNameTest() {
		
		String accountName = homePage.getUserAccountName();
		System.out.println("User Account Name : " + accountName);
		Assert.assertEquals(Constants.HomePage_ACCOUNT_NAME, prop.getProperty("accountname").trim() , Constants.HOME_PAGE_ACCOUNT_NAME_ERROR_MESSAGE);
	}
		
	@Test(priority = 4)
	public void NotificationIconTest() {
		
		System.out.println();
		boolean notificationIcon = homePage.isExistNotificationIcon();
		System.out.println("Notification Icon is available = " + notificationIcon);
		Assert.assertTrue(notificationIcon, Constants.HOME_PAGE_NOTIFICATION_ICON_ERROR_MESSAGE);
	}
	
	
	
	
		
}
