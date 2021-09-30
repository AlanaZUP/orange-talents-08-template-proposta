package com.zupacademy.propostas.cartao.viagem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zupacademy.propostas.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String destinoViagem;

    @NotBlank
    @Column(nullable = false)
    private String ipRequisicao;

    @NotBlank
    @Column(nullable = false)
    private String userAgenteRequisicao;

    @NotNull @FutureOrPresent
    @Column(nullable = false)
    private LocalDate dateTermino;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime instanteAviso;

    @ManyToOne
    @JsonIgnore
    private Cartao cartao;

    @Deprecated
    public Viagem() {
    }

    public Viagem(String destinoViagem, String ipRequisicao, String userAgenteRequisicao, LocalDate dateTermino, Cartao cartao) {
        this.destinoViagem = destinoViagem;
        this.ipRequisicao = ipRequisicao;
        this.userAgenteRequisicao = userAgenteRequisicao;
        this.dateTermino = dateTermino;
        this.instanteAviso = LocalDateTime.now();
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public String getIpRequisicao() {
        return ipRequisicao;
    }

    public String getUserAgenteRequisicao() {
        return userAgenteRequisicao;
    }

    public LocalDate getDateTermino() {
        return dateTermino;
    }

    public LocalDateTime getInstanteAviso() {
        return instanteAviso;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
