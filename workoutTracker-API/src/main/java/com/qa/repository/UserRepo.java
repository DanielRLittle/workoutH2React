package com.qa.repository;

import java.util.List;

import com.qa.model.User;

public interface UserRepo {

	public User createUser(User user);
	
	public User readUser(int id);
	
	public User readUser(String userName);
	
	public List<User> readAll();
	
	public User updateUser(int id, User newUser);
	
	public void deleteUser(int id);
	
}
