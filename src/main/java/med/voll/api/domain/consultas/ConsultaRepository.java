package med.voll.api.domain.consultas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    // this method is used in the NonConflictingScheduleValidator class
    // It must return an Unique value.
    Optional<Consulta> findByMedicoIdAndDataConsulta(Long medicoId, LocalDateTime dataConsulta);

    // this method is used in the NonConflictingScheduleValidator class
    // It must return an Unique value.


    /**
     * Método que retorna as consultas de um paciente em um dia específico.
     * @param pacienteId
     * @param dataConsulta Data da consulta em LocalDate, use o método toLocalDate() para converter.
     * @return Lista de consultas do paciente na data especificada.
     */
    @Query(value = "SELECT * FROM consultas c WHERE c.paciente_id = :pacienteId AND DATE(c.data_consulta) = :dataConsulta LIMIT 5", nativeQuery = true)
    Optional<List<Consulta>> findByPacienteIdInADay(@Param("pacienteId") Long pacienteId, @Param("dataConsulta") LocalDate dataConsulta);
}
