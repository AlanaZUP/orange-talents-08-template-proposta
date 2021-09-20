package com.zupacademy.propostas.proposta.cadastra.analisaSolicitacao;

import com.zupacademy.propostas.proposta.StatusProposta;

public enum StatusSolicitacao {
    COM_RESTRICAO{
        @Override
        StatusProposta statusProposta() {
            return StatusProposta.NAO_ELEGIVEL;
        }
    }, SEM_RESTRICAO{
        @Override
        StatusProposta statusProposta() {
            return StatusProposta.ELEGIVEL;
        }
    };

    abstract StatusProposta statusProposta();
}
