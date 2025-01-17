package com.zupacademy.propostas.cartao;

import com.zupacademy.propostas.proposta.Proposta;
import com.zupacademy.propostas.proposta.PropostaRepository;
import com.zupacademy.propostas.proposta.StatusProposta;
import feign.FeignException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ObservaCartao {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private SolicitaCartao solicitaCartao;

    @Scheduled(fixedDelayString = "${periodicidade.observa-cartao}")
    public void observa(){
        while (true){
            List<Proposta> propostas = propostaRepository.findTop100BystatusPropostaAndCartaoIsNull(StatusProposta.ELEGIVEL);

            if(propostas.isEmpty())
                return;

            propostas.forEach(proposta -> {
                SolicitaCartaoRequest cartaoRequest = new SolicitaCartaoRequest(proposta.getDocumento(), proposta.getNome(), proposta.getId());
                try{
                    SolicitaCartaoResponse cartaoResponse = solicitaCartao.solicita(cartaoRequest);
                    Cartao cartao = cartaoResponse.toModel(proposta);
                    proposta.adicionaCartao(cartao);
                    propostaRepository.save(proposta);
                    System.out.println("Consegui um cartao");
                }
                catch (FeignException feignException){
                    System.out.println(feignException);
                    System.out.println("Nao consegui um cartao");
                }
            });
        }
    }
}
