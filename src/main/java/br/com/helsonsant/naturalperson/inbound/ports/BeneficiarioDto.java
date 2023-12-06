package br.com.helsonsant.naturalperson.inbound.ports;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BeneficiarioDto {
    private Integer id;
    @NotBlank
    @Size(min = 10, max = 40)
    private String nome;
    @NotBlank
    @Size(min = 10, max = 11)
    private String telefone;
    @NotBlank
    private LocalDate dataNascimento;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAtualizacao;

    @Getter(value= AccessLevel.NONE)
    private List<DocumentoDto> documentos;

    public List<DocumentoDto> getDocumentos() {
        if (this.documentos == null){
            this.documentos = new ArrayList<>();
        }
        return this.documentos;
    }
}
