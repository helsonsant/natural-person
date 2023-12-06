package br.com.helsonsant.naturalperson.controllers;

import br.com.helsonsant.naturalperson.domain.model.Beneficiario;
import br.com.helsonsant.naturalperson.inbound.ports.BeneficiarioDto;
import br.com.helsonsant.naturalperson.inbound.ports.BeneficiarioPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class NaturalPersonController {
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;
    private final BeneficiarioPort beneficiarios;

    @Autowired
    public NaturalPersonController(ObjectMapper objectMapper, ModelMapper modelMapper, BeneficiarioPort beneficiarios) {
        this.objectMapper = objectMapper;
        this.modelMapper = modelMapper;
        this.beneficiarios = beneficiarios;
    }
    @Operation(summary = "Cria um novo beneficiário")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Beneficiário criado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BeneficiarioDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos. Alguma informação obrigatória não foi informada ou está incorreta",
                    content = @Content)})
    @PostMapping(value = "/natural-person")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> addBeneficiario(@RequestBody BeneficiarioDto beneficiarioDto) throws JsonProcessingException {
        Beneficiario beneficiario = beneficiarios.save(this.convertToEntity(beneficiarioDto, Beneficiario.class));
        String result = objectMapper.writeValueAsString(this.convertToDTO(beneficiario, BeneficiarioDto.class));
        return new ResponseEntity<String>(result, HttpStatus.CREATED);
    }

    @Operation(summary = "Altera os dados de um beneficiário")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do Beneficiário salvos com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Beneficiario.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos. Alguma informação obrigatória não foi informada ou está incorreta",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Beneficiário informado não localizado para atualização",
                    content = @Content)})
    @PatchMapping(value = "/natural-person")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> saveBeneficiario(BeneficiarioDto beneficiarioDto) throws JsonProcessingException {
        Beneficiario beneficiario = beneficiarios.save(this.convertToEntity(beneficiarioDto, Beneficiario.class));
        String result = objectMapper.writeValueAsString(this.convertToDTO(beneficiario, BeneficiarioDto.class));
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @Operation(summary = "Recupera os dados de um beneficiário")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do Beneficiário recuperados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Beneficiario.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos. Alguma informação obrigatória não foi informada ou está incorreta",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Beneficiário informado não localizado para atualização",
                    content = @Content)})
    @GetMapping(value = "/natural-person/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> getBeneficiario(@PathVariable String id) throws JsonProcessingException {
        Beneficiario beneficiario = beneficiarios.get(Long.parseLong(id));
        String result = objectMapper.writeValueAsString(this.convertToDTO(beneficiario, BeneficiarioDto.class));
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @Operation(summary = "Recupera beneficiários cadastrados")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados dos Beneficiário recuperados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Beneficiario.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos. Alguma informação obrigatória não foi informada ou está incorreta",
                    content = @Content)})
    @GetMapping(value = "/natural-person")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> getBeneficiarios() throws JsonProcessingException {
        List<Beneficiario> beneficiarios = this.beneficiarios.get();
        List<BeneficiarioDto> beneficiariosResult = new ArrayList<>();
        for (Beneficiario beneficiario: beneficiarios){
            beneficiariosResult.add(this.convertToDTO(beneficiario, BeneficiarioDto.class));
        }
        String result = objectMapper.writeValueAsString(beneficiariosResult);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @Operation(summary = "Remove beneficiário cadastrado")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados dos Beneficiário removidos com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Beneficiario.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos. Alguma informação obrigatória não foi informada ou está incorreta",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Beneficiário informado não localizado para remoção",
                    content = @Content)})
    @DeleteMapping(value = "/natural-person/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> deleteBeneficiarios(@PathVariable String id) throws JsonProcessingException {
        beneficiarios.delete(Long.parseLong(id));
        return new ResponseEntity<String>("", HttpStatus.OK);
    }
    @GetMapping(value = "/health")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço saudável",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Beneficiario.class)) })})
    public ResponseEntity<String> getHealthStatus() {
        return new ResponseEntity<String>(new Gson().toJson(new Health()), HttpStatus.OK);
    }

    protected Beneficiario convertToEntity(final BeneficiarioDto dto, final Class<Beneficiario> entityClass) {
        Beneficiario entity = modelMapper.map(dto, entityClass);
        return entity;
    }

    protected BeneficiarioDto convertToDTO(final Beneficiario entity, final Class<BeneficiarioDto> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
}

