package med.voll.api.infra.exceptions;

public class ConsultaConflictException extends RuntimeException {
    public ConsultaConflictException(String message) {
        super(message);
    }

    public ConsultaConflictException(String message, Throwable cause) {
        super(message, cause);
    }

}
