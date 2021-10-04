package com.zupacademy.propostas.commos.seguranca;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;


public class DadosCrypto {

    private TextEncryptor textEncryptor = Encryptors.queryableText("criptografia","A307F432A855C3122522");

    /**
     * @param dado -> dado com valor real que será encriptografado
     * */
    public String encrytor(String dado){
        return textEncryptor.encrypt(dado);
    }


    /**
     * @param dadoEncrypt -> dado encriptografado que se deseja comparar
     * @param dado -> dado com o valor real decriptografado
    * */
    public boolean equals(String dadoEncrypt, String dado){
        dado = encrytor(dado);
        return dado.equals(dadoEncrypt);
    }
}
