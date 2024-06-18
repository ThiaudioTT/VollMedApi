package med.voll.api.domain.consultas.validationsAgendamentoConsulta;

import med.voll.api.domain.consultas.Consulta;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * As consultas devem ser agendadas com antecedência mínima de 30 minutos
 */
@Component
public class AppointmentAdvanceValidator implements AgendaConsultaStrategy {

    @Override
    public void validate(Consulta consulta) {
        if (consulta.getDataConsulta().isBefore(LocalDateTime.now().plusMinutes(30)))
            throw new IllegalArgumentException("A consulta deve ser agendada com antecedência mínima de 30 minutos");
    }
}
