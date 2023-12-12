package caetano.maria.medicationmanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
public class EstoqueTransferenciaRetornoDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer nroRegistro;

    private Long cnpjOrigem;

    private Integer quantidadeOrigem;

    private Long cnpjDestino;

    private Integer quantidadeDestino;
}
