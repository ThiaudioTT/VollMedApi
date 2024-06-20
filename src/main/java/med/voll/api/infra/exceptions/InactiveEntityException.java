package med.voll.api.infra.exceptions;

public class InactiveEntityException extends RuntimeException {

    public InactiveEntityException(String message) {
        super(message);
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Mensagem não pode ser vazia.");
        }
    }

    public InactiveEntityException() {
        super("Entidade inativa");
    }
}
