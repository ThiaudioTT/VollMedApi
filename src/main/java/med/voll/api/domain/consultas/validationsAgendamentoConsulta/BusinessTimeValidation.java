package med.voll.api.domain.consultas.validationsAgendamentoConsulta;

import med.voll.api.domain.consultas.Consulta;
import med.voll.api.infra.exceptions.OffBusinessHoursException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

/**
 * Classe que valida se a consulta está sendo marcada em horário comercial
 */
@Component
public class BusinessTimeValidation implements AgendaConsultaStrategy {

    public final int START_HOUR = 7;
    public final int END_HOUR = 19;

    @Override
    public void validate(Consulta consulta) {
        if (consulta.getDataConsulta().getHour() < START_HOUR || consulta.getDataConsulta().getHour() > END_HOUR)
            throw new OffBusinessHoursException("Horário de atendimento: " + this.START_HOUR + " às " + this.END_HOUR);

        if(consulta.getDataConsulta().getDayOfWeek() == DayOfWeek.SUNDAY) throw new OffBusinessHoursException("Não atendemos aos domingos");

    }
}
