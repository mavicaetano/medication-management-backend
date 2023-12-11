package caetano.maria.medicationmanagement.repository;

import caetano.maria.medicationmanagement.model.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {
}
