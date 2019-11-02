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

import com.qa.seleniumCRUD.CreateLandingPage;
import com.qa.seleniumCRUD.LoginPage;

public class MainMenu {
	WebDriver driver;
	Actions action;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/Daniel Little/chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	@Category(Cat1.class)
	public void goToLogin() {
		
		driver.get("http://35.242.137.2:8080/workoutTracker-1.0/frontpage.html");
		
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[2]/button"))).click().perform();
		driver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
		
		LoginPage loPage = PageFactory.initElements(driver, LoginPage.class);
		String loginButton = loPage.loginButton();
		
		assertEquals("Login", loginButton);
		
	}
	
	@Test
	@Category(Cat2.class)
	public void goToCreateUser() {
		
		driver.get("http://35.242.137.2:8080/workoutTracker-1.0/frontpage.html");
		
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[3]/button"))).click().perform();
		driver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
		
		CreateLandingPage laPage = PageFactory.initElements(driver, CreateLandingPage.class);
		String create = laPage.createButton();
		
		assertEquals("Create", create);
		
	}
	
}
