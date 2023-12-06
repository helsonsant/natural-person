package br.com.helsonsant.naturalperson.domain.usecases;

import br.com.helsonsant.naturalperson.domain.model.Beneficiario;
import br.com.helsonsant.naturalperson.domain.model.Documento;
import br.com.helsonsant.naturalperson.inbound.ports.BeneficiarioPort;
import br.com.helsonsant.naturalperson.outbound.ports.BeneficiarioDataPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@Component
public class BeneficiarioUseCase implements BeneficiarioPort {
    private BeneficiarioDataPort dataPort;
    @Autowired
    public BeneficiarioUseCase(BeneficiarioDataPort dataPort){
        this.dataPort = dataPort;
    }
    @Override
    public Beneficiario save(Beneficiario beneficiario) {
        LocalDateTime now = LocalDateTime.now();
        beneficiario.setDataInclusao(now);
        beneficiario.setDataAtualizacao(now);
        for (Documento documento: beneficiario.getDocumentos()){
            documento.setBeneficiario(beneficiario);
            documento.setDataInclusao(now);
            documento.setDataAtualizacao(now);
        }
        return this.dataPort.save(beneficiario);
    }

    @Override
    public void delete(long id) {
        this.dataPort.delete(id);
    }

    @Override
    public Beneficiario get(long id){
        return this.dataPort.getById(id);
    }

    @Override
    public List<Beneficiario> get() {
        return this.dataPort.getBeneficiarios();
    }
}
