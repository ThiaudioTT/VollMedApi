package med.voll.api.medico;

import med.voll.api.endereco.dadosEndereco;

// nome Will be a private prop inside this class and it will generate a getter for nome, so we access like this: instance.nome();
public record MedicoRecord(String nome, String email, String crm, Especialidade especialidade, dadosEndereco endereco) {
}
