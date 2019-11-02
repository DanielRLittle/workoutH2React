package com.qa.seleniumCRUD;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeletePage {

	@FindBy(id="redirectAfterUpdate")
	private WebElement confirmDelete;
	
	public String deleteMessage() {
		return confirmDelete.getText();
	}
}
