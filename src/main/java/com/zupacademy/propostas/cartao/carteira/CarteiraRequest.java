package com.zupacademy.propostas.cartao.carteira;

import com.zupacademy.propostas.cartao.Cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraRequest {

    @Email @NotBlank
    private String email;
    @NotNull
    private EnumCarteiras carteira;

    public CarteiraRequest(String email, EnumCarteiras carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public Carteira toModel(Cartao cartao){
        return new Carteira(email, carteira, cartao);
    }
}
