package med.voll.api.domain.consultas;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaRequestDTO(

        // not blank is used with strings to validate if the string is not null and not empty
        @NotBlank(message = "Nome do paciente é obrigatório") String nomePaciente,
        @NotBlank(message = "Nome do médico é obrigatório") String nomeMedico,

        @NotNull(message = "Data da consulta é obrigatória")
        @Future(message = "Data da consulta deve ser futura")
        LocalDateTime dataConsulta

        ) {
}
