package intmedsys.repository;

import intmedsys.model.InteracaoMedicamentosa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InteracaoMedicamentosaRepository extends JpaRepository<InteracaoMedicamentosa, Long> {
   List<InteracaoMedicamentosa> findByIdMedicamentoA(long idA);
   @Query(value= "select * from INTERACAO_MEDICAMENTOSA i where " +
           "(i.ID_MEDICAMENTOA  =:ida and i.ID_MEDICAMENTOB = :idb) or (i.ID_MEDICAMENTOA =:idb and i.ID_MEDICAMENTOB = :ida)",
           nativeQuery = true)
   Optional<InteracaoMedicamentosa> findInteraction(@Param("ida") long ida, @Param("idb") long idb);

   @Query(value= "select * from INTERACAO_MEDICAMENTOSA i where " +
           "(i.ID_MEDICAMENTOA  =:id or i.ID_MEDICAMENTOB = :id)",
           nativeQuery = true)
   List <InteracaoMedicamentosa> findAllInteractions(@Param("id") long id);

}
