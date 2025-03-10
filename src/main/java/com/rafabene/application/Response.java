package com.rafabene.application;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonPropertyOrder({ "status", "message", "data", "errors" })
public class Response<T> {

    public static enum Status {

        SUCESS("success"), ERROR("error");

        private String name;

        Status(String name) {
            this.name = name;
        }

        @JsonValue
        public String getName() {
            return name;
        }

    }

    public static class FieldError {
        private String field;
        private String message;

        public FieldError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }
    }

    private T data;
    private String message;
    private Status status;
    private List<FieldError> errors;

    public Response(T data, String message, Status status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public Response(String message, Status status) {
        this(null, message, status);
    }

    @JsonInclude(Include.NON_NULL)
    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }

    @JsonInclude(Include.NON_EMPTY)
    public List<FieldError> getErrors() {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        return errors;
    }

}
