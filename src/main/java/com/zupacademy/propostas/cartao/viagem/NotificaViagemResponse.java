package com.zupacademy.propostas.cartao.viagem;

import javax.validation.constraints.NotNull;

public class NotificaViagemResponse {

    @NotNull
    private StatusNotificaViagem resultado;

    @Deprecated
    public NotificaViagemResponse() {
    }

    public NotificaViagemResponse(StatusNotificaViagem resultado) {
        this.resultado = resultado;
    }

    public StatusNotificaViagem getResultado() {
        return resultado;
    }
}
