package br.com.helsonsant.naturalperson.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Beneficiario {
    private long id;
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAtualizacao;

    @Getter(value= AccessLevel.NONE)
    private List<Documento> documentos;

    public List<Documento> getDocumentos() {
        if (this.documentos == null){
            this.documentos = new ArrayList<>();
        }
        return this.documentos;
    }
}
