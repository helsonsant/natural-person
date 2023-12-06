package br.com.helsonsant.naturalperson.inbound.ports;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class DocumentoDto {
    private Integer id;
    @NotBlank
    private int tipoDocumento;
    @NotBlank
    private String Descricao;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAtualizacao;
}
