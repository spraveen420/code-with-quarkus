package com.mindtree.controller;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mindtree.model.Example;
import com.mindtree.service.ExampleService;

@Path("/")
public class ExampleController {

	@Inject
	ExampleService exampleService;
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}")
    public Response starter(@PathParam("name") String name) {
        return Response.ok(exampleService.starter(name)).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/findAll")
    public Response findAll() {
    	List<Example> exampleList= exampleService.findAll();
    	if(exampleList.size() == 0) {
    		return Response.noContent().build();
    	}
    	return Response.ok(exampleList).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response create(Example example) {
    	Example exampleResult = exampleService.create(example);
    	if(exampleResult == null) {
    		return Response.status(409, "Already exist").build();
    	}
    	return Response.status(201, "created").entity(exampleResult).build();
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response update(Example example) {
    	Example updatedResult = exampleService.update(example);
    	if(updatedResult == null) {
    		return Response.status(404, "Not exist").build();
    	}
    	return Response.ok(updatedResult).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/findOne/{id}")
    public Response findOne(@PathParam("id") Long id) {
    	Optional<Example> searchResult = exampleService.findOne(id);
    	if(!searchResult.isPresent()) {
    		return Response.status(404, "Not exist").build();
    	}
    	return Response.ok(searchResult).build();
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
    	Optional<Example> searchResult = exampleService.delete(id);
    	if(!searchResult.isPresent()) {
    		return Response.status(404, "Not exist").build();
    	}
    	return Response.ok(searchResult).build();
    }
    
//    @POST
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.TEXT_PLAIN)
//    @Path("/fileUpload")
//    public Response fileUpload(@MultipartForm MultipartExample fileData) {
//    	exampleService.fileUpload(fileData);
//    	return null;
//    }
    
}
