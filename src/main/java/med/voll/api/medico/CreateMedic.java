package med.voll.api.medico;

import med.voll.api.endereco.dadosEndereco;

public record CreateMedic(String nome, String email, String crm, Especialidade especialidade, dadosEndereco dadosEndereco) {
}
