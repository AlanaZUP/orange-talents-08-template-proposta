package com.zupacademy.propostas.proposta.consulta;

import com.zupacademy.propostas.proposta.Proposta;
import com.zupacademy.propostas.proposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/propostas")
public class ConsultaProposta {

    @Autowired
    private PropostaRepository propostaRepository;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PropostaConsultaDTO consulta(@PathVariable("id") Long idProposta){
        Proposta proposta = propostaRepository.findById(idProposta).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe uma proposta com a identificação informada"));
        return new PropostaConsultaDTO(proposta);
    }
}
