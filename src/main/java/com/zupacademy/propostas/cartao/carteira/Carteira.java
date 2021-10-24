package com.zupacademy.propostas.cartao.carteira;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zupacademy.propostas.cartao.Cartao;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;
    @Email @NotBlank @Column(nullable = false)
    private String email;
    @NotNull @Column(nullable = false) @Enumerated(EnumType.STRING)
    private EnumCarteiras carteira;
    @NotBlank @Column(nullable = false)
    private String idCarteira;
    @ManyToOne
    @JsonIgnore
    private Cartao cartao;

    @Deprecated
    public Carteira() {
    }

    public Carteira(@Email @NotBlank String email, @NotBlank EnumCarteiras carteira, Cartao cartao) {
        this.email = email;
        this.carteira = carteira;
        this.cartao = cartao;
    }

    public void setIdCarteira(String idCarteira) {
        this.idCarteira = idCarteira;
    }

    public String getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }

    public EnumCarteiras getCarteira() {
        return carteira;
    }

    public String getIdCarteira() {
        return idCarteira;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
