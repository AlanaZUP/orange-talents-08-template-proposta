package com.zupacademy.propostas.cartao.viagem;

import com.zupacademy.propostas.cartao.Cartao;
import com.zupacademy.propostas.cartao.CartaoRepository;
import com.zupacademy.propostas.cartao.bloqueio.ValidaRequisicao;
import com.zupacademy.propostas.commos.exceptions.NotFoundException;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/cartoes")
public class CadastraViagem {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ValidaRequisicao validaRequisicao;

    @Autowired
    private NotificaViagem notificaViagem;

    @Autowired
    private Tracer tracer;

    @PostMapping("/{id}/viagens")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void cadastraViagem(@PathVariable("id") Long id, @RequestBody @Valid ViagemRequest viagemRequest, HttpServletRequest request, @AuthenticationPrincipal Jwt jwt){
        Cartao cartao = cartaoRepository.findById(id).orElseThrow(() -> new NotFoundException("Cartão", "id", id));
        validaRequisicao.valida(request, jwt);
        Viagem viagem = viagemRequest.toModel(request.getRemoteAddr(), request.getHeader("User-Agent"), cartao);
        cartao.adicionaViagem(viagem, jwt.getClaim("documento"), notificaViagem);
        cartaoRepository.save(cartao);


        Span activeSpan = tracer.activeSpan();
        activeSpan.setBaggageItem("user.email", cartao.getProposta().getEmail());

    }
}
