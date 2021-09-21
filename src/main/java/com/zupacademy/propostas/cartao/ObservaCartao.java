package com.zupacademy.propostas.teste;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Test {

    @Scheduled(fixedDelay = 5000)
    public void teste(){
        System.out.println("Estou sendo mostrado de 5 em 5 segundos");
    }
}
