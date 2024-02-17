package med.voll.api.medico;

public record ListagemMedicoRecord(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) { // Returning only the fields that we want to show in the list in this DTO.
    // It is good to use DTOs to avoid exposing the database model


    public ListagemMedicoRecord(Medico medico) { // to convert medico to ListagemMedicoRecord
        this(medico.getId(),medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
