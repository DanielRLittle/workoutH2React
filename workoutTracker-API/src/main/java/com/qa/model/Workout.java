package com.qa.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Workout {
	
	@Id
	@GeneratedValue
	private int id;
	private String workoutName;
	private String workoutDescription;
	private LocalDate workoutDate;
	
	@OneToMany(cascade = (CascadeType.ALL), fetch = (FetchType.EAGER))
	@JoinColumn(name = "Workout_Id")
	private Set<ExercisesForWorkout> exercises;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWorkoutName() {
		return this.workoutName;
	}
	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}
	public String getWorkoutDescription() {
		return this.workoutDescription;
	}
	public void setWorkoutDescription(String workoutDescription) {
		this.workoutDescription = workoutDescription;
	}
	
	public void setAll(Workout newWorkout) {
		this.workoutName = newWorkout.workoutName;
		this.workoutDescription = newWorkout.workoutDescription;
		this.workoutDate = newWorkout.workoutDate;
		this.exercises = newWorkout.exercises;
	}
	
	public LocalDate getWorkoutDate() {
		return this.workoutDate;
	}
	
	public void setWorkoutDate(LocalDate workoutDate) {
		this.workoutDate = workoutDate;
	}
	
	public Set<ExercisesForWorkout> getExercises() {
		return exercises;
	}
	
	public Set<ExercisesForWorkout> addExercises(ExercisesForWorkout exercise) {
		exercises.add(exercise);
		return exercises;
	}
	
	public Set<ExercisesForWorkout> removeExercises(ExercisesForWorkout exercise) {
		exercises.remove(exercise);
		return exercises;
	}
}
