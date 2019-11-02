package com.qa.seleniumCRUD;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CRUD {
	
	// Do not run tests from this class, use the runner class to edit the tests required

WebDriver driver;
Actions action;
String fName = "Hello";
String lName = "Goodbye";	
String newFName = null;
String newLName = null;


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
	public void stage1_TestCreateFunc() {
		driver.get("http://35.242.137.2:8080/workoutTracker-1.0/createUser.html");
		CreateLandingPage laPage = PageFactory.initElements(driver, CreateLandingPage.class);
		laPage.typeName(fName, lName);
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[5]/button"))).click().perform();
		String confirmed = laPage.confirmationText();
		assertEquals("User successfully created!", confirmed);
	}
	
	@Test
	@Category(Cat2.class)
	public void stage2_TestGetFunc() {
		driver.get("http://35.242.137.2:8080/workoutTracker-1.0/loginPage.html");
		LoginPage loPage = PageFactory.initElements(driver, LoginPage.class);
		UserLandingPage usPage = PageFactory.initElements(driver, UserLandingPage.class);
		
		loPage.typeName(fName, lName);
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[5]/button"))).click().perform();
		driver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
		
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[1]/button[2]"))).click().perform();
		String nameDisplayed = usPage.findDetails();
		
		assertEquals(fName+lName, nameDisplayed);
	}
	
	@Test
	@Category(Cat4.class)
	public void stage4_TestUpdateFuncSuccess() {
		newFName = lName;
		newLName = fName;
		
		UpdatePage upPage = PageFactory.initElements(driver, UpdatePage.class);
		LoginPage loPage = PageFactory.initElements(driver, LoginPage.class);
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		driver.get("http://35.242.137.2:8080/workoutTracker-1.0/loginPage.html");
		
		loPage.typeName(fName, lName);
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[5]/button"))).click().perform();
		driver.manage().timeouts().implicitlyWait(1l, TimeUnit.SECONDS);
		
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[1]/button[4]"))).click().perform();
		driver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
		
		upPage.typeName(newFName, newLName);
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[5]/button"))).click().perform();
		String confirmedUpdate = upPage.updateConfirmation();
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		String redirected = hPage.findHomePage();
		
		assertEquals("A fresh login is required for your username to take effect, redirecting...", confirmedUpdate);
		assertEquals("Track Your Workouts!", redirected);
		
	}
	
	@Test
	@Category(Cat5.class)
	public void stage3_TestUpdateFuncFail() {
		
		String newerFName = "";
		String newerLName = "";
		
		UpdatePage upPage = PageFactory.initElements(driver, UpdatePage.class);
		LoginPage loPage = PageFactory.initElements(driver, LoginPage.class);
		driver.get("http://35.242.137.2:8080/workoutTracker-1.0/loginPage.html");
		
		loPage.typeName(fName, lName);
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[5]/button"))).click().perform();
		driver.manage().timeouts().implicitlyWait(1l, TimeUnit.SECONDS);
		
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[1]/button[4]"))).click().perform();
		driver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
		
		upPage.typeName(newerFName, newerLName);
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[5]/button"))).click().perform();
		
		String failedUpdate = upPage.updateRejected();
		String remained = upPage.findHeader();
		
		assertEquals("Cannot enter a blank name!", failedUpdate);
		assertEquals("Enter a Username:", remained);
		
	}
	
	@Test
	@Category(Cat3.class)
	public void stage6_deleteFuncYes() {

		LoginPage loPage = PageFactory.initElements(driver, LoginPage.class);
		DeletePage dPage = PageFactory.initElements(driver, DeletePage.class);
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		driver.get("http://35.242.137.2:8080/workoutTracker-1.0/loginPage.html");
		
		loPage.typeName(lName, fName);
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[5]/button"))).click().perform();
		driver.manage().timeouts().implicitlyWait(2L, TimeUnit.SECONDS);
		
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[1]/button[5]"))).click().perform();
		driver.manage().timeouts().implicitlyWait(2L, TimeUnit.SECONDS);
		
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[2]/button"))).click().perform();
		String deleteConfirmation = dPage.deleteMessage();
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		
		String redirected = hPage.findHomePage();
		
		assertEquals("Account has been deleted, redirecting...", deleteConfirmation);
		assertEquals("Track Your Workouts!", redirected);
		
		
	}
	
	@Test
	@Category(Cat3.class)
	public void stage5_deleteFunctionNo() {
		
		LoginPage loPage = PageFactory.initElements(driver, LoginPage.class);
		UserLandingPage usPage = PageFactory.initElements(driver, UserLandingPage.class);
		driver.get("http://35.242.137.2:8080/workoutTracker-1.0/loginPage.html");
		
		loPage.typeName(lName, fName);
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[5]/button"))).click().perform();
		driver.manage().timeouts().implicitlyWait(2L, TimeUnit.SECONDS);
		
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[1]/button[5]"))).click().perform();
		driver.manage().timeouts().implicitlyWait(2L, TimeUnit.SECONDS);
		
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[4]/button"))).click().perform();
		driver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
		
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[1]/button[2]"))).click().perform();
		String nameDisplayed = usPage.findDetails();
		
		assertEquals(lName+fName, nameDisplayed);
		
	}
}
