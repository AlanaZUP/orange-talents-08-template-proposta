package com.zupacademy.propostas.cartao.biometria;

import com.zupacademy.propostas.cartao.Cartao;
import com.zupacademy.propostas.commos.validations.isBase64.IsBase64;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;

    @NotBlank @Lob @IsBase64
    private String fingerprint;

    private LocalDateTime instanteCriacao;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria() {
    }

    public Biometria(String fingerprint, Cartao cartao) {
        this.fingerprint = fingerprint;
        this.instanteCriacao = LocalDateTime.now();
        this.cartao = cartao;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
