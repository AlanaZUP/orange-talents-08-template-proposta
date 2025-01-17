package com.zupacademy.propostas.commos.exceptions;


public class ErroResponse {
    private String field;
    private Object value;
    private String message;

    public ErroResponse(String field, Object value, String message) {
        this.field = field;
        this.value = value;
        this.message = message;
    }

    public Object getValue() {
        return value;
    }

    public String getField() {
        return field;
    }
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErroResponse{" +
                "field='" + field + '\'' +
                ", value=" + value +
                ", message='" + message + '\'' +
                '}';
    }
}