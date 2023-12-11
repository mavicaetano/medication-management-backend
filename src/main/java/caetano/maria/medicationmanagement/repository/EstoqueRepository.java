package caetano.maria.medicationmanagement.repository;

import caetano.maria.medicationmanagement.model.Estoque;
import caetano.maria.medicationmanagement.model.IdEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, IdEstoque> {

    List<Estoque> findEstoquesByCnpj(Long cnpj);
    Optional<Estoque> findEstoqueByCnpjAndNroRegistro(Long cnpj, Integer nroRegistro);
}
