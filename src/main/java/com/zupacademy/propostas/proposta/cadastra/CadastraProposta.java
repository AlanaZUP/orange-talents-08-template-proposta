package com.zupacademy.propostas.proposta.cadastra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zupacademy.propostas.commos.logs.LogExample;
import com.zupacademy.propostas.commos.metrics.PropostaMetrics;
import com.zupacademy.propostas.proposta.Proposta;
import com.zupacademy.propostas.proposta.PropostaRepository;
import com.zupacademy.propostas.proposta.cadastra.analisaSolicitacao.AnalisaSolicitacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class CadastraProposta {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private AnalisaSolicitacao analisaSolicitacao;

    @Autowired
    private PropostaMetrics propostaMetrics;

    private final Logger logger = LoggerFactory.getLogger(LogExample.class);

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> cadastraProposta(@RequestBody @Valid PropostaRequest propostaRequest, UriComponentsBuilder uriBuilder) throws JsonProcessingException {
        Proposta proposta = propostaRequest.toModel(propostaRepository);
        propostaRepository.save(proposta);

        proposta.analisaSolicitacao(analisaSolicitacao);
        proposta = propostaRepository.save(proposta);

        URI uri = uriBuilder.path("/propostas/{id}").build(proposta.getId());

        logger.info("Proposta documento={} salário={} criada com sucesso!", proposta.getDocumento(), proposta.getSalario());

        propostaMetrics.contadorPropostas();

        return ResponseEntity.created(uri).build();
    }
}
