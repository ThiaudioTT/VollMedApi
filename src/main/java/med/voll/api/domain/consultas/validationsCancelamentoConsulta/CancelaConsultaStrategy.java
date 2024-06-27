package med.voll.api.domain.consultas.validationsCancelamentoConsulta;

import med.voll.api.domain.consultas.Consulta;

public interface CancelaConsultaStrategy {

    /**
     * Valida a consulta usando strategy pattern
     * @param consulta
     */
    void validate(Consulta consulta);
}
