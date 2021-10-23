package com.zupacademy.propostas.commos.exceptions;

public class NotFoundException extends RuntimeException{
    private String entity;
    private String message;
    private String field;
    private Object value;

    public NotFoundException(String entity, String field, Object value){
        this.entity = entity;
        this.field = field;
        this.value = value;
        this.message = "Não existe " + entity + " com a identificação " + field + " = " + value.toString();
    }

    public String getEntity() {
        return entity;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }
}
