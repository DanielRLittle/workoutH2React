package com.qa.repository;

import java.util.List;

import com.qa.model.ExercisesForWorkout;
import com.qa.model.Workout;

public interface ExercisesForWorkoutRepo {
	
	public Workout addingBothExerciseAndWorkout(ExercisesForWorkout efw, int workoutId, String exerciseName);
	
	public ExercisesForWorkout findExercise(int id);
	
	public List<ExercisesForWorkout> findExerciseByWorkout(int id);
	
	public ExercisesForWorkout changeExerciseDetails(int id, ExercisesForWorkout newExercise, String exerciseName);
	
	public void deleteExercise(int id);

}
