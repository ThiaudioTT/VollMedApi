package med.voll.api.domain.consultas;

import jakarta.persistence.EntityNotFoundException;
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

        // using .orElseThrow() to throw an exception if the id is not found is better than using .get() who throws a NoSuchElementException
        // this way we can throw a custom exception and give a message to the user
        Consulta consultaAgendada = new Consulta(
                null,
                this.medicoRepo.findById(consulta.idMedico())
                        .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado")),
                this.pacienteRepo.findById(consulta.idPaciente())
                        .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado")),
                consulta.dataConsulta()
        );

        this.consultaRepo.save(consultaAgendada);

        return consultaAgendada;
    }
}
