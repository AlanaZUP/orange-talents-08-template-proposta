package com.zupacademy.propostas.cartao.bloqueio;

import com.zupacademy.propostas.cartao.Cartao;
import com.zupacademy.propostas.cartao.CartaoRepository;
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

    @PostMapping("/{id}/bloqueio")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void bloqueiaCartao(@PathVariable("id") Long idCartao, HttpServletRequest request, @AuthenticationPrincipal Jwt jwt){
        Cartao cartao = cartaoRepository.findById(idCartao).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe cartão com a identificação informada"));
        cartao.bloqueia(request, jwt);
        cartaoRepository.save(cartao);
    }
}
