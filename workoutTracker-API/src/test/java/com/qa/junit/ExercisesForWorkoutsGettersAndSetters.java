package com.qa.junit;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.qa.model.ExercisesForWorkout;

public class ExercisesForWorkoutsGettersAndSetters {
	
	ExercisesForWorkout exercise;
	
	@Before
	public void setup() {
		exercise = new ExercisesForWorkout();
	}
	
	@Test
	public void testingRepsPerSet() {
		int reps = 12;
		int sets = 6;
		
		exercise.setTotalReps(reps);
		exercise.setSets(sets);
		
		exercise.setRepsPerSet();
		
		assertEquals("failed to find correct number", BigDecimal.valueOf(2), exercise.getRepsPerSet());
	}

}
