package com.zupacademy.propostas.cartao;

import com.zupacademy.propostas.cartao.biometria.Biometria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String numeroCartao;

    @NotBlank
    @Column(nullable = false)
    private String titular;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime emitidoEm;

    @NotNull
    @Column(nullable = false)
    private BigDecimal limite;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Biometria> biometrias = new ArrayList<>();

    @Deprecated
    public Cartao() {
    }

    public Cartao(String numeroCartao, String titular, LocalDateTime emitidoEm, BigDecimal limite) {
        this.numeroCartao = numeroCartao;
        this.titular = titular;
        this.emitidoEm = emitidoEm;
        this.limite = limite;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getTitular() {
        return titular;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void adicionaBiomatria(Biometria biometria) {
        this.biometrias.add(biometria);
    }
}