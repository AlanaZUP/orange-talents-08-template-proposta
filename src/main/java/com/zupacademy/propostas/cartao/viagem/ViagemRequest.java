package com.zupacademy.propostas.cartao.viagem;

import com.zupacademy.propostas.cartao.Cartao;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ViagemRequest {

    @NotBlank
    private String destinoViagem;

    @NotNull @FutureOrPresent
    private LocalDate dataTermino;

    public ViagemRequest(String destinoViagem, LocalDate dataTermino) {
        this.destinoViagem = destinoViagem;
        this.dataTermino = dataTermino;
    }

    public Viagem toModel(String ipRequisicao, String userAgenteRequisicao, Cartao cartao){
        return new Viagem(destinoViagem, ipRequisicao, userAgenteRequisicao, dataTermino, cartao);
    }
}
