package med.voll.api.infra.exceptions;

public class OffBusinessHoursException extends IllegalArgumentException {

    public OffBusinessHoursException(String message) {
        super(message);
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Mensagem não pode ser vazia.");
        }

    }

    public OffBusinessHoursException() {
        super("Fora do horário de atendimento.");
    }
}
