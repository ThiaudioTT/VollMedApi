package med.voll.api.controller;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultasController {


    @PostMapping("/agendar")
    @Transactional
    public void agendarConsulta() {

    }
}
