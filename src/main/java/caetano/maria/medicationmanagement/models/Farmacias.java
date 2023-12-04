package caetano.maria.medicationmanagement.models;

import jakarta.persistence.*;

@Entity
@Table(name = "FARMACIAS")
public class Farmacias {
    @Id
    private Long cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String email;
    private String telefone;
    private String celular;
    @Embedded
    private Endereco endereco;
}

}
