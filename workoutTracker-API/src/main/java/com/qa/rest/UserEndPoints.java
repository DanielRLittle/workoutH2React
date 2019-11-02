package com.qa.rest;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.qa.model.User;
import com.qa.repository.UserRepo;

@Path("/")
public class UserEndPoints {

	@Inject
	UserRepo ur;
	
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers() {
		List<User> list = ur.readAll();
		if (list.isEmpty()) {
			return Response.noContent().build();
		}
		return Response.ok(list).build();	
	}
	
	@GET
	@Path("/users/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOneUser(@PathParam("id") int id) {
		if (ur.readUser(id).equals(null)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		User user = ur.readUser(id);
		return Response.ok(user).build();
	}
	
	@GET
	@Path("/users/username/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByName(@PathParam("userName") String userName) {
		if (ur.readUser(userName).equals(null)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		User user = ur.readUser(userName);
		return Response.ok(user).build();
	}
	
	@POST
	@Consumes({"application/json"})
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/users")
	public Response addUser(User user, @Context UriInfo uriInfo) {
		user = ur.createUser(user);
		URI createdURI = uriInfo.getBaseUriBuilder().path(""+user.getId()).build();
		System.out.println(createdURI);
		return Response.ok(createdURI.toString()).status(Status.CREATED).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({"application/json"})
	@Path("/users/{id}")
	public Response alterFirstName(User user, @PathParam("id") int id) {
		if (ur.readUser(id).equals(null)){
			return Response.status(Status.NOT_FOUND).build();
		}
		User user2 = ur.updateUser(id, user);
		return Response.accepted(user2).build();
	}
	
	@DELETE
	@Path("/users/{id}")
	public Response removeUser(@PathParam("id") int id) {
		if (ur.readUser(id).equals(null)){
			return Response.status(Status.NOT_FOUND).build();
		}
		ur.deleteUser(id);
		return Response.noContent().build();
	}	
}