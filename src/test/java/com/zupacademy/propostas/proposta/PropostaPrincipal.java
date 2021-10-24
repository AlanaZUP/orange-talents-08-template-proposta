package com.zupacademy.propostas.proposta;

import com.zupacademy.propostas.TestPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PropostaPrincipal extends TestPrincipal {

    @Autowired
    protected PropostaRepository propostaRepository;
}
