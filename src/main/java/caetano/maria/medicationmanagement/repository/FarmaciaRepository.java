package caetano.maria.medicationmanagement.repository;

import caetano.maria.medicationmanagement.model.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {

    @Query(value = "SELECT exists(SELECT 1 FROM farmacia WHERE cnpj = ?1)", nativeQuery = true)
    boolean isCnpjExistente(Long cnpj);
}
