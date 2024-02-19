package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.*;
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
        return this.pacienteRepository.findAllByAtivoTrue(pageable).map(ListagemPacienteRecord::new);
    }

    // update for paciente
    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdatePacienteDTO paciente) {
        System.out.println(paciente);
        Paciente pacienteToUpdate = this.pacienteRepository.findById(paciente.id())
                .orElseThrow(() -> new RuntimeException("Paciente not found"));

        pacienteToUpdate.update(paciente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        Paciente pacienteToInactivate = this.pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente not found"));
        pacienteToInactivate.inactivate();
        this.pacienteRepository.save(pacienteToInactivate);
    }
}
