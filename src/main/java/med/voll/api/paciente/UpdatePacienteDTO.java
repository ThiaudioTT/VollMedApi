package med.voll.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.EnderecoRecord;

public record UpdatePacienteDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        @Valid
        EnderecoRecord endereco
) {
}
