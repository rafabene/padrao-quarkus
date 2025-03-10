package com.rafabene.application;

import java.util.List;

public class Response<T> {

    public enum Status {
        SUCESS, ERROR
    }

    public class FieldError {
        private String field;
        private String message;

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
    private List<FieldError> errors = List.of();

    public Response(T data, String message, Status status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

}
