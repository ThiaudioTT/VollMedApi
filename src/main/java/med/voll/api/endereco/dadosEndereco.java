package med.voll.api.endereco;

// DTO pattern
public record dadosEndereco(
        String logradouro,
        String bairro, String cep,
        String cidade, String uf,
        String complemento,
        String numero
) { }
