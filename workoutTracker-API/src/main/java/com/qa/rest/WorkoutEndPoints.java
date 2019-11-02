package com.qa.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.qa.model.User;
import com.qa.model.Workout;
import com.qa.repository.UserRepo;
import com.qa.repository.WorkoutRepo;

@Path("/")
public class WorkoutEndPoints {

	@Inject
	WorkoutRepo wr;
	
	@Inject
	UserRepo ur;
	
	public Response checkWorkout(int id) {
		if (wr.findWorkout(id).equals(null)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		else {
			return null;
		}
	}
	
	@PUT
	@Consumes({"application/json"})
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/workout/{user_id}")
	public Response addingWorkout(Workout workout, @PathParam("user_id") int id) {
		if (ur.readUser(id).equals(null)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		User user = wr.addWorkout(workout, id);
		return Response.accepted(user).build();
	}
	
	@GET
	@Path("/workouts/{workout_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWorkoutById(@PathParam("workout_id") int id) {
		if (wr.findWorkout(id).equals(null)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Workout w = wr.findWorkout(id);
		return Response.ok(w).build();
	}
	
	@GET
	@Path("/workout/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWorkoutByUser(@PathParam("user_id") int id) {
		if (wr.findWorkoutByUser(id).equals(null)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		List<Workout> w = wr.findWorkoutByUser(id);
		return Response.ok(w).build(); 
	}
	
	@PUT
	@Consumes({"application/json"})
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/workouts/{workout_id}")
	public Response updateWorkout(Workout newWorkout, @PathParam("workout_id") int id) {
		if (wr.findWorkout(id).equals(null)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Workout w = wr.changeWorkout(id, newWorkout);
		return Response.accepted(w).build();
	}
	
	@DELETE
	@Path("/workouts/{workout_id}")
	public Response removeWorkout(@PathParam("workout_id") int id) {
		if (wr.findWorkout(id).equals(null)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		wr.deleteWorkout(id);
		return Response.noContent().build();
	}
}
