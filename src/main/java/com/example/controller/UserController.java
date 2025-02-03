package com.example.controller;

import java.util.UUID;

import com.example.model.User;
import com.example.services.UserService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GET
    @Path("/{id}")
    public Response find(@PathParam("id") UUID id){
        return Response.ok(service.findUser(id)).build();
    }

    @GET
    @Path("/all")
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page, 
                            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize){
        
        var users = this.service.findAll(page, pageSize);
        return Response.ok(users).build();
    }

    @POST
    public Response createUser(User user){
        return Response.ok(service.save(user)).build();
    }

}
