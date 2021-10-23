package com.zupacademy.propostas.proposta;

import org.hibernate.LockOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    List<Proposta> findByDocumento(String documento);

    @QueryHints({
            @QueryHint(
                    name = "javax.persistence.lock.timeout",
                    value = LockOptions.SKIP_LOCKED + ""
            )
    })
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Proposta> findTop100BystatusPropostaAndCartaoIsNull(StatusProposta statusProposta);
}
