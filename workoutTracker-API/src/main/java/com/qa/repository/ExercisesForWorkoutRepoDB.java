package com.qa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.model.Exercise;
import com.qa.model.ExercisesForWorkout;
import com.qa.model.Workout;

public class ExercisesForWorkoutRepoDB implements ExercisesForWorkoutRepo {
	
	@PersistenceContext(unitName = "myPU")
	EntityManager em;
	
	ExerciseRepo er;
	
	public Exercise addExerciseToEfw(ExercisesForWorkout efw, String exerciseName) {
		TypedQuery<Exercise> tQ = em.createQuery(
				"select exercise from Exercise exercise where exerciseName = '"
				+ exerciseName + "'", Exercise.class);
		Exercise exercise = tQ.getSingleResult();
		exercise.addExercises(efw);
		return exercise;
	}
	
	@Transactional(value = TxType.REQUIRED)
	public Workout addingBothExerciseAndWorkout(ExercisesForWorkout efw, int workoutId, String exerciseName) {
		efw.setRepsPerSet();
		Workout workout = em.find(Workout.class, workoutId);
		addExerciseToEfw(efw, exerciseName);
		workout.addExercises(efw);
		return workout;
	}

	public ExercisesForWorkout findExercise(int id) {
		ExercisesForWorkout efw = em.find(ExercisesForWorkout.class, id);
		return efw;
	}

	public List<ExercisesForWorkout> findExerciseByWorkout(int id) {
		TypedQuery<ExercisesForWorkout> tq = em
				.createQuery("Select efw from ExercisesForWorkout efw where workout_id = '" + id + "'", ExercisesForWorkout.class);
		List<ExercisesForWorkout> efw  = tq.getResultList();
		return efw;
	}

	@Transactional(value =  TxType.REQUIRED)
	public ExercisesForWorkout changeExerciseDetails(int id, ExercisesForWorkout newExercise,
			String exerciseName) {
		ExercisesForWorkout efw = findExercise(id);
		addExerciseToEfw(efw, exerciseName);
		efw.setAll(newExercise, exerciseName);
		return efw;
	}

	@Transactional(value = TxType.REQUIRED)
	public void deleteExercise(int id) {
		em.remove(findExercise(id));
	}
	
}
