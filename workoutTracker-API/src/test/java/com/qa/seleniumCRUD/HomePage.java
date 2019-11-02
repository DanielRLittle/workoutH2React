package com.qa.seleniumCRUD;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	@FindBy(id = "frontPageHeader")
	private WebElement title;
	
	public String findHomePage() {
		return title.getText();
	}
	
}
