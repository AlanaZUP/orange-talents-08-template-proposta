package com.zupacademy.propostas.cartao.carteira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "carteira", url = "${feign.url.cartao}")
public interface NotificaCarteira {

    @PostMapping("/{id}/carteiras")
    public NotificaCarteiraResponse notifica (@PathVariable String id, @RequestBody NotificaCarteiraRequest carteiraRequest);
}
