package com.zupacademy.propostas.cartao.bloqueio;

import javax.validation.constraints.NotBlank;

public class NotificaRequest {

    @NotBlank
    public String sistemaResponsavel;

    public NotificaRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
