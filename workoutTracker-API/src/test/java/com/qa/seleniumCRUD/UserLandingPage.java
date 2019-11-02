package com.qa.seleniumCRUD;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserLandingPage {
	
	@FindBy(id = "firstName")
	private WebElement showFirstName;
	
	@FindBy(id = "lastName")
	private WebElement showLastName;
	
	
	public String findDetails() {
		return showFirstName.getText() + showLastName.getText();
	}
}
