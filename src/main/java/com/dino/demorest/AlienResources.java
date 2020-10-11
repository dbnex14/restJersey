package com.dino.demorest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("aliens")
public class AlienResources {
	
	AlienRepository repo = new AlienRepository();

	@GET
	@Path("alien/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Alien getAlien(@PathParam("id") int id) {
		return repo.getAlien(id);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Alien> getAliens() {
		System.out.println("AlienResource: getAliens GET method");
		return repo.getAliens();
	}
	
	@POST
	@Path("alien")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Alien createAlien(Alien a) {
		System.out.println(a);
		repo.addAlien(a);
		return a;
	}
}
