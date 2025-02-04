package com.example.exception;

import com.example.exception.dto.ErrorResponse;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UserNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException> {

    @Override
    public Response toResponse(UserNotFoundException exception) {

        var error = new ErrorResponse(exception.getMessage(), Response.Status.BAD_REQUEST.getStatusCode());

        return Response.status(error.getStatus()).entity(error).type(MediaType.APPLICATION_JSON).build();
    }

}
