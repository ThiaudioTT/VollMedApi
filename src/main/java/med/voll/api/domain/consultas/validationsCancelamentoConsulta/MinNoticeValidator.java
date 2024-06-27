package med.voll.api.domain.consultas.validationsCancelamentoConsulta;

import med.voll.api.domain.consultas.Consulta;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MinNoticeValidator implements CancelaConsultaStrategy {

    public static final int MIN_NOTICE = 24;

    /**
     * Valida se a consulta foi cancelada com antecedência mínima de 1 hora
     * @param consulta
     */
    @Override
    public void validate(Consulta consulta) {
        if (consulta.getDataConsulta().isBefore(LocalDateTime.now().plusHours(MIN_NOTICE)))
            throw new IllegalArgumentException("A consulta deve ser cancelada com antecedência mínima de " + MIN_NOTICE + " horas");
    }
}
