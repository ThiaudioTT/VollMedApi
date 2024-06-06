package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consultas.AgendaConsultasService;
import med.voll.api.domain.consultas.ConsultaRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Void> agendarConsulta(@RequestBody @Valid ConsultaRequestDTO consulta) {
        // A classe controller não deve trazer as regras de negócio da aplicação.
        this.agenda.agendarConsulta(consulta);

        // todo: retornar a consulta criada
        return ResponseEntity.ok().build();
    }
}
