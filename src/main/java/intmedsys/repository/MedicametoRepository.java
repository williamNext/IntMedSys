package intmedsys.repository;

import intmedsys.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicametoRepository  extends JpaRepository<Medicamento, Long>{
}
