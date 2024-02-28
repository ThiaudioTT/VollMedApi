package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.EnderecoRecord;

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
