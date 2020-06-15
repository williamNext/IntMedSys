package intmedsys.service;

import intmedsys.model.Medicamento;
import intmedsys.repository.MedicametoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class MedicamentoService {

    @Autowired
    private MedicametoRepository medicametoRepository;
    public String getAll() {
        try{


            List<Medicamento> all = medicametoRepository.findAll();
            return all.toString();
        }catch (Exception e){
            System.out.println(e.getMessage()+ "deu ruim");
        }
        return "deu ruim";
    }
}
