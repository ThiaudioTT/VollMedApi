package med.voll.api.paciente;

import med.voll.api.endereco.EnderecoRecord;

public record PacienteRecord(
        String nome,
        String email,
        String telefone,
        String cpf,
        EnderecoRecord endereco
) { }
