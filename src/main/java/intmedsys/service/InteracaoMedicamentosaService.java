package intmedsys.service;

import intmedsys.model.InteracaoMedicamentosa;
import intmedsys.model.Medicamento;
import intmedsys.repository.InteracaoMedicamentosaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InteracaoMedicamentosaService {
    @Autowired InteracaoMedicamentosaRepository interacaoMedicamentosaRepository;
    @Autowired MedicamentoService medicamentoService;


    public Optional<InteracaoMedicamentosa> getInteracaoNames(String medA, String medB){
        LinkedList<Medicamento> meds = medicamentoService.getMedicamentos(medA, medB);
        Optional<InteracaoMedicamentosa> interactions = interacaoMedicamentosaRepository.findInteraction(meds.getFirst().getId(), meds.getLast().getId());
        return interactions;
    }

    public List<Long> getInteractionList(Long id){
        ArrayList<Long> listaIds = new ArrayList<Long>();
        interacaoMedicamentosaRepository.findAllInteractions(id).stream()
                .forEach(e->{
                    if(e.getIdMedicamentoA()!=id)
                        listaIds.add(e.getIdMedicamentoA());
                    else
                        listaIds.add(e.getIdMedicamentob());
                });
        System.out.println(listaIds.size());
        return listaIds;
    }

    public void saveInteraction(String descricao,long idMedA, long idMedB){
          interacaoMedicamentosaRepository.save(new InteracaoMedicamentosa(descricao,idMedA,idMedB));
    }

    public Optional<InteracaoMedicamentosa> getInteracao(LinkedList<Medicamento> meds) {
        return interacaoMedicamentosaRepository.findInteraction(meds.getFirst().getId(), meds.getLast().getId());
    }

    public void removeInteraction(LinkedList<Medicamento> medicamentos) {
        Optional<InteracaoMedicamentosa> interaction = interacaoMedicamentosaRepository.findInteraction(medicamentos.getFirst().getId(), medicamentos.getLast().getId());
        interacaoMedicamentosaRepository.delete(interaction.get());
    }
}
