package com.zupacademy.propostas.cartao.bloqueio;

import com.zupacademy.propostas.commos.exceptions.ApiErroException;
import com.zupacademy.propostas.commos.validations.document.CpfOrCnpj;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

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
            throw new ApiErroException(HttpStatus.BAD_REQUEST, "documento", "O documento do usuário autenticado não foi encontrado");
        }
    }

    private void validaUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if(userAgent.isBlank()){
            throw new ApiErroException(HttpStatus.BAD_REQUEST, "userAgent", "O User-Agent da requisição não foi encontrado");
        }
    }

    private void validaIp(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        if(ip.isBlank()){
            throw new ApiErroException(HttpStatus.BAD_REQUEST, "IP", "O IP da requisição não foi encontrado");
        }
    }
}
