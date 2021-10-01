package com.zupacademy.propostas.cartao.carteira;

import javax.validation.constraints.NotBlank;

public class NotificaCarteiraResponse {

    @NotBlank
    private EnumNotificaCarteira resultado;

    @NotBlank
    private String id;

    @Deprecated
    public NotificaCarteiraResponse() {
    }

    public NotificaCarteiraResponse(EnumNotificaCarteira resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public EnumNotificaCarteira getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
