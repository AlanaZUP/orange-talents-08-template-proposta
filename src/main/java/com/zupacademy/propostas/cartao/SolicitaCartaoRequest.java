package com.zupacademy.propostas.cartao;

import com.zupacademy.propostas.commos.validations.document.CpfOrCnpj;
import com.zupacademy.propostas.commos.validations.existsId.ExistisId;
import com.zupacademy.propostas.proposta.Proposta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SolicitaCartaoRequest {

    @CpfOrCnpj @NotBlank
    private String documento;
    @NotBlank
    private String nome;
    @NotNull @ExistisId(classe = Proposta.class, acceptedNull = false)
    private Long idProposta;

    public SolicitaCartaoRequest(String documento, String nome, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
