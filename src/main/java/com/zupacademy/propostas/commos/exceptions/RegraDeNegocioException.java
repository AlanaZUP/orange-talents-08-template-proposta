package com.zupacademy.propostas.commos.exceptions;

import org.springframework.http.HttpStatus;

public class RegraDeNegocioException extends RuntimeException{
    private String field;
    private String message;
    private Object value;

    public RegraDeNegocioException(String field, String message, Object value){
        this.field = field;
        this.message = message;
        this.value = value;
    }


    public String getField() {
        return field;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Object getValue() {
        return value;
    }
}
