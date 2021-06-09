package com.ensas.ebanking.exceptions;

public class ClientValidationException extends RuntimeException  {
    public ClientValidationException(String err) {
        super(err);
    }

}
