package med.voll.api.medico;

import med.voll.api.endereco.Endereco;

public record CreateMedic(String nome, String email, String crm, Especialidade especialidade, Endereco endereco) {
}
