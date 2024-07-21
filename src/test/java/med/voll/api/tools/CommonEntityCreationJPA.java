package med.voll.api.tools;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static med.voll.api.tools.CommonEntityCreation.createMedico;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CommonEntityCreationJPA {

    @Autowired
    private TestEntityManager em;

    /**
     * Save a medico in the database
     * @return the medico saved
     */
    public Medico saveMedico() {
        return em.persist(createMedico());
    }

    public Medico saveMedico(Especialidade especialidade) {
        return em.persist(CommonEntityCreation.createMedico(especialidade));
    }
}
