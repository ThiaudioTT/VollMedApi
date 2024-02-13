package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.EnderecoRecord;

// nome Will be a private prop inside this class and it will generate a getter for nome, so we access like this: instance.nome();
public record MedicoRecord(
        // this annotation will generate a validation for this field
        @NotBlank
        String nome,
        @NotBlank @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull @Valid // this annotation (Valid) will validate the nested object
        EnderecoRecord endereco) {}
