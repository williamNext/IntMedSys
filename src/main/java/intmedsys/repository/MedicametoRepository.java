package intmedsys.repository;

import intmedsys.model.InteracaoMedicamentosa;
import intmedsys.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicametoRepository  extends JpaRepository<Medicamento, Long>{
    Optional<Medicamento> findByNome(String nome);
}
