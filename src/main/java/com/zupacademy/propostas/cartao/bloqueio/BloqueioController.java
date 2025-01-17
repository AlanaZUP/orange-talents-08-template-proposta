package com.zupacademy.propostas.cartao.bloqueio;

import com.zupacademy.propostas.cartao.Cartao;
import com.zupacademy.propostas.cartao.CartaoRepository;
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

@RestController
@RequestMapping("/cartoes")
public class BloqueioController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ValidaRequisicao validaRequisicao;

    @Autowired
    private NotificaBloqueio notificaBloqueio;

    @Autowired
    private Tracer tracer;

    @PostMapping("/{id}/bloqueio")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void bloqueiaCartao(@PathVariable("id") Long idCartao, HttpServletRequest request, @AuthenticationPrincipal Jwt jwt){
        Cartao cartao = cartaoRepository.findById(idCartao).orElseThrow(() -> new NotFoundException("Cartao", "id", idCartao));

        validaRequisicao.valida(request, jwt);
        cartao.bloqueia(request, request.getRemoteAddr(), request.getHeader("User-Agent"), jwt.getClaim("documento"), notificaBloqueio);
        cartaoRepository.save(cartao);

        Span activeSpan = tracer.activeSpan();
        activeSpan.setBaggageItem("user.email", cartao.getProposta().getEmail());

    }
}
