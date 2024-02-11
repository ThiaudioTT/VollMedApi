package med.voll.api.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

// DTO pattern
public record dadosEndereco(
        @NotBlank
        String logradouro,
        @NotBlank @Pattern(regexp = "\\d{8}")
        String bairro, String cep,
        @NotBlank
        String cidade, String uf,
        String complemento,
        String numero
) { }
