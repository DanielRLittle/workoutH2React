package com.qa.rest;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import com.qa.model.Exercise;
import com.qa.repository.ExerciseRepo;

@Path("/")
public class ExerciseEndPoints {
	
	@Inject
	ExerciseRepo er;
	
	@POST
	@Consumes({"application/json"})
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/exercises")
	public Response addExercise(Exercise exercise, @Context UriInfo uriInfo) {
		exercise = er.createExercise(exercise);
		URI createdURI = uriInfo.getBaseUriBuilder().path("" + exercise.getId()).build();
		System.out.println(createdURI);
		return Response.ok(createdURI.toString()).status(Status.CREATED).build();
	}
	
	@GET
	@Path("/exercises")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllExercises() {
		List<Exercise> exerciseList = er.readAll();
		if (exerciseList.isEmpty()) {
			return Response.noContent().build();
		}
		
		return Response.ok(exerciseList).build();
	}
	
	@GET
	@Path("/exercises/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getExerciseById(@PathParam("id") int id) {
		if (er.readExercise(id).equals(null)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		Exercise exercise = er.readExercise(id);
		return Response.ok(exercise).build();
	}
	
	@GET
	@Path("/exercises/exerciseName/{exerciseName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getExerciseByName(@PathParam("exerciseName") String name) {
		if (er.readExerciseByName(name).equals(null)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		Exercise exercise = er.readExerciseByName(name);
		return Response.ok(exercise).build();
	}
	
	@PUT
	@Consumes({"application/json"})
	@Produces(MediaType.APPLICATION_JSON)
	@Path("exercises/{id}")
	public Response updateExercise(Exercise exercise, @PathParam("id") int id) {
		if (er.readExercise(id).equals(null)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		Exercise newEx = er.updateExercise(id, exercise);
		return Response.accepted(newEx).build();
	}
	
	@DELETE
	@Path("/exercises/{id}")
	public Response removeExercise(@PathParam("id") int id) {
		if (er.readExercise(id).equals(null)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		er.deleteExercise(id);
		return Response.noContent().build();
	}
}