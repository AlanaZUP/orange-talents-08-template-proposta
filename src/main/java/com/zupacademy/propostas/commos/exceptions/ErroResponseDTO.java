package com.orangetalents.mercadolivre.comms.exceptions;


public class ErroResponseDTO {
    private String campo;
    private String erro;

    public ErroResponseDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}