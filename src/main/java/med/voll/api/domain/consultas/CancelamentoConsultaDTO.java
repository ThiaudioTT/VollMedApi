package med.voll.api.domain.consultas;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record CancelamentoConsultaDTO(

        @NotNull
        Long id,
        @NotEmpty
        String motivo,

        @NotNull
        StatusConsultaEnum status,


        @NotNull(message = "Id do paciente é obrigatório") Long idPaciente,
        Long idMedico,
        @NotNull(message = "Data da consulta é obrigatória")
        @Future(message = "Data da consulta deve ser futura")
        LocalDateTime dataConsulta,
        Especialidade especialidade
) {
        public CancelamentoConsultaDTO(Consulta consulta) {
                this(
                        consulta.getId(),
                        consulta.getMotivo_cancelamento(),
                        consulta.getStatusConsulta(),
                        consulta.getPaciente().getId(),
                        consulta.getMedico().getId(),
                        consulta.getDataConsulta(),
                        consulta.getMedico().getEspecialidade()
                );
        }
}
