package med.voll.api.domain.medico;

import med.voll.api.tools.CommonEntityCreation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest extends CommonEntityCreation {

    @Autowired
    private MedicoRepository medicoRepository;

    @Test
    @DisplayName("Should NOT return random medico based on the especialidade")
    void findRandomByEspecialidadeMedicoNUll() {
        var medico = medicoRepository.findRandomByEspecialidade(Especialidade.GINECOLOGIA, null);

        // verify if the medico is null
        assertTrue(medico.isEmpty());
        // after the test, the database will be empty
        // because of the annotation @DataJpaTest
        // spring will rollback the transaction
    }

    @Test
    @DisplayName("Should return a random medico based on the especialidade")
    void findRandomByEspecialidadeMedico() {
        // create a medico
        var especialidade = this.getRandomEspecialidade();

        var medico = createMedico(especialidade);
        // save the medico
        medicoRepository.save(medico);

        // find a random medico based on the especialidade
        var medicoFound = medicoRepository.findRandomByEspecialidade(especialidade, null);

        // verify if the medico is not null
        assertTrue(medicoFound.isPresent());
        // verify if the medico is the same as the created
        assertEquals(medico, medicoFound.get());
    }


}