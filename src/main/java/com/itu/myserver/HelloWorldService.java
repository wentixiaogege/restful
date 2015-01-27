package com.itu.myserver;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorldService {
//	@GET
//	@Path("/{param}")
//	public Response getMsg(@PathParam("param") String msg) {
//
//		String output = "Gqq say : " + msg;
//
//		return Response.status(200).entity(output).build();
//
//	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello Jersey";
	}
}
