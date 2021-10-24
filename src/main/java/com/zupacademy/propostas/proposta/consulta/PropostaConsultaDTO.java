package com.zupacademy.propostas.proposta.consulta;

import com.zupacademy.propostas.cartao.Cartao;
import com.zupacademy.propostas.commos.validations.document.CpfOrCnpj;
import com.zupacademy.propostas.proposta.Proposta;
import com.zupacademy.propostas.proposta.StatusProposta;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaConsultaDTO {

    private Long id;
    private String documento;
    private String nome;
    private String email;
    private String endereco;
    private BigDecimal salario;
    private Cartao cartao;
    private StatusProposta statusProposta;

    public PropostaConsultaDTO(Proposta proposta) {
        this.id = proposta.getId();
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.email = proposta.getEmail();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.cartao = proposta.getCartao();
        this.statusProposta = proposta.getStatusProposta();
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }
}
