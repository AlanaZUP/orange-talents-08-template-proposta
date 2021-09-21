package com.zupacademy.propostas.proposta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    List<Proposta> findByDocumento(String documento);

    List<Proposta> findBystatusPropostaAndCartaoIsNull(StatusProposta statusProposta);
}
