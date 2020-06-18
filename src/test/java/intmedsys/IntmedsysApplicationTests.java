package intmedsys;

import intmedsys.model.InteracaoMedicamentosa;
import intmedsys.model.Medicamento;
import intmedsys.repository.InteracaoMedicamentosaRepository;
import intmedsys.repository.MedicametoRepository;
import intmedsys.service.InteracaoMedicamentosaService;
import intmedsys.service.MedicamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
class IntmedsysApplicationTests {
	@Autowired
	MedicamentoService medicamentoService;
	@Autowired
	InteracaoMedicamentosaRepository interacaoMedicamentosaRepository;
	@Autowired
	InteracaoMedicamentosaService interacaoMedicamentosaService;
	@Test
	void contextLoads() {
//		Optional<InteracaoMedicamentosa> medicamentoB = interacaoMedicamentosaRepository.findByIdMedicamentoAAnAndIdMedicamentob(1,5);
//
//		if(medicamentoB.isPresent())
//			System.out.println(medicamentoB.get());
	}
	@Test
	void teste(){
//		Optional<InteracaoMedicamentosa> interacaoByid = interacaoMedicamentosaService.getInteracaoByid(1, 3);
//		if(interacaoByid.isPresent()){
//			System.out.println(interacaoByid.get().getIdMedicamentoA());
//			System.out.println(interacaoByid.get().getIdMedicamentob());}
	}
	@Test
	void testaBuscaListaInteracoes(){
//		List<Medicamento> lista = new ArrayList<>();
//		List<InteracaoMedicamentosa> interactionList = interacaoMedicamentosaService.getInteractionList((long) 1);
//		interactionList.forEach(med->{
//			Medicamento byid = medicamentoService.getByid(med.getIdMedicamentob());
//			lista.add(byid);
//		});
//		lista.forEach(i-> System.out.println(i.getNome()));

	}
}
