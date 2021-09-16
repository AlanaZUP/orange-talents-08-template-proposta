package com.zupacademy.propostas.commos.exceptions;

public class ValorDuplicadoException extends RuntimeException{
    private String campo;
    private String message;

    public ValorDuplicadoException(String campo, String message){
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
}
