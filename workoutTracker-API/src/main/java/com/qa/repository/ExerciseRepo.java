package com.qa.repository;

import java.util.List;

import com.qa.model.Exercise;

public interface ExerciseRepo {
	
	public Exercise createExercise(Exercise exercise);
	
	public Exercise readExercise(int id);
	
	public Exercise readExerciseByName(String exerciseName);
	
	public List<Exercise> readAll();
	
	public Exercise updateExercise(int id, Exercise newExercise);
	
	public void deleteExercise(int id);

}
