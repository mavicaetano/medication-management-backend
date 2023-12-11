package caetano.maria.medicationmanagement.model;

import caetano.maria.medicationmanagement.enums.TipoMedicamentoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MEDICAMENTO")
@Getter
@Setter
public class Medicamento {
    @Id
    private Integer nroRegistro;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String laboratorio;

    @Column(nullable = false)
    private String dosagem;

    private String descricao;

    @Column(nullable = false)
    private Float preco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMedicamentoEnum tipo;
}