package med.voll.api.medico;

import med.voll.api.endereco.Endereco;
import med.voll.api.endereco.EnderecoRecord;

public record UpdatedMedicoDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        Endereco endereco
) {

    public UpdatedMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
