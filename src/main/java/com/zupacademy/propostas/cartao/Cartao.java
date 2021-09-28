package com.zupacademy.propostas.cartao;

import com.zupacademy.propostas.cartao.biometria.Biometria;
import com.zupacademy.propostas.cartao.bloqueio.Bloqueio;
import com.zupacademy.propostas.commos.exceptions.ApiErroException;
import com.zupacademy.propostas.proposta.Proposta;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
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

    private StatusCartao statusCartao;

    @OneToOne
    @NotNull
    private Proposta proposta;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Biometria> biometrias = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Bloqueio> bloqueios = new ArrayList<>();

    @Deprecated
    public Cartao() {
    }

    public Cartao(Proposta proposta, String numeroCartao, String titular, LocalDateTime emitidoEm, BigDecimal limite) {
        this.proposta = proposta;
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

    public void bloqueia(HttpServletRequest request, Jwt jwt) {
        if(statusCartao.equals(StatusCartao.BLOQUEADO)){
            throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "statusCartao", "Cartão já está bloqueado");
        }
        String documento = jwt.getClaim("documento");
        if(!this.proposta.getDocumento().equals(documento)){
            throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "", "Cartão não pertence ao usuário logado");
        }

        String IP = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        if(IP.isBlank()){
            throw new ApiErroException(HttpStatus.BAD_REQUEST, "IP", "O IP da requisição não foi encontrado");
        }
        if(userAgent.isBlank()){
            throw new ApiErroException(HttpStatus.BAD_REQUEST, "userAgent", "O User-Agent da requisição não foi encontrado");
        }

        Bloqueio bloqueio = new Bloqueio(this, IP, userAgent);

    }
}