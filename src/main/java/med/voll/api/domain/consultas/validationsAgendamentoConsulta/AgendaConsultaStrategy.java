package med.voll.api.domain.consultas.validationsAgendamentoConsulta;

import med.voll.api.domain.consultas.Consulta;

public interface AgendaConsultaStrategy {

    /**
     * Valida a consulta usando strategy pattern
     * @param consulta
     */
    void validate(Consulta consulta);
}
