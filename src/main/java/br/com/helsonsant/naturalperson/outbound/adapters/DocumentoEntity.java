package br.com.helsonsant.naturalperson.outbound.adapters;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "documento")
@Setter
@Getter
public class DocumentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private BeneficiarioEntity beneficiario;
    private int tipoDocumento;
    private String Descricao;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAtualizacao;
}
