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
    @Autowired
    InteracaoMedicamentosaRepository interacaoMedicamentosaRepository;
    @Autowired
    MedicamentoService medicamentoService;

    public Optional<InteracaoMedicamentosa> getInteracao(String medA, String medB){
        LinkedList<Medicamento> medicamentos = medicamentoService.getMedicamentos(medA, medB);
        List<InteracaoMedicamentosa> interacoesmedicamentoA= interacaoMedicamentosaRepository.findByIdMedicamentoA(medicamentos.get(0).getId());
        return interacoesmedicamentoA.stream().filter(it -> it.getIdMedicamentob() == medicamentos.get(1).getId())
                .reduce((m, v) ->{ throw new NoSuchElementException();});
    }

    public List<Long> getInteractionList(Long id){
        return interacaoMedicamentosaRepository.findAll().stream()
                .filter(med ->  med.getIdMedicamentoA()==id).
                map( i-> i.getIdMedicamentob())
                .peek(System.out::println)
                .collect(Collectors.toList());
    }
}
