package intmedsys.repository;

import intmedsys.model.InteracaoMedicamentosa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InteracaoMedicamentosaRepository extends JpaRepository<InteracaoMedicamentosa, Long> {
   List<InteracaoMedicamentosa> findByIdMedicamentoA(long idA);
}
