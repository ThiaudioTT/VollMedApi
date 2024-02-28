package med.voll.api.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    public ResponseEntity<DetailPacienteDTO> create(@RequestBody @Valid PacienteRecord dados, UriComponentsBuilder uriBuilder) {
        Paciente paciente = new Paciente(dados);
        this.pacienteRepository.save(paciente);

        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailPacienteDTO(paciente));
    }

    // GET for paciente
    @GetMapping("/{id}")
    public ResponseEntity<DetailPacienteDTO> get(@PathVariable Long id) {
        Paciente paciente = this.pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente not found"));
        return ResponseEntity.ok(new DetailPacienteDTO(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemPacienteRecord>> list(Pageable pageable) {
        // pageable is an object that contains the page number,
        // the page size, the sort and the filter provided by the user of the API
        return ResponseEntity.ok(this.pacienteRepository.findAllByAtivoTrue(pageable).map(ListagemPacienteRecord::new));
    }

    // update for paciente
    @PutMapping
    @Transactional
    public ResponseEntity<DetailPacienteDTO> update(@RequestBody @Valid UpdatePacienteDTO paciente) {
//        System.out.println(paciente);
        Paciente pacienteToUpdate = this.pacienteRepository.findById(paciente.id())
                .orElseThrow(() -> new EntityNotFoundException("Paciente not found"));

        pacienteToUpdate.update(paciente);

        return ResponseEntity.ok(new DetailPacienteDTO(pacienteToUpdate));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Paciente pacienteToInactivate = this.pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente not found"));
        pacienteToInactivate.inactivate();
        this.pacienteRepository.save(pacienteToInactivate);

        return ResponseEntity.noContent().build();
    }
}
