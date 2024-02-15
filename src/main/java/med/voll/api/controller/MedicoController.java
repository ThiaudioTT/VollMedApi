package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.ListagemMedicoRecord;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRecord;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<ListagemMedicoRecord> list() {
        return this.repository.findAll()// return all the medicos
                .stream().map(ListagemMedicoRecord::new).toList(); // map the medicos to the ListagemMedicoRecord
    }

}
