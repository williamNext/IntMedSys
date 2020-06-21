package intmedsys.service;

import intmedsys.model.InteracaoMedicamentosa;
import intmedsys.model.Medicamento;
import intmedsys.repository.InteracaoMedicamentosaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InteracaoMedicamentosaService {
    @Autowired InteracaoMedicamentosaRepository interacaoMedicamentosaRepository;
    @Autowired MedicamentoService medicamentoService;

    public Optional<InteracaoMedicamentosa> getInteracao(String medA, String medB){
        LinkedList<Medicamento> meds = medicamentoService.getMedicamentos(medA, medB);
        Optional<InteracaoMedicamentosa> interactions = interacaoMedicamentosaRepository.findInteraction(meds.getFirst().getId(), meds.getLast().getId());
        return interactions;
    }

    public List<Long> getInteractionList(Long id){
//        return interacaoMedicamentosaRepository.findAll().stream()
//                .filter(med ->  med.getIdMedicamentoA()==id)
//                .map(InteracaoMedicamentosa::getIdMedicamentob)
//                .collect(Collectors.toList());
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
}
