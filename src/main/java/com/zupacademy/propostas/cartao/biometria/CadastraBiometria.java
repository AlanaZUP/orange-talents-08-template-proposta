package com.zupacademy.propostas.cartao.biometria;

import com.zupacademy.propostas.cartao.Cartao;
import com.zupacademy.propostas.cartao.CartaoRepository;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cartoes")
public class CadastraBiometria {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private Tracer tracer;

    @PostMapping("/{id}/biometrias")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity cadastraBiometria(@PathVariable("id") Long idCartao, @RequestBody @Valid BiometriaRequest biometriaRequest, UriComponentsBuilder uriBuilder){
        Cartao cartao = cartaoRepository.findById(idCartao).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado um cartão com o id informado"));
        Biometria biometria = biometriaRequest.toModel(cartao);
        cartao.adicionaBiomatria(biometria);

        cartaoRepository.save(cartao);

        Span activeSpan = tracer.activeSpan();
        activeSpan.setBaggageItem("user.email", cartao.getProposta().getEmail());


        URI uri = uriBuilder.path("/cartoes/{idCartao}/biometrias/{idBiomatrie}").build(cartao.getId(), biometria.getUuid());
        return ResponseEntity.created(uri).build();
    }
}
