package caetano.maria.medicationmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "FARMACIA")
@Getter
@Setter
public class Farmacia {
    @Id
    private Long cnpj;

    @Column(nullable = false)
    private String razaoSocial;

    @Column(nullable = false)
    private String nomeFantasia;

    @Column(nullable = false)
    private String email;

    private String telefone;

    @Column(nullable = false)
    private String celular;

    @Embedded
    private Endereco endereco;
}
