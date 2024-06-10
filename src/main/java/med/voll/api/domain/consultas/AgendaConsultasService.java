package med.voll.api.domain.consultas;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaConsultasService {

    @Autowired
    private ConsultaRepository consultaRepo;

    @Autowired
    private MedicoRepository medicoRepo;

    @Autowired
    private PacienteRepository pacienteRepo;

    // this is where the business logic should be
    public Consulta agendarConsulta(ConsultaDTO consulta) {
        // todo: see if IllegalArgumentException is the best exception to throw here
        if(consulta.idMedico() == null && consulta.especialidade() == null) throw new IllegalArgumentException("Médico ou especialidade é obrigatório");

        // using .orElseThrow() to throw an exception if the id is not found is better than using .get() who throws a NoSuchElementException
        // this way we can throw a custom exception and give a message to the user
        Medico medico = consulta.idMedico() == null ?
                this.chooseMedico(consulta) :
                this.medicoRepo.findById(consulta.idMedico())
                        .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado"));


        Consulta consultaAgendada = new Consulta(
                null,
                medico,
                this.pacienteRepo.findById(consulta.idPaciente())
                        .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado")),
                consulta.dataConsulta()
        );

        this.consultaRepo.save(consultaAgendada);

        // todo: it should return as well the id of the created entity
        return consultaAgendada;
    }

    private Medico chooseMedico(ConsultaDTO consulta) {
        if(consulta.especialidade() == null) throw new IllegalArgumentException("Especialidade é obrigatória");

        // todo: this is a dummy implementation, it should be replaced by a real implementation
        return new Medico();
    }
}
