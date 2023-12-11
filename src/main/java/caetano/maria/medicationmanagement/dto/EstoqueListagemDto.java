package caetano.maria.medicationmanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class EstoqueListagemDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer nroRegistro;
    private String nome;
    private Integer quantidade;
    private LocalDateTime dataAtualizacao;
}
