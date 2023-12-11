package caetano.maria.medicationmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class  EnderecoRecordDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "CEP não pode estar em branco.")
    private Long cep;

    @NotBlank(message = "Logradouro não pode estar em branco.")
    private String logradouro;

    @NotBlank(message = "Número não pode estar em branco.")
    private String numero;

    @NotBlank(message = "Bairro não pode estar em branco.")
    private String bairro;

    @NotBlank(message = "Cidade não pode estar em branco.")
    private String cidade;

    @NotBlank(message = "Estado não pode estar em branco.")
    private String estado;

    private String complemento;

    @NotNull(message = "Latitude não pode ser nulo.")
    private Double latitude;

    @NotNull(message = "Longitude não pode ser nulo.")
    private Double longitude;
}
