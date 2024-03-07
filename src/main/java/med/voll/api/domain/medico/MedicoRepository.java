package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// This interface will be implemented by Spring Data JPA, so we don't need to implement the methods
// This interface will be used to access the database and perform CRUD operations
// Search in the net for what is interfaces (POO)
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    // this method will return all the medicos that are active
    // The name of the method is important, because Spring Data JPA will use the method name to create the query
    // The method name is in the format "findAllBy" + "Field" + "Condition"
    Page<Medico> findAllByAtivoTrue(Pageable pageable);
}
