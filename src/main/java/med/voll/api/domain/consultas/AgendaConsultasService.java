package med.voll.api.domain.consultas;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.consultas.validationsAgendamentoConsulta.AgendaConsultaStrategy;
import med.voll.api.domain.consultas.validationsCancelamentoConsulta.CancelaConsultaStrategy;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exceptions.InactiveEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaConsultasService {

    @Autowired
    private ConsultaRepository consultaRepo;

    @Autowired
    private MedicoRepository medicoRepo;

    @Autowired
    private PacienteRepository pacienteRepo;

    @Autowired
    private List<AgendaConsultaStrategy> agendaConsultaValidations;

    @Autowired
    private List<CancelaConsultaStrategy> cancelaConsultaValidations;

    // this is where the business logic should be
    public Consulta agendarConsulta(RequestConsultaDTO consulta) {
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
                consulta.dataConsulta(),
                StatusConsultaEnum.AGENDADA,
                null
        );

        // before saving the consultation, we need to validate it
        this.agendaConsultaValidations.forEach(v -> v.validate(consultaAgendada));

        return this.consultaRepo.save(consultaAgendada);
    }

    /**
     * Cancela uma consulta
     * @param id
     * @param motivo
     * @return Consulta cancelada
     */
    public Consulta cancelarConsulta(Long id, String motivo) {
        Consulta consultaCancelar = this.consultaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada"));

        if(consultaCancelar.getStatusConsulta() == StatusConsultaEnum.CANCELADA) throw new InactiveEntityException("Consulta já cancelada");

        this.cancelaConsultaValidations.forEach(v -> v.validate(consultaCancelar));

        consultaCancelar.cancelar(motivo);
        return this.consultaRepo.save(consultaCancelar);
    }

    // randomly chooses a doctor based on the specialization
    private Medico chooseMedico(RequestConsultaDTO consulta) {
        // todo: see if IllegalArgumentException is the best exception to throw here
        if(consulta.especialidade() == null) throw new IllegalArgumentException("Especialidade é obrigatória");

        return this.medicoRepo.findRandomByEspecialidade(consulta.especialidade(), consulta.dataConsulta())
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado ou sem horário disponível"));
    }
}
