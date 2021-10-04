package com.zupacademy.propostas.proposta;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zupacademy.propostas.cartao.Cartao;
import com.zupacademy.propostas.commos.validations.document.CpfOrCnpj;
import com.zupacademy.propostas.proposta.cadastra.analisaSolicitacao.AnalisaSolicitacao;
import com.zupacademy.propostas.proposta.cadastra.analisaSolicitacao.AnalisaSolicitacaoRequest;
import com.zupacademy.propostas.proposta.cadastra.analisaSolicitacao.AnalisaSolicitacaoResponse;
import com.zupacademy.propostas.proposta.cadastra.analisaSolicitacao.StatusSolicitacao;
import feign.FeignException;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Proposta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Column(unique = true)
    private String documento;
    @NotBlank
    private String nome;
    @NotBlank @Email
    private String email;
    @NotBlank
    private String endereco;
    @NotNull @Positive
    private Double salario;
    @OneToOne(cascade = CascadeType.ALL)
    private Cartao cartao;

    private StatusProposta statusProposta;

    @Deprecated
    public Proposta() {
    }

    /**
     * @param documento -> valor já deve estar encriptografado
     * @param nome
     * @param email
     * @param endereco
     * @param salario
     * */
    public Proposta(@NotBlank String documento, @NotBlank String nome, @NotBlank @Email String email, @NotBlank String endereco, @NotNull @Positive Double salario) {
        this.documento = documento;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public Double getSalario() {
        return salario;
    }

    public void analisaSolicitacao(AnalisaSolicitacao analisaSolicitacao) throws JsonProcessingException {
        AnalisaSolicitacaoRequest solicitacaoRequest = new AnalisaSolicitacaoRequest(this.documento, this.nome, this.id);
        AnalisaSolicitacaoResponse solicitacaoResponse;
        try{
            solicitacaoResponse = analisaSolicitacao.consultar(solicitacaoRequest);
        }
        catch (FeignException feignException){
            int status = feignException.status();
            if(HttpStatus.UNPROCESSABLE_ENTITY.value() == feignException.status()){
                ObjectMapper objectMapper = new ObjectMapper();
                solicitacaoResponse = objectMapper.readValue(feignException.contentUTF8(), AnalisaSolicitacaoResponse.class);
            }
            else{
                throw feignException;
            }
        }
        this.statusProposta = solicitacaoResponse.getStatusProposta();
    }

    public void adicionaCartao(Cartao cartao) {
        this.cartao=cartao;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }
}
