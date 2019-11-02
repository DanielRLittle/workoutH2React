package com.qa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.model.Exercise;

@Transactional(value = TxType.SUPPORTS)
public class ExerciseRepoDB implements ExerciseRepo {

	@PersistenceContext(unitName = "myPU")
	private EntityManager em;
	
	@Transactional(value = TxType.REQUIRED)
	public Exercise createExercise(Exercise exercise) {
		em.persist(exercise);
		return exercise;
	}

	public Exercise readExercise(int id) {
		Exercise exercise = em.find(Exercise.class, id);
		return exercise;
	}

	public Exercise readExerciseByName(String exerciseName) {
		TypedQuery<Exercise> tQ = em.createQuery(
				"select exercise from Exercise exercise where exerciseName = '" + exerciseName + "'", Exercise.class);
		Exercise exercise = tQ.getSingleResult();
		return exercise;
	}

	public List<Exercise> readAll() {
		TypedQuery<Exercise> tQ = em.createQuery(
				"select exercises from Exercise exercises", Exercise.class);
		List<Exercise> exerciseList = tQ.getResultList();
		return exerciseList;
	}

	@Transactional(value = TxType.REQUIRED)
	public Exercise updateExercise(int id, Exercise newExercise) {
		Exercise exercise = readExercise(id);
		exercise.setAll(newExercise);
		return exercise;
	}

	@Transactional(value = TxType.REQUIRED)
	public void deleteExercise(int id) {
		em.remove(readExercise(id));
	}

}
