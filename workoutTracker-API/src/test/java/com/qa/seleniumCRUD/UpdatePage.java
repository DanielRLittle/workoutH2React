package com.qa.seleniumCRUD;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpdatePage {
	

	@FindBy(id="input1")
	private WebElement firstName;
	
	@FindBy(id = "input2")
	private WebElement lastName;
	
	@FindBy(id = "redirectAfterUpdate")
	private WebElement updateMessage;
	
	@FindBy(id = "remain")
	private WebElement remainMessage;
	
	@FindBy(xpath = "/html/body/div[2]/h1")
	private WebElement header;
	
	public void typeName(String fName, String lName) {
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
	}
	
	public String updateConfirmation() {
		return updateMessage.getText();
	}
	
	public String updateRejected() {
		return remainMessage.getText();
	}
	
	public String findHeader() {
		return header.getText();
	}
}
