package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // This annotation is used to handle exceptions in the whole application
public class ErrorClass {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<String> handle404(EntityNotFoundException ex) {
        // This method will handle 404 errors
//        return ResponseEntity.notFound().build();
        // todo: change this to a JSON response (optional)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
