package com.qa.navigationTests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.qa.seleniumCRUD.HomePage;

public class UserPageTests {
	
	WebDriver driver;
	Actions action;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/Daniel Little/chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
	
	@Test
	@Category(Cat3.class)
	public void mainMenuNav() {
		
		driver.get("http://35.242.137.2:8080/workoutTracker-1.0/userPage.html");
		
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[1]/button[1]"))).click().perform();
		driver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
		
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		String header = hPage.findHomePage();
		
		assertEquals("Track Your Workouts!", header);
		
	}
	
}
