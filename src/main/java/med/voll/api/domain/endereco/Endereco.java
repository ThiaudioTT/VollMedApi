package med.voll.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(EnderecoRecord EnderecoRecord) {
        this.logradouro = EnderecoRecord.logradouro();
        this.bairro = EnderecoRecord.bairro();
        this.cep = EnderecoRecord.cep();
        this.numero = EnderecoRecord.numero();
        this.complemento = EnderecoRecord.complemento();
        this.cidade = EnderecoRecord.cidade();
        this.uf = EnderecoRecord.uf();
    }
}
