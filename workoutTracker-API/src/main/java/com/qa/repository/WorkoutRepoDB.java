package com.qa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.model.User;
import com.qa.model.Workout;

@Transactional(value = TxType.SUPPORTS)
public class WorkoutRepoDB implements WorkoutRepo {
	
	
	@PersistenceContext(unitName = "myPU")
	EntityManager em;

	@Transactional(value = TxType.REQUIRED)
	public User addWorkout(Workout w, int id) {
		User user = em.find(User.class, id);
		user.addWorkouts(w);
		return user;
	}

	public Workout findWorkout(int id) {
		Workout w = em.find(Workout.class, id);
		return w;
	}

	public List<Workout> findWorkoutByUser(int id) {
		TypedQuery<Workout> tQ = em.createQuery("Select w from Workout w WHERE user_id = '" + id + "'", Workout.class);
		List<Workout> w = tQ.getResultList();
		return w;
	}

	@Transactional(value = TxType.REQUIRED)
	public Workout changeWorkout(int id, Workout newWorkout) {
		Workout w = findWorkout(id);
		w.setAll(newWorkout);
		return w;
	}

	@Transactional(value = TxType.REQUIRED)
	public void deleteWorkout(int id) {
		em.remove(findWorkout(id));
	}
	
}
