package br.com.helsonsant.naturalperson.outbound.adapters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BeneficiarioH2Repository extends JpaRepository<BeneficiarioEntity, Long> {
    Optional<BeneficiarioEntity> findOneById(Long id);
    List<BeneficiarioEntity> findAllByDeleted(boolean deleted);

    Optional<BeneficiarioEntity> findOneByIdAndDeleted(long id, boolean deleted);
}
