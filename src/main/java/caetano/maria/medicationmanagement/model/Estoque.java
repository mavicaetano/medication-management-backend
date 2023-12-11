package caetano.maria.medicationmanagement.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "ESTOQUE")
@IdClass(IdEstoque.class)
@Getter
@Setter
public class Estoque {

    @Id
    private Long cnpj;

    @Id
    private Integer nroRegistro;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;

}