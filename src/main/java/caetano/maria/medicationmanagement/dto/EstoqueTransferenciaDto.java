package caetano.maria.medicationmanagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
public class EstoqueTransferenciaDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "O preenchimento do CNPJ de origem é obrigatório.")
    private Long cnpjOrigem;

    @NotNull(message = "O preenchimento do CNPJ de destino é obrigatório.")
    private Long cnpjDestino;

    @NotNull(message = "O número de registro do medicamento é obrigatório")
    private Integer nroRegistro;

    @NotNull(message = "O preenchimento da quantidade é obrigatório.")
    private Integer quantidade;

}
