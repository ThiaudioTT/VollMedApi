package med.voll.api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import java.util.List;

/**
 * This is a class to handle exceptions in the application
 */

@RestControllerAdvice // This annotation is used to handle exceptions in the whole application
public class ErrorClass {

    // this method will handle 404 errors
    @ExceptionHandler({EntityNotFoundException.class, NoResourceFoundException.class})
    public ResponseEntity<String> handle404(Exception ex) {
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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle400(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    // Handle 400 for invalid JSON and other errors like it
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handle400(HttpMessageNotReadableException exception) {
        return ResponseEntity.badRequest().body("Invalid JSON. Check the request body");
    }

    // 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle500(Exception exception) {
        System.out.println(exception.getClass());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    public record Error(String field, String message) {
        public Error(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }


    // todo: handle error 409 (conflict)
}
