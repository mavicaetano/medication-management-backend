package caetano.maria.medicationmanagement.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class IdEstoque implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long cnpj;

    private Integer nroRegistro;
}
