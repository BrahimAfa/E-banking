package com.ensas.ebanking.exceptions;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String Model ,long id) {
        super(Model+" with the id "+id+" is not Found");

    }
    public ModelNotFoundException(String Model ,String email) {
        super(Model+" with the email "+email+" not Found");

    }
}
