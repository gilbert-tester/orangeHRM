package com.qa.orangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangeHRM.base.BasePage;
import com.qa.orangeHRM.utils.ElementUtil;

public class PimPage extends BasePage{

	ElementUtil elementUtil;
	private WebDriver driver;
	
	private By PIMlinkMenu = By.id("menu_pim_viewPimModule");
	private By addPIMEmployee = By.id("menu_pim_addEmployee");
	
	private By headerEmp = By.xpath("//h1[contains(text(),'Add Employee')]");
	private By firstName = By.id("firstName");
	private By middleName = By.xpath("//input[@id = 'middleName']");
	private By lastName = By.cssSelector("#lastName");
	private By empID = By.xpath("//input[@id='employeeId']");
	private By createLoginDetails = By.xpath("//input[@id='chkLogin']");
	private By buttonSave = By.xpath("//input[@id='btnSave']");
	
	
	public PimPage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		this.driver = driver;
	}
	
	public boolean getPIMpageURL() {
		return elementUtil.waitForUrl(null, 10);		
	}
	
	public String getEmployeeHeaderValue () {
		return elementUtil.doGetText(headerEmp);
	}
	
	public void addEmployee(String firstName, String middleName, String lastName, String empID) {
		
		elementUtil.waitForElementToBeLocated(addPIMEmployee, 10);
		elementUtil.doClick(addPIMEmployee);
		
		elementUtil.waitForElementToBeLocated(this.firstName, 5);
		elementUtil.waitForElementToBeLocated(this.middleName, 5);
		elementUtil.waitForElementToBeLocated(this.lastName, 5);
		elementUtil.waitForElementToBeLocated(this.empID, 5);
		elementUtil.doActionsClick(createLoginDetails);
		//elementUtil.doClick(buttonSave);
		
		
	}
	
	
//	public void getAddEmployeeInfo () {
//		elementUtil.doSendKeys(firstName, "George");
//		elementUtil.doSendKeys(middleName, "L");
//		elementUtil.doSendKeys(lastName, "B");
//		elementUtil.doSendKeys(empID, "002321");
//		elementUtil.doClick(createLoginDetails);
//		elementUtil.doActionsClick(buttonSave);
//	}
	
	
}
