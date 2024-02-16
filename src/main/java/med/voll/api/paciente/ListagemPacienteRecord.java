package med.voll.api.paciente;

// DTO para listar os pacientes
public record ListagemPacienteRecord(
        String nome,
        String email,
        String CPF
) {

    public ListagemPacienteRecord(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
