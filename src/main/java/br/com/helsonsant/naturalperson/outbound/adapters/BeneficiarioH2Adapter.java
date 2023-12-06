package br.com.helsonsant.naturalperson.outbound.adapters;

import br.com.helsonsant.naturalperson.domain.model.Beneficiario;
import br.com.helsonsant.naturalperson.inbound.ports.BeneficiarioPort;
import br.com.helsonsant.naturalperson.outbound.ports.BeneficiarioDataPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BeneficiarioH2Adapter implements BeneficiarioDataPort {
    private BeneficiarioH2Repository repository;
    private ModelMapper mapper;

    @Autowired
    public BeneficiarioH2Adapter(BeneficiarioH2Repository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public Beneficiario save(Beneficiario beneficiario) {
        BeneficiarioEntity entity = this.repository.save(this.convertToEntity(beneficiario, BeneficiarioEntity.class));
        beneficiario.setId(entity.getId());
        return beneficiario;
    }

    @Override
    public void delete(long id) {
        BeneficiarioEntity entity = this.repository.findOneById(id).orElseThrow(() -> new IllegalArgumentException("Banco não encontrado."));
        entity.setDeleted(true);
        this.repository.save(entity);
    }

    @Override
    public List<Beneficiario> getBeneficiarios() {
        List<BeneficiarioEntity> beneficiariosEntity = this.repository.findAllByDeleted(false);
        List<Beneficiario> beneficiarios = new ArrayList<>();
        for (BeneficiarioEntity entity: beneficiariosEntity){
            beneficiarios.add(this.convertToDomain(entity, Beneficiario.class));
        }
        return beneficiarios;
    }

    @Override
    public Beneficiario getById(long id) {
        BeneficiarioEntity entity = this.repository.findOneByIdAndDeleted(id, false).orElseThrow(() -> new IllegalArgumentException("Banco não encontrado."));
        Beneficiario beneficiario = this.convertToDomain(entity, Beneficiario.class);
        return beneficiario;
    }

    protected BeneficiarioEntity convertToEntity(final Beneficiario beneficiario, final Class<BeneficiarioEntity> entityClass) {
        BeneficiarioEntity entity = this.mapper.map(beneficiario, entityClass);
        return entity;
    }

    protected Beneficiario convertToDomain(final BeneficiarioEntity entity, final Class<Beneficiario> domainClass) {
        return mapper.map(entity, domainClass);
    }
}
