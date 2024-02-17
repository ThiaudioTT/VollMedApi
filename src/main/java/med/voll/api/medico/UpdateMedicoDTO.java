package med.voll.api.medico;

import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.EnderecoRecord;

// DTO for updating a medico
public record UpdateMedicoDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        @Valid
        EnderecoRecord endereco
) {
}
