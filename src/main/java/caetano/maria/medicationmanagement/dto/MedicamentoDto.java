package caetano.maria.medicationmanagement.dto;

import caetano.maria.medicationmanagement.enums.TipoMedicamentoEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class MedicamentoDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Número de Registro não pode ser vazio.")
    private Integer nroRegistro;

    @NotBlank(message = "Nome não pode estar em branco.")
    private String nome;

    @NotBlank(message = "Laboratório não pode estar em branco.")
    private String laboratorio;

    @NotBlank(message = "Dosagem não pode estar em branco.")
    private String dosagem;

    private String descricao;

    @NotNull(message = "Preço não pode estar ser vazio.")
    private Float preco;

    @Valid
    private TipoMedicamentoEnum tipo;
}
