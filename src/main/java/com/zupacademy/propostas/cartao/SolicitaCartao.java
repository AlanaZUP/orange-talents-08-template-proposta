package com.zupacademy.propostas.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "cartao", url = "${feign.url.cartao}")
public interface SolicitaCartao {

    @PostMapping
    public SolicitaCartaoResponse solicita(@RequestBody @Valid SolicitaCartaoRequest cartaoRequest);
}
