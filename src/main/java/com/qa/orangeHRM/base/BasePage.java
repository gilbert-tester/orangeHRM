package com.qa.orangeHRM.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.orangeHRM.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionManager;
	public static String highlight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to launch the browser and initialize WebDriver
	 * 
	 * @author Gilbert Simon Baidya
	 * @param browserName
	 * @return this method will return WebDriver
	 */

	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser");

		System.out.println("browser name is : " + browserName);
		highlight = prop.getProperty("highlight");
		optionManager = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();

			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver(browserName);
			} else {
				// driver = new ChromeDriver(optionManager.getChromeOptions());
				tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
			}
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver(browserName);
			} else {
//				driver = new FirefoxDriver(optionManager.getFirefoxOptions());
				tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
			}

		} else if (browserName.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			// driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());

		} else if (browserName.equalsIgnoreCase("safari")) {

//			driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("Please pass the correct browser : " + browserName);
		}

		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	/**
	 * This method will define the desired capabilities and will initialize the
	 * driver with capabilities. This method will send the request to the GRID - HUB
	 * @param browserName
	 */
	
	
	public void init_remoteDriver(String browserName) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, optionManager.getChromeOptions());
			cap.setCapability("version", "85.0");
			cap.setCapability("enableVNC", true);
			//cap.setCapability("enableVideo", true);
			
			try {// remote webdriver is superclass of ChromeDriver
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} 
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionManager.getFirefoxOptions());
			cap.setCapability("version", "63.0");
			cap.setCapability("enableVNC", true);
			//cap.setCapability("enableVideo", true);
			
			try {// remote webdriver is superclass of ChromeDriver
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} 
		}
	}
	
	/**
	 * getDriver using ThreadLocal
	 * @return
	 */
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	

	/**
	 * 
	 * This method is used to initialize the properties from config file
	 * 
	 * @return Properties prop object
	 */

	public Properties init_prop() {

		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/main/java/com/qa/orangeHRM/properties/config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
		/**
		 * take screenshot
		 */
		
		public String getScreenshot() {
			File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
			File destination = new File(path);
			try {
				FileUtils.copyFile(src, destination);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return path;
		}

	}

