package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRecord;
import med.voll.api.paciente.PacienteRepository;
import med.voll.api.paciente.ListagemPacienteRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    public void create(@RequestBody @Valid PacienteRecord dados) {
        System.out.println(dados);
        this.pacienteRepository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<ListagemPacienteRecord> list(Pageable pageable) {
        // pageable is an object that contains the page number,
        // the page size, the sort and the filter provided by the user of the API
        return this.pacienteRepository.findAll(pageable).map(ListagemPacienteRecord::new);
    }
}
