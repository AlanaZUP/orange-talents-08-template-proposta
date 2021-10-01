package com.zupacademy.propostas.cartao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zupacademy.propostas.cartao.biometria.Biometria;
import com.zupacademy.propostas.cartao.bloqueio.*;
import com.zupacademy.propostas.cartao.carteira.*;
import com.zupacademy.propostas.cartao.viagem.NotificaViagem;
import com.zupacademy.propostas.cartao.viagem.NotificaViagemRequest;
import com.zupacademy.propostas.cartao.viagem.NotificaViagemResponse;
import com.zupacademy.propostas.cartao.viagem.Viagem;
import com.zupacademy.propostas.commos.exceptions.ApiErroException;
import com.zupacademy.propostas.proposta.Proposta;
import feign.FeignException;
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
import java.util.stream.Collectors;

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
    @JsonIgnore
    private Proposta proposta;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Biometria> biometrias = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Bloqueio> bloqueios = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Viagem> viagens = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Carteira> carteiras = new ArrayList<>();

    @Deprecated
    public Cartao() {
    }

    public Cartao(Proposta proposta, String numeroCartao, String titular, LocalDateTime emitidoEm, BigDecimal limite) {
        this.proposta = proposta;
        this.numeroCartao = numeroCartao;
        this.titular = titular;
        this.emitidoEm = emitidoEm;
        this.limite = limite;
        this.statusCartao = StatusCartao.ATIVO;
    }

    public StatusCartao getStatusCartao() {
        return statusCartao;
    }

    public List<Biometria> getBiometrias() {
        return biometrias;
    }

    public List<Bloqueio> getBloqueios() {
        return bloqueios;
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

    public List<Viagem> getViagens() {
        return viagens;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void adicionaBiomatria(Biometria biometria) {
        this.biometrias.add(biometria);
    }

    public void bloqueia(HttpServletRequest request, String ip, String userAgent, String documento, NotificaBloqueio notificaBloqueio) {
        cartaoPertenceAoRequisitante(documento);
        cartaoBloqueado();
        try{
            NotificaRequest notificaRequest = new NotificaRequest("proposta");
            NotificaResponse notificaResponse = notificaBloqueio.notifica(this.numeroCartao, notificaRequest);

            Bloqueio bloqueio = new Bloqueio(this, ip, userAgent);
            adicionaBloqueio(bloqueio);
            statusCartao = StatusCartao.BLOQUEADO;
        }
        catch (FeignException feignException){
            System.out.println(feignException);
            throw new ApiErroException(HttpStatus.INTERNAL_SERVER_ERROR, "", "Não foi possível notificar sistema legado banco");
        }
    }

    private void adicionaBloqueio(Bloqueio bloqueio) {
        this.bloqueios.add(bloqueio);
    }

    private void cartaoPertenceAoRequisitante(String documento) {
        if(!this.proposta.getDocumento().equals(documento)){
            throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "documento", "Cartão não pertence ao usuário logado");
        }
    }

    private void cartaoBloqueado() {
        if(statusCartao.equals(StatusCartao.BLOQUEADO)){
            throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "statusCartao", "Cartão já está bloqueado");
        }
    }

    public void adicionaViagem(Viagem viagem, String documento, NotificaViagem notificaViagem) {
        cartaoPertenceAoRequisitante(documento);
        NotificaViagemRequest notificaViagemRequest = new NotificaViagemRequest(viagem.getDestinoViagem(), viagem.getDateTermino());
        try{
            NotificaViagemResponse notificaViagemResponse = notificaViagem.notificaViagem(this.numeroCartao, notificaViagemRequest);
            viagens.add(viagem);
        }
        catch (FeignException feignException){
            throw new ApiErroException(HttpStatus.INTERNAL_SERVER_ERROR,"", "Não foi possível notificar a viagem ao sistema legado, tente novamente mais tarde");
        }
    }

    public void adicionarCarteira(Carteira carteira, NotificaCarteira notificaCarteira) {

        if(possuiCarteira(carteira.getCarteira())){
            throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "carteira", "Já existe essa carteira nesse cartão");
        }

        NotificaCarteiraRequest notificaCarteiraRequest = new NotificaCarteiraRequest(carteira.getEmail(), carteira.getCarteira());
        try{
            NotificaCarteiraResponse notificaCarteiraResponse = notificaCarteira.notifica(this.numeroCartao, notificaCarteiraRequest);
            carteira.setIdCarteira(notificaCarteiraResponse.getId());
            this.addCarteiras(carteira);
        }
        catch (FeignException feignException){
            System.out.println(feignException);
            throw new ApiErroException(HttpStatus.INTERNAL_SERVER_ERROR,"" ,"Não foi possível notificar o sistema legado, tente novamente mais tarde");
        }
    }

    public boolean possuiCarteira(EnumCarteiras enumCarteiras){
        return this.carteiras.stream()
                .filter(carteira -> carteira.getCarteira().equals(enumCarteiras))
                .collect(Collectors.toList())
                .size() > 0;
    }

    private void addCarteiras(Carteira carteira) {
        this.carteiras.add(carteira);
    }

    public List<Carteira> getCarteiras() {
        return carteiras;
    }
}