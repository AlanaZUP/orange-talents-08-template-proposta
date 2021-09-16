package com.zupacademy.propostas.proposta.cadastra;

import com.zupacademy.propostas.commos.validations.document.CpfOrCnpj;
import com.zupacademy.propostas.proposta.Proposta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank @CpfOrCnpj
    private String documento;
    @NotBlank
    private String nome;
    @NotBlank @Email
    private String email;
    @NotBlank
    private String endereco;
    @NotNull @Positive
    private Double salario;

    public PropostaRequest(String documento, String nome, String email, String endereco, Double salario) {
        this.documento = documento;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel(){
        return new Proposta(documento, nome, email, endereco, salario);
    }
}
