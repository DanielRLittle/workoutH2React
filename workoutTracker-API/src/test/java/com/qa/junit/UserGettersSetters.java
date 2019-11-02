package com.qa.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.qa.model.User;

public class UserGettersSetters {

	User user;
	
	@Before
	public void setup() {
		user = new User();
	}
	
	@Test
	public void testingFirstName() {
		user.setFirstName("H");
		
		assertEquals("H", user.getFirstName());
		
	}
	
	@Test
	public void testingLastName() {
		user.setLastName("H");
		assertEquals("H", user.getLastName());
	}
	
	@Test
	public void testUserName() {
		String fName = "xxx";
		String lName = "yyy";
		user.setFirstName(fName);
		user.setLastName(lName);
		user.setUserName();
		assertEquals("xxxyyy", user.getUserName());
	}
}
