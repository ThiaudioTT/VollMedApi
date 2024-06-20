package med.voll.api.domain.consultas.validationsAgendamentoConsulta;

import med.voll.api.domain.consultas.Consulta;
import med.voll.api.infra.exceptions.InactiveEntityException;
import org.springframework.stereotype.Component;


/**
 * Classe que valida se a entidade está inativa, medico ou paciente
 */
@Component
public class InactiveEntityValidator implements AgendaConsultaStrategy {

    @Override
    public void validate(Consulta consulta) {
        if (!consulta.getMedico().isAtivo() || !consulta.getPaciente().isAtivo())
            throw new InactiveEntityException("Medico ou Paciente inativo, não é possível agendar consulta.");
    }
}
