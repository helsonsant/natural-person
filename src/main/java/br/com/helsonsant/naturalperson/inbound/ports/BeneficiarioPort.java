package br.com.helsonsant.naturalperson.inbound.ports;

import br.com.helsonsant.naturalperson.domain.model.Beneficiario;

import java.util.List;

public interface BeneficiarioPort {
    Beneficiario save(Beneficiario beneficiario);
    void delete(long id);
    Beneficiario get(long id);
    List<Beneficiario> get();
}
