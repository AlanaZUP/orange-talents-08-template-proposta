package com.zupacademy.propostas.cartao.bloqueio;

import com.zupacademy.propostas.commos.exceptions.RegraDeNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ValidaRequisicao {


    public void valida(HttpServletRequest request, Jwt jwt) {
        validaIp(request);
        validaUserAgent(request);
        validaJwtDocumento(jwt);
    }

    private void validaJwtDocumento(Jwt jwt) {
        String documento = jwt.getClaim("documento");
        if(documento.isBlank()){
            throw new RegraDeNegocioException("documento", "Não foi recebido o documento na autenticação", "");
        }
    }

    private void validaUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if(userAgent.isBlank()){
            throw new RegraDeNegocioException("User-Agent", "Não foi recebido o User-Agent da requisição", "");
        }
    }

    private void validaIp(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        if(ip.isBlank()){
            throw new RegraDeNegocioException("IP", "Não foi recebido o IP da requisição", "");
        }
    }
}
