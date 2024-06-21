package med.voll.api.domain.consultas.validationsAgendamentoConsulta;

import med.voll.api.domain.consultas.Consulta;
import med.voll.api.domain.consultas.ConsultaRepository;
import med.voll.api.infra.exceptions.ConsultaConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe que valida se a consulta não conflita com outra consulta.
 * Se o medico já tiver uma consulta marcada no mesmo horário, não é possível agendar outra consulta.
 * Ou se o paciente já tiver uma consulta marcada no mesmo **DIA**, não é possível agendar outra consulta.
 */
@Component
public class NonConflictingScheduleValidator implements AgendaConsultaStrategy {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validate(Consulta consulta) {

        this.consultaRepository
                .findByMedicoIdAndDataConsulta(consulta.getMedico().getId(), consulta.getDataConsulta())
                .ifPresent(c -> {
                    throw new ConsultaConflictException("Já existe uma consulta marcada para este médico neste horário.");
                });

        this.consultaRepository
                .findByPacienteIdInADay(consulta.getPaciente().getId(), consulta.getDataConsulta().toLocalDate())
                .ifPresent(c -> {
                    throw new ConsultaConflictException("Já existe uma consulta marcada para este paciente neste dia.");
                });


    }
}
