package med.voll.api.domain.consultas;


import jakarta.validation.constraints.Future;
//import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record RequestConsultaDTO(

        // not blank is used with strings to validate if the string is not null and not empty
//        @NotBlank(message = "Nome do paciente é obrigatório") String nomePaciente,
//        @NotBlank(message = "Nome do médico é obrigatório") String nomeMedico,
        @NotNull(message = "Id do paciente é obrigatório") Long idPaciente,

        Long idMedico,

        @NotNull(message = "Data da consulta é obrigatória")
        @Future(message = "Data da consulta deve ser futura")
        LocalDateTime dataConsulta,

        Especialidade especialidade
        ) {

        public RequestConsultaDTO(Consulta consulta) {
                this(consulta.getPaciente().getId(), consulta.getMedico().getId(), consulta.getDataConsulta(), consulta.getMedico().getEspecialidade());
        }

}
