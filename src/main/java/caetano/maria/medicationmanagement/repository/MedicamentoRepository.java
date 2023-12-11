package caetano.maria.medicationmanagement.repository;

import caetano.maria.medicationmanagement.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {

    @Query(value = "SELECT exists(SELECT 1 FROM medicamento WHERE nro_registro = ?1)", nativeQuery = true)
    boolean isNroRegistroExistente(Integer nroRegistro);
}