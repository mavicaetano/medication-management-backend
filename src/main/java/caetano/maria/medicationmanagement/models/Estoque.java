package caetano.maria.medicationmanagement.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ESTOQUES")
@IdClass(IdEstoque.class)
public class Estoque {
    @Id
    private Long cnpj;
    @Id
    private Integer nroRegistro;
    private Integer quantidade;
    private LocalDateTime dataAtualizacao;

}