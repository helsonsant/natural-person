package br.com.helsonsant.naturalperson.outbound.adapters;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "beneficiario")
public class BeneficiarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAtualizacao;
    private boolean deleted;
    @Getter(value= AccessLevel.NONE)

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentoEntity> documentos;

    public List<DocumentoEntity> getDocumentos() {
        if (this.documentos == null){
            this.documentos = new ArrayList<>();
        }
        return this.documentos;
    }
}
