package com.qa.orangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangeHRM.base.BasePage;
import com.qa.orangeHRM.utils.Constants;
import com.qa.orangeHRM.utils.ElementUtil;

public class HomePage extends BasePage{

	WebDriver driver;
	ElementUtil elementUtil;
	
	private By header = By.xpath("//h1[contains(text(),'Dashboard')]");
	private By accountName = By.xpath("//a[@id='welcome']");
	private By notificationIcon = By.id("password");
	private By PIMlinkMenu = By.id("menu_pim_viewPimModule");
	private By addPIMEmployee = By.id("menu_pim_addEmployee");
	
	
	public HomePage(WebDriver driver) {
	this.driver = driver;
	elementUtil = new ElementUtil(driver);
}
	
	public String  getHomePageTitle () {
		return elementUtil.waitForPageTitlePresent(Constants.HomePage_PAGE_TITLE, 10);
	}
	
	
	public String getHomePageHeaderValue() {
				if (elementUtil.doIsDisplayed(header)) {
			return elementUtil.doGetText(header);
		}
		return null;
		
	}
	
	public String getUserAccountName () {
		if (elementUtil.doIsDisplayed(accountName)) {
			return elementUtil.doGetText(accountName);
		}
		return null;
		
	}
	
	public boolean isExistNotificationIcon () {
		return elementUtil.doIsDisplayed(notificationIcon);	
	}
	
	public PimPage gotoPIMlinks () {
		getAddPIMEmployee(); // we are implementing encapsulation method.. 
		return new PimPage(driver);
	}

	private void getAddPIMEmployee () { //making private to create an encapsulation method..
		 elementUtil.waitForElementToBeLocated(PIMlinkMenu, 10);
		 elementUtil.doClick(PIMlinkMenu);
		 elementUtil.clickWhenReady(addPIMEmployee, 10);
	}

	
}
