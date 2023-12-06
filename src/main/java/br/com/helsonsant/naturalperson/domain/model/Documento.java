package br.com.helsonsant.naturalperson.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Documento {
    private long id;
    private Beneficiario beneficiario;
    private int tipoDocumento;
    private String Descricao;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAtualizacao;
}
