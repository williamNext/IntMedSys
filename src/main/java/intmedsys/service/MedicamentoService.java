package intmedsys.service;

import intmedsys.model.Medicamento;
import intmedsys.repository.MedicametoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicamentoService {

    @Autowired
    private MedicametoRepository medicametoRepository;
    public List<String> getAll() {
        try{
            List<Medicamento> all = medicametoRepository.findAll();
            return all.stream().map(Medicamento::getName).collect(Collectors.toList());
        }catch (Exception e){
            System.out.println(e.getMessage()+ "deu ruim");
        }
        return null;
    }
    public LinkedList<Medicamento> getMedicamentos(String a, String  b){
        LinkedList<Medicamento> medicamentos = new LinkedList<>();
        Medicamento meda = getByName(a.strip().toUpperCase());
        Medicamento medb = getByName(b.strip().toUpperCase());
        medicamentos.add(meda);
        medicamentos.add(medb);
        return medicamentos;
    }
    private Medicamento getByName(String name){
        return medicametoRepository.findByNome(name.strip().toUpperCase())
                                   .orElseThrow(IllegalStateException::new);
    }


}
