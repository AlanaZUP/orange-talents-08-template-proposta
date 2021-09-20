package com.zupacademy.propostas.proposta.cadastra.analisaSolicitacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "solicitacao", url = "http://localhost:9999/api")
public interface AnalisaSolicitacao {

    @PostMapping("/solicitacao")
    AnalisaSolicitacaoResponse consultar(AnalisaSolicitacaoRequest analisaSolicitacaoRequest);
}
