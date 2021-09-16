package com.zupacademy.propostas.proposta.cadastra;

import com.zupacademy.propostas.proposta.Proposta;
import com.zupacademy.propostas.proposta.PropostaRepository;
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

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> cadastraProposta(@RequestBody @Valid PropostaRequest propostaRequest, UriComponentsBuilder uriBuilder){
        Proposta proposta = propostaRequest.toModel();
        propostaRepository.save(proposta);

        URI uri = uriBuilder.path("/propostas/{id}").build(proposta.getId());
        return ResponseEntity.created(uri).build();
    }
}
