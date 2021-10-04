package com.zupacademy.propostas.proposta.cadastra;

import com.zupacademy.propostas.commos.exceptions.ApiErroException;
import com.zupacademy.propostas.commos.seguranca.DadosCrypto;
import com.zupacademy.propostas.commos.validations.document.CpfOrCnpj;
import com.zupacademy.propostas.proposta.Proposta;
import com.zupacademy.propostas.proposta.PropostaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

public class PropostaRequest {

    @NotBlank @CpfOrCnpj
    private String documento;
    @NotBlank
    private String nome;
    @NotBlank @Email
    private String email;
    @NotBlank
    private String endereco;
    @NotNull @Positive
    private Double salario;

    public PropostaRequest(String documento, String nome, String email, String endereco, Double salario) {
        this.documento = documento;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel(PropostaRepository propostaRepository){
        DadosCrypto dadosCrypto = new DadosCrypto();
        String documentoCrypto = dadosCrypto.encrytor(documento);
        List<Proposta> propostas = propostaRepository.findByDocumento(documentoCrypto);

        if (propostas.size() == 1){
            throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "documento", "Já existe um documento com esse valor.");
        }
        Assert.isTrue(propostas.size() == 0, "Bug geral, existe mais de uma proposta com esse documento :o !!");

        return new Proposta(documentoCrypto, nome, email, endereco, salario);
    }
}
