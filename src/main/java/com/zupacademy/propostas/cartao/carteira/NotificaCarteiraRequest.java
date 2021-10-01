package com.zupacademy.propostas.cartao.carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NotificaCarteiraRequest {

    @Email @NotBlank
    private String email;

    @NotBlank
    private EnumCarteiras carteira;

    public NotificaCarteiraRequest(String email, EnumCarteiras carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public EnumCarteiras getCarteira() {
        return carteira;
    }
}
