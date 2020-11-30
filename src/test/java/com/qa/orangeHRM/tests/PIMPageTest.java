package com.qa.orangeHRM.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.orangeHRM.base.BaseTest;
import com.qa.orangeHRM.utils.Constants;

public class PIMPageTest extends BaseTest{

	
	@BeforeClass
	public void pimPageSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		homePage.gotoPIMlinks();
	
	}
	
	
	
//	WebDriver driver;
//	Properties prop;
//	BasePage basePage; //1. create object of BasePage.
//	LoginPage loginPage; //2. create object of LoginPage.
//	HomePage homePage; //3. create object of HomePage.
//	PimPage pimPage; // 4. Object of PimPage
//	
//	
//	//@BeforeMethod
//	@BeforeTest
//	public void setUp () {
//		basePage = new BasePage();
//		prop = basePage.init_prop();
//		driver = basePage.init_driver(prop);
//		loginPage = new LoginPage(driver);
//		homePage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim()); // if you not call homePage as reference = .. it will show nullpointerexception error
//		pimPage = homePage.gotoPIMlinks();
//	
//	}
	
	@Test(priority =1)
	public void verifyPIMLinkURLTest() {
		boolean pageURL = pimPage.getPIMpageURL();
		System.out.println(pageURL);
		Assert.assertTrue(pageURL);
	}
	@Test(priority =2)
	public void verifyPIMEmpListHeaderTest () {
		String empHeader = pimPage.getEmployeeHeaderValue();
		System.out.println(empHeader);
		Assert.assertEquals(empHeader, Constants.PIM_PAGE_HEADER, Constants.PIM_Employee_ERROR_MESSAGE);
		
	}
	
	@Test (priority =3)
	
	public void addEmployeeList() {
		
		pimPage.addEmployee("George", "L", "B", "gsb@gmail.com");
		
		
	}
	
	
}
