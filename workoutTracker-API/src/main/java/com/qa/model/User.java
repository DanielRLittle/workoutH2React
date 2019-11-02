package com.qa.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private int age;
	
	@OneToMany(cascade = (CascadeType.ALL), fetch = (FetchType.EAGER))
	@JoinColumn(name = "User_Id")
	private Set<Workout> workouts;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName() {
		this.userName = this.firstName + this.lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setAll(User newUser) {
		this.firstName = newUser.firstName;
		this.lastName = newUser.lastName;
		this.userName= newUser.firstName + newUser.lastName;
	}
	public Set<Workout> getWorkouts() {
		return workouts;
	}
	
	public Set<Workout> addWorkouts(Workout w) {
		workouts.add(w);
		return workouts;
	}
	
	public Set<Workout> removeWorkouts(Workout w) {
		workouts.remove(w);
		return workouts;
	}
	
}


