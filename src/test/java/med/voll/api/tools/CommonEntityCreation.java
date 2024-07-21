package med.voll.api.tools;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

public class CommonEntityCreation {

    /**
     * Create a medico
     * @return the medico created
     */
    public static Medico createMedico() {
        return new Medico(
                1L,
                "Dr. House",
                "houseemail@gmail.com",
                "123456789",
                "123456",
                Especialidade.CARDIOLOGIA,
                new Endereco(
                        "Rua dos Bobos",
                        "Jardim Recreio",
                        "72910280",
                        "12",
                        "casa",
                        "Brasília",
                        "DF"),
                true
        );
    }

    public static Medico createMedico(Especialidade especialidade) {
        return new Medico(
                2L,
                "Jose Especialidade",
                "joseemail@gmail.com",
                "6199999999",
                "123456",
                especialidade,
                new Endereco(
                        "Rua dos Bobos",
                        "Jardim Recreio",
                        "72910280",
                        "12",
                        "casa",
                        "Brasília",
                        "DF"),
                true
        );
    }

    public static Especialidade getRandomEspecialidade() {
        return Especialidade.values()[(int) (Math.random() * Especialidade.values().length)];
    }

    public static Paciente createPaciente() {
        return new Paciente(
                1L,
                "Paciente Teste",
                "paciente@email.com",
                "6199999999",
                "123456",
                createEndereco(),
                true
        );
    }

    public static Endereco createEndereco() {
        return new Endereco(
                "Rua dos Bobos",
                "Jardim Recreio",
                "72910280",
                "12",
                "casa",
                "Brasília",
                "DF"
        );
    }
}
