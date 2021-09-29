package com.zupacademy.propostas.cartao.bloqueio;

import com.zupacademy.propostas.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cartao cartao;

    @NotBlank
    private String ip;

    @NotBlank
    private String userAgent;

    private LocalDateTime instante;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(Cartao cartao, String ip, String userAgent) {
        this.cartao = cartao;
        this.ip = ip;
        this.userAgent = userAgent;
        this.instante = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LocalDateTime getInstante() {
        return instante;
    }
}
