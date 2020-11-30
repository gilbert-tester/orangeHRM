package com.qa.orangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangeHRM.base.BasePage;
import com.qa.orangeHRM.utils.Constants;
import com.qa.orangeHRM.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;

	// 1. Page locator: By locators

	By userID = By.xpath("//input[@id='txtUsername']");
	By pass = By.xpath("//input[@id='txtPassword']");
	By loginBtn = By.xpath("//input[@id='btnLogin']");
	By forgotPassLink = By.linkText("Forgot your password?");

	// 2. Create the constractor of the page class:

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	// 3. Page Actions / page methods
	@Step("Getting Login page title......") // allure report
	public String getLoginPageTitle() {
		return elementUtil.waitForPageTitlePresent(Constants.LOGIN_PAGE_TITLE, 10);

	}

	@Step("Getting forgot password link....") // allure report
	public boolean isForgotPassLinkExist() {

		return elementUtil.doIsDisplayed(forgotPassLink);

	}
	@Step("Login with : {0} and {1}") // allure report
	public HomePage doLogin(String username, String password) {
		System.out.println("login with : " + username + " : " + password);
		elementUtil.doSendKeys(userID, username);
		elementUtil.doSendKeys(pass, password);
		elementUtil.doClick(loginBtn);
		return new HomePage(driver);
	}

}
