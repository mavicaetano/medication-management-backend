package caetano.maria.medicationmanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class EstoqueAddMedicamentoDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "CNPJ não pode estar vazio.")
    private Long cnpj;

    @NotNull(message = "Número de Registro não pode estar vazio")
    private Integer nroRegistro;

    @NotNull(message = "Quantidade não pode estar vazia.")
    @Min(message = "A quantidade deve ser maior que 0.", value = 1)
    private Integer quantidade;
}
