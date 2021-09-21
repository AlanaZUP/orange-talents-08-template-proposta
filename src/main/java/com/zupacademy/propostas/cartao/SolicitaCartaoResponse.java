package com.zupacademy.propostas.cartao;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SolicitaCartaoResponse {

    private String id;
    private String titular;
    private LocalDateTime emitidoEm;
    private BigDecimal limite;

    public SolicitaCartaoResponse(String id, String titular, LocalDateTime emitidoEm, BigDecimal limite) {
        this.id = id;
        this.titular = titular;
        this.emitidoEm = emitidoEm;
        this.limite = limite;
    }

    public String getId() {
        return id;
    }

    public String getTitular() {
        return titular;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public Cartao toModel(){
        return new Cartao(this.id, this.titular, this.emitidoEm, this.limite);
    }
}
