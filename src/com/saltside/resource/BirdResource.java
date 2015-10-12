package com.saltside.resource;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.saltside.mongo.MyMongoClient;
import com.saltside.util.RestUtility;

@Path("/rest")
public class BirdResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	public BirdResource(UriInfo uriInfo, Request request) {
		this.uriInfo = uriInfo;
		this.request = request;
	}
	
	public BirdResource() {
	
	}
	
	@GET
	@Path("/birds")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBirds(@PathParam("param") String msg) {
		DBCursor cursorDoc = MyMongoClient.getDBCollection().find();
		String[] items = new String[cursorDoc.length()];
		int i = 0;
		while (cursorDoc.hasNext()) {
			items[i] = cursorDoc.next().toString();
			i++;
		}
		return Response.status(200).entity(items).build();
	}
	
	@GET
	@Path("/birds/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBirdDetails(@PathParam("id") String id) {
		DBObject dbObject = (DBObject) MyMongoClient.getDBCollection().findOne(id);
		if(dbObject != null) {
			return Response.status(200).entity(dbObject).build();
		}
		else {
			return Response.status(404).build();
		}
	}
	
	@POST
	@Path("/{birds}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void postBirds(@Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse) throws IOException {
		String requestJson = RestUtility.getBody(servletRequest);
		Gson gson = new Gson();
		Bird bird = gson.fromJson(requestJson, Bird.class);
		String birdJson = new Gson().toJson(bird);
		DBObject dbObject = (DBObject) JSON
				.parse(birdJson);

		MyMongoClient.getDBCollection().insert(dbObject);
	}
	
	@DELETE
	@Path("/birds/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBird(@PathParam("id") String id) {
		DBObject dbObject = (DBObject) MyMongoClient.getDBCollection().findOne(id);
		MyMongoClient.getDBCollection().remove(dbObject);
		if(dbObject != null) {
			return Response.status(200).entity(dbObject).build();
		}
		else {
			return Response.status(404).build();
		}
	}

}