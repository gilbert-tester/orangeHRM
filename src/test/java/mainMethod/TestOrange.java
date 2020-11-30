package mainMethod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestOrange {
	static WebDriver driver;
public static void main(String[] args) {
	
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	
	driver.get("https://opensource-demo.orangehrmlive.com/");
	
	driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
	driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
	driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
	
	boolean dashboard = driver.findElement(By.xpath("//h1[contains(text(),'Dashboard')]")).isDisplayed();
	
	System.out.println(dashboard);
	System.out.println(driver.findElement(By.linkText("Welcome Paul")).getText());
	
	driver.quit();
}
}
