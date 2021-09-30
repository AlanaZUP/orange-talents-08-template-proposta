package com.zupacademy.propostas.cartao.viagem;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class NotificaViagemRequest {

    @NotBlank
    private String destino;

    @NotNull @FutureOrPresent
    private LocalDate validoAte;

    @Deprecated
    public NotificaViagemRequest() {
    }

    public NotificaViagemRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
