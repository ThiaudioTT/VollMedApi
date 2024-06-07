package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consultas.AgendaConsultasService;
import med.voll.api.domain.consultas.ConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultasController {

    @Autowired
    private AgendaConsultasService agenda;


    @PostMapping("/agendar")
    @Transactional
    public ResponseEntity<ConsultaDTO> agendarConsulta(@RequestBody @Valid ConsultaDTO consulta) {
        // A classe controller não deve trazer as regras de negócio da aplicação.

        // todo: retornar a URI
        return ResponseEntity.status(HttpStatus.CREATED).body(new ConsultaDTO(this.agenda.agendarConsulta(consulta)));
    }
}
