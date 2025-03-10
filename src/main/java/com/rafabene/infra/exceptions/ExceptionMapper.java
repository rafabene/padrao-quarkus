package com.rafabene.infra.exceptions;

import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import com.rafabene.application.Response;
import com.rafabene.application.Response.FieldError;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response.Status;

public class ExceptionMapper {

    @ServerExceptionMapper
    public jakarta.ws.rs.core.Response handleMyException(ConstraintViolationException x) {

        Response<Void> response = new Response<Void>("Erro de validação", Response.Status.ERROR);
        x.getConstraintViolations().forEach(v -> {
            response.getErrors().add(
                    new FieldError(
                            v.getPropertyPath().toString(),
                            v.getMessage()));
        });
        return jakarta.ws.rs.core.Response.status(Status.BAD_REQUEST).entity(response).build();

    }

}
