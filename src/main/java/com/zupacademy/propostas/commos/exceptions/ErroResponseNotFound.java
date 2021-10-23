package com.zupacademy.propostas.commos.exceptions;


public class ErroResponseNotFound {
    private String entity;
    private String field;
    private Object value;
    private String message;

    public ErroResponseNotFound(String entity, String field, Object value, String message) {
        this.entity = entity;
        this.field = field;
        this.value = value;
        this.message = message;
    }

    public String getEntity() {
        return entity;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}