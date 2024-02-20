package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository; // inject the repository

    @PostMapping // type http post
    @Transactional // open a transaction to save the data
    public void create(@RequestBody @Valid MedicoRecord dados) { // @Validate will use bean validation and will validate the object
//        System.out.println(dados);
        repository.save(new Medico(dados)); // create a new Medico object and save it to the database
    }


    @GetMapping
    public Page<ListagemMedicoRecord> list(@PageableDefault(size = 10) Pageable pageable) {
        // with @pageableDefault we can set the default page size
        // when using pagination, we don't return a list, we return a page
        return this.repository.findAllByAtivoTrue(pageable)// return all the medicos
                .map(ListagemMedicoRecord::new); // map the medicos to the ListagemMedicoRecord
    }

    @PutMapping
//    @PutMapping("/{id}") // we can use this to get the id from the path and in the code use @PathVariable("id") Long id
    @Transactional // for every update we need to open a transaction
    public void update(@RequestBody @Valid UpdateMedicoDTO medico) {
        // we need to find the medico by id
        Medico medicoToUpdate = this.repository.findById(medico.id())
                .orElseThrow(() -> new RuntimeException("Medico not found")); // if the medico is not found, throw an exception


        // update the medico
        medicoToUpdate.update(medico); // JPA will automatically update the medico in the database because we are inside a transaction
    }

    @DeleteMapping("/{id}") // Dynamic path
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) { // this inactivates the medico
        // this.repository.deleteById(id); // delete the medico by id

        Medico medicoToInactivate = this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medico not found")); // if the medico is not found, throw an exception
        medicoToInactivate.inactivate(); // inactivate the medico
        this.repository.save(medicoToInactivate); // save the medico

        return ResponseEntity.noContent().build(); // return a 204 no content
        // we can return void, but it's better to return a response entity
    }

}
