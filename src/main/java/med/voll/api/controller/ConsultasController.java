package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consultas.ConsultaRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultasController {


    @PostMapping("/agendar")
    @Transactional
    public ResponseEntity<Void> agendarConsulta(@RequestBody @Valid ConsultaRequestDTO consulta) {
        System.out.println("Agendando consulta: " + consulta.toString());

        return ResponseEntity.ok().build();
    }
}
