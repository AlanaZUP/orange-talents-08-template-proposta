package com.zupacademy.propostas.commos.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogExample {
    private final Logger logger = LoggerFactory.getLogger(LogExample.class);

    public void log(){
        logger.info("Log de informação");
        logger.warn("Log de aviso, algo está errado ou faltando! cuidado!");
        logger.error("Log de erro, algo de errado aconteceu!");
        logger.debug("Log de depuração, contém informações mais refinadas, que são mais úteis para depurar um aplicativo");
        logger.trace("Log de rastreabilidade, contém informações mais refinadas do que o DEBUG");
    }
}
