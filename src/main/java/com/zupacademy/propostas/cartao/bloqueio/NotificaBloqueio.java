package com.zupacademy.propostas.cartao.bloqueio;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bloqueia", url = "${feign.url.cartao}")
public interface NotificaBloqueio {

    @PostMapping("/{id}/bloqueios")
    public NotificaResponse notifica(@PathVariable String id, @RequestBody NotificaRequest request);
}
