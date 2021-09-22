package com.zupacademy.propostas.cartao.biometria;

import com.zupacademy.propostas.cartao.Cartao;
import com.zupacademy.propostas.commos.validations.isBase64.IsBase64;

import javax.validation.constraints.NotBlank;

public class BiometriaRequest {

    @NotBlank @IsBase64
    private String fingerprint;

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Biometria toModel(Cartao cartao) {
        return new Biometria(this.fingerprint, cartao);
    }
}
