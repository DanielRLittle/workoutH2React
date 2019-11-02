package com.qa.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ExercisesForWorkout {
	
	@Id
	@GeneratedValue
	private int id;
	
	private BigDecimal weight;
	
	private int sets;
	
	private int totalReps;
	
	private BigDecimal repsPerSet;
	
	private String exerciseName;
	
	public void setAll(ExercisesForWorkout newExercise, String exerciseName) {
		this.weight = newExercise.weight;
		this.sets = newExercise.sets;
		this.totalReps = newExercise.totalReps;
		this.setRepsPerSet();
		this.exerciseName = exerciseName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	public int getTotalReps() {
		return totalReps;
	}

	public void setTotalReps(int totalReps) {
		this.totalReps = totalReps;
	}
	
	public BigDecimal getRepsPerSet() {
		return repsPerSet;
	}
	
	public void setRepsPerSet() {
		this.repsPerSet = BigDecimal.valueOf(this.totalReps)
				.divide(BigDecimal.valueOf(this.sets));
	}
	
	public String getExerciseName() {
		return exerciseName;
	}
	
	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

}
