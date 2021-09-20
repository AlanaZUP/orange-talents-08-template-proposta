package com.zupacademy.propostas.proposta.cadastra.analisaSolicitacao;

import com.zupacademy.propostas.commos.validations.document.CpfOrCnpj;
import com.zupacademy.propostas.commos.validations.existsId.ExistisId;
import com.zupacademy.propostas.proposta.Proposta;
import com.zupacademy.propostas.proposta.StatusProposta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AnalisaSolicitacaoResponse {
    @CpfOrCnpj
    @NotBlank
    private String documento;
    @NotBlank
    private String nome;
    @NotNull
    @ExistisId(acceptedNull = false, classe = Proposta.class)
    private Long idProposta;
    @NotBlank
    private StatusSolicitacao resultadoSolicitacao;

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public StatusSolicitacao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public StatusProposta getStatusProposta() {
        return resultadoSolicitacao.statusProposta();
    }

}
