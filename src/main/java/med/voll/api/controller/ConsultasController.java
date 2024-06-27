package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.consultas.AgendaConsultasService;
import med.voll.api.domain.consultas.CancelamentoConsultaDTO;
import med.voll.api.domain.consultas.RequestConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultasController {

    @Autowired
    private AgendaConsultasService agenda;


    @PostMapping("/agendar")
    @Transactional
    public ResponseEntity<RequestConsultaDTO> agendarConsulta(@RequestBody @Valid RequestConsultaDTO consulta) {
        // A classe controller não deve trazer as regras de negócio da aplicação.

        // todo: retornar a URI e o ID da consulta
        return ResponseEntity.status(HttpStatus.CREATED).body(new RequestConsultaDTO(this.agenda.agendarConsulta(consulta)));
    }

    @PostMapping("/cancelar/{id}")
    @Transactional
    public ResponseEntity<CancelamentoConsultaDTO> cancelarConsulta(@PathVariable Long id, @RequestBody @NotBlank String motivo) {
        // we should not have business logic in the controller
        // wherever we have a business rule, we should have a service
        // there are exceptions, like validation, but this is not the case
//        return ResponseEntity.ok().body(new RequestConsultaDTO(this.agenda.cancelarConsulta(id, motivo)));\
        return ResponseEntity.ok().body(new CancelamentoConsultaDTO(this.agenda.cancelarConsulta(id, motivo)));
    }
}
