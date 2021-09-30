package com.zupacademy.propostas.cartao.viagem;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "viagem", url = "${feign.url.cartao}")
public interface NotificaViagem {

    @PostMapping("/{id}/avisos")
    public NotificaViagemResponse notificaViagem(@PathVariable("id") String id, @RequestBody NotificaViagemRequest request);
}
