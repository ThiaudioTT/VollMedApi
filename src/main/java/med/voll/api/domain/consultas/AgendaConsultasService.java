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


        return this.consultaRepo.save(consultaAgendada);
    }

    // randomly chooses a doctor based on the specialization
    // todo: fix this method, it is not working as intended
    private Medico chooseMedico(ConsultaDTO consulta) {
        // todo: see if IllegalArgumentException is the best exception to throw here
        if(consulta.especialidade() == null) throw new IllegalArgumentException("Especialidade é obrigatória");

        return this.medicoRepo.findRandomByEspecialidade(consulta.especialidade(), consulta.dataConsulta())
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado"));
    }
}
