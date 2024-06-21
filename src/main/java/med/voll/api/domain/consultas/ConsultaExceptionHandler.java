package med.voll.api.domain.consultas;

import med.voll.api.infra.exceptions.ConsultaConflictException;
import med.voll.api.infra.exceptions.InactiveEntityException;
import med.voll.api.infra.exceptions.OffBusinessHoursException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ConsultaExceptionHandler {

    @ExceptionHandler(OffBusinessHoursException.class)
    public ResponseEntity<String> handleOffBusinessHoursException(OffBusinessHoursException exception) {
        // 422 Unprocessable Entity is used when the request is well-formed but the server is unable to process it
        // in this case, the server is unable to process the request because the consultation is outside business hours
        // This inflict with our business rules
        return ResponseEntity.unprocessableEntity().body(exception.getMessage());
    }

    @ExceptionHandler(InactiveEntityException.class)
    public ResponseEntity<String> handleInactiveEntityException(InactiveEntityException exception) {
        return ResponseEntity.unprocessableEntity().body(exception.getMessage());
    }

    @ExceptionHandler(ConsultaConflictException.class)
    public ResponseEntity<String> handleConsultaConflictException(ConsultaConflictException exception) {
        return ResponseEntity.unprocessableEntity().body(exception.getMessage());
    }
}
