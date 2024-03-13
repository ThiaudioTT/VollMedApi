package med.voll.api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice // This annotation is used to handle exceptions in the whole application
public class ErrorClass {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<String> handle404(EntityNotFoundException ex) {
        // This method will handle 404 errors
//        return ResponseEntity.notFound().build();
        // todo: change this to a JSON response (optional)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // 400 Bad Request
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handle400(MethodArgumentNotValidException exception) {
        List<FieldError> errors = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(Error::new).toList());
    }

    public record Error(String field, String message) {
        public Error(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
