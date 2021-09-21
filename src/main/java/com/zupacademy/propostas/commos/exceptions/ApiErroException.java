package com.zupacademy.propostas.commos.exceptions;

import org.springframework.http.HttpStatus;

public class ApiErroException extends RuntimeException{
    private HttpStatus httpStatus;
    private String campo;
    private String message;

    public ApiErroException(HttpStatus httpStatus, String campo, String message){
        this.httpStatus = httpStatus;
        this.campo = campo;
        this.message = message;
    }

    public String getCampo() {
        return campo;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
