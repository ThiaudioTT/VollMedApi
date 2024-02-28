package med.voll.api.paciente;

import med.voll.api.endereco.Endereco;

public record DetailPacienteDTO(String nome, String email, String telefone, String cpf, Endereco endereco) {

    public DetailPacienteDTO(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
