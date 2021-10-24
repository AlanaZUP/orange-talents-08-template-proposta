package com.zupacademy.propostas.proposta.cadastra;

import com.zupacademy.propostas.TestPrincipal;
import com.zupacademy.propostas.proposta.Proposta;
import com.zupacademy.propostas.proposta.PropostaPrincipal;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CadastraPropostaTest extends PropostaPrincipal {

    @Test
    void deveCadastrarUmaProposta() throws Exception {
        PropostaRequest propostaRequest = new PropostaRequest("03498680188", "Alana", "alana@teste.com", "Endereço teste", new BigDecimal(5000.0));

        mockMvc.perform(post("/propostas")
                        .contentType("application/json")
                        .content(gson.toJson(propostaRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"))
                .andExpect(redirectedUrlPattern("http://localhost/propostas/{id}"));

    }

    @Test
    void naoDeveCadastrarUmaPropostaVazia() throws Exception {
        PropostaRequest propostaRequest = new PropostaRequest("", "", "", "", new BigDecimal(0));

        mockMvc.perform(post("/propostas")
                        .contentType("application/json")
                        .content(gson.toJson(propostaRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


}