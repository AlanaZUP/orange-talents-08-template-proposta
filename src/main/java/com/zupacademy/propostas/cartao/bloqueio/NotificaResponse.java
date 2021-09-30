package com.zupacademy.propostas.cartao.bloqueio;

import javax.validation.constraints.NotBlank;

public class NotificaResponse {

    @NotBlank
    private StatusNotifica resultado;

    @Deprecated
    public NotificaResponse() {
    }

    public NotificaResponse(StatusNotifica resultado) {
        this.resultado = resultado;
    }

    public StatusNotifica getResultado() {
        return resultado;
    }
}
