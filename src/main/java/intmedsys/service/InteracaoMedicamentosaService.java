package intmedsys.service;

import intmedsys.model.InteracaoMedicamentosa;
import intmedsys.model.Medicamento;
import intmedsys.repository.InteracaoMedicamentosaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
}
