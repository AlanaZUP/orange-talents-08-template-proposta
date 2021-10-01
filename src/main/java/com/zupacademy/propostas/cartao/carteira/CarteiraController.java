package com.zupacademy.propostas.cartao.carteira;

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
public class CarteiraController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private NotificaCarteira notificaCarteira;

    @Autowired
    private Tracer tracer;

    @PostMapping("/{id}/carteiras")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity adicionaCarteira(@PathVariable("id") Long id, @RequestBody @Valid CarteiraRequest carteiraRequest, UriComponentsBuilder uriBuilder){
        Cartao cartao = cartaoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe cartão com a identificação recebida"));

        Carteira carteira = carteiraRequest.toModel(cartao);
        cartao.adicionarCarteira(carteira, notificaCarteira);
        cartaoRepository.save(cartao);

        Span activeSpan = tracer.activeSpan();
        activeSpan.setBaggageItem("user.email", cartao.getProposta().getEmail());


        URI uri = uriBuilder.path("/cartoes/{idCartao}/carteiras/{idCarteira}").build(cartao.getId(), carteira.getUuid());
        return ResponseEntity.created(uri).build();
    }
}
