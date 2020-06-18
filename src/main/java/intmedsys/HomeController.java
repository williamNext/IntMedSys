package intmedsys;

import intmedsys.model.Medicamento;
import intmedsys.repository.InteracaoMedicamentosaRepository;
import intmedsys.repository.MedicametoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    MedicametoRepository MRepo;


    @GetMapping(value = "/meds")
    @ResponseBody
    public List<Medicamento> gets(){
        Medicamento med = new Medicamento();
//        med.setName("Bulbo");
        MRepo.save(med);
        return MRepo.findAll();
    }
}
