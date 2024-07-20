package med.voll.api.tools;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CommonEntityCreation {

    @Autowired
    private TestEntityManager em;

    /**
     * Create a medico
     * @return the medico created
     */
    public Medico createMedico() {
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

    public Medico createMedico(Especialidade especialidade) {
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

    /**
     * Save a medico in the database
     * @return the medico saved
     */
    public Medico saveMedico() {
        return em.persist(createMedico());
    }

    public Medico saveMedico(Especialidade especialidade) {
        return em.persist(createMedico(especialidade));
    }

    public Especialidade getRandomEspecialidade() {
        return Especialidade.values()[(int) (Math.random() * Especialidade.values().length)];
    }
}
