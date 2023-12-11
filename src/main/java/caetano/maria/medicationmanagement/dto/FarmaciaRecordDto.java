package caetano.maria.medicationmanagement.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class FarmaciaRecordDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "CNPJ não pode ser nulo.")
    private Long cnpj;

    @NotBlank(message = "Razão Social não pode estar em branco.")
    private String razaoSocial;

    @NotBlank(message = "Nome Fantasia não pode estar em branco.")
    private String nomeFantasia;

    @NotBlank(message = "E-mail não pode estar em branco.")
    private String email;

    private String telefone;

    @NotNull(message = "Celular não pode ser nulo.")
    private String celular;

    @Valid
    private EnderecoRecordDto endereco;
}