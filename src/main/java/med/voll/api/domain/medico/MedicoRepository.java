package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.Optional;

// This interface will be implemented by Spring Data JPA, so we don't need to implement the methods
// This interface will be used to access the database and perform CRUD operations
// Search in the net for what is interfaces (POO)
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    // this method will return all the medicos that are active
    // The name of the method is important, because Spring Data JPA will use the method name to create the query
    // The method name is in the format "findAllBy" + "Field" + "Condition"
    Page<Medico> findAllByAtivoTrue(Pageable pageable);

    // this method will return a random medico based on the specialization
    // Optional is used to avoid null pointer exceptions
    // Note: the method wasn't working as intended because of the enum especialidade
    // Idk why, but now I am converting to a string and it is working
    @Query(value = """
            SELECT m.*\s
                FROM medicos m
                WHERE
                    m.ativo = 1
                    AND m.especialidade = :#{#especialidade.toString()}
                    AND NOT EXISTS (
                        SELECT 1\s
                        FROM consultas c
                        WHERE c.medico_id = m.id\s
                          AND c.data_consulta = :data
                    )
                ORDER BY RAND()
                LIMIT 1
                   """, nativeQuery = true)
    Optional<Medico> findRandomByEspecialidade(@Param("especialidade") Especialidade especialidade, @Param("data") LocalDateTime data);
}
