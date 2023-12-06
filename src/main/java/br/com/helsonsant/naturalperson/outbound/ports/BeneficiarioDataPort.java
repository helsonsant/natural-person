package br.com.helsonsant.naturalperson.outbound.ports;

import br.com.helsonsant.naturalperson.domain.model.Beneficiario;
import br.com.helsonsant.naturalperson.inbound.ports.BeneficiarioPort;
import br.com.helsonsant.naturalperson.outbound.adapters.BeneficiarioEntity;

import java.util.List;

public interface BeneficiarioDataPort {
    Beneficiario save(Beneficiario beneficiario);
    void delete(long id);
    List<Beneficiario> getBeneficiarios();
    Beneficiario getById(long id);
}
