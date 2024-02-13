package med.voll.api.medico;

import org.springframework.data.jpa.repository.JpaRepository;

// This interface will be implemented by Spring Data JPA, so we don't need to implement the methods
// This interface will be used to access the database and perform CRUD operations
public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
