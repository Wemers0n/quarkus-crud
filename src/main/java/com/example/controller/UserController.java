package com.example.controller;

import com.example.model.User;
import com.example.services.UserService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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

    @POST
    public Response createUser(User user){
        return Response.ok(service.save(user)).build();
    }

}
