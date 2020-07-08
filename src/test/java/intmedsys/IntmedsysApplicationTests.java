package intmedsys;

import intmedsys.controller.BuscaAvancadaController;
import intmedsys.model.InteracaoMedicamentosa;
import intmedsys.model.Medicamento;
import intmedsys.repository.InteracaoMedicamentosaRepository;
import intmedsys.repository.MedicametoRepository;
import intmedsys.service.InteracaoMedicamentosaService;
import intmedsys.service.MedicamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.spec.PSource;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class IntmedsysApplicationTests {
	@Autowired
	MedicamentoService medicamentoService;
	@Autowired
	InteracaoMedicamentosaRepository interacaoMedicamentosaRepository;
	@Autowired
	InteracaoMedicamentosaService interacaoMedicamentosaService;
	@Autowired
	private BuscaAvancadaController busca;
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
//
		List<String[]> strings = new ArrayList<>();
		strings.add(new String[]{"METOCLOPRAMIDA","PIPERACILINA+TAZOBACTAM"});
		strings.add(new String[]{"METOCLOPRAMIDA","CEFEPIME"});
		strings.add(new String[]{"METOCLOPRAMIDA","FUROSEMIDA","CEFALOTINA"});
		strings.add(new String[]{"METOCLOPRAMIDA","FUROSEMIDA"});
		strings.add(new String[]{"MIDAZOLAM","METOCLOPRAMIDA"});
		strings.add(new String[]{"MIDAZOLAM","METOCLOPRAMIDA","PIPERACILINA+TAZOBACTAM"});
		strings.add(new String[]{"METOCLOPRAMIDA","AMPICILINA+SULBACTAM"});
		strings.add(new String[]{"METOCLOPRAMIDA","CEFTRIAXONA"});
		strings.add(new String[]{"AMPICILINA+SULBACTAM","METOCLOPRAMIDA"});
		strings.add(new String[]{"METOCLOPRAMIDA","AMPICILINA+SULBACTAM","FLUCONAZOL"});
		strings.add(new String[]{"METOCLOPRAMIDA","AMPICILINA+SULBACTAM","MIDAZOLAM"});
		strings.add(new String[]{"METOCLOPRAMIDA","FUROSEMIDA","AMPICILINA+SULBACTAM","FLUCONAZOL"});
		strings.add(new String[]{"METOCLOPRAMIDA","FUROSEMIDA","AMPICILINA+SULBACTAM"});
		strings.add(new String[]{"METOCLOPRAMIDA","MEROPENEM"});
		strings.add(new String[]{"METOCLOPRAMIDA","AMICACINA"});
		strings.add(new String[]{"FUROSEMIDA","METOCLOPRAMIDA"});
		strings.add(new String[]{"METOCLOPRAMIDA","CEFALOTINA"});
		strings.add(new String[]{"METOCLOPRAMIDA","FUROSEMIDA","CEFTRIAXONA"});
		strings.add(new String[]{"METOCLOPRAMIDA","FUROSEMIDA","CEFEPIME"});
		strings.add(new String[]{"METOCLOPRAMIDA","FUROSEMIDA","PIPERACILINA+TAZOBACTAM"});
		strings.add(new String[]{"FUROSEMIDA","METOCLOPRAMIDA","PIPERACILINA+TAZOBACTAM"});
		strings.add(new String[]{"METOCLOPRAMIDA","CLINDAMICINA"});
		strings.add(new String[]{"METOCLOPRAMIDA","PIPERACILINA+TAZOBACTAM","DAPTOMICINA"});
		strings.add(new String[]{"METOCLOPRAMIDA","DAPTOMICINA"});
		strings.add(new String[]{"METOCLOPRAMIDA","FUROSEMIDA","OXACILINA"});
		strings.add(new String[]{"METOCLOPRAMIDA","OXACILINA"});
		strings.add(new String[]{"METOCLOPRAMIDA","MEROPENEM","FLUCONAZOL","TEICOPLANINA"});
		strings.add(new String[]{"METOCLOPRAMIDA","MEROPENEM","FLUCONAZOL","LEVOFLOXACINO"});
		strings.add(new String[]{"METOCLOPRAMIDA","LEVOFLOXACINO","MEROPENEM","FLUCONAZOL"});
		strings.add(new String[]{"METOCLOPRAMIDA","LEVOFLOXACINO","FLUCONAZOL"});
		strings.add(new String[]{"CIPROFLOXACINO","CLINDAMICINA"});
		strings.add(new String[]{"METOCLOPRAMIDA","MEROPENEM","DAPTOMICINA"});
		strings.add(new String[]{"METOCLOPRAMIDA","FUROSEMIDA","CEFALOTINA","MEROPENEM"});
		strings.add(new String[]{"CEFALOTINA","MEROPENEM"});
		strings.add(new String[]{"METOCLOPRAMIDA","FUROSEMIDA","MEROPENEM"});
		strings.add(new String[]{"METOCLOPRAMIDA","CIPROFLOXACINO"});
		strings.add(new String[]{"CEFTRIAXONA","METOCLOPRAMIDA"});
		strings.add(new String[]{"CEFEPIME","METOCLOPRAMIDA"});
		strings.add(new String[]{"METOCLOPRAMIDA","METRONIDAZOL"});
		strings.add(new String[]{"CEFALOTINA","MIDAZOLAM"});
		strings.add(new String[]{"CEFALOTINA","METOCLOPRAMIDA"});
		strings.add(new String[]{"METOCLOPRAMIDA","TEICOPLANINA"});
		strings.add(new String[]{"FUROSEMIDA","MEROPENEM"});
		strings.add(new String[]{"MIDAZOLAM","METOCLOPRAMIDA","FUROSEMIDA","CEFALOTINA"});
		strings.add(new String[]{"METOCLOPRAMIDA","CEFALOTINA","AMICACINA"});
		strings.add(new String[]{"MIDAZOLAM","FUROSEMIDA","CEFEPIME"});
		strings.add(new String[]{"MIDAZOLAM","METOCLOPRAMIDA","FUROSEMIDA","CEFEPIME"});
		strings.add(new String[]{"PIPERACILINA+TAZOBACTAM","TEICOPLANINA"});
		strings.add(new String[]{"METOCLOPRAMIDA","CEFALOTINA","GENTAMICINA"});
		strings.add(new String[]{"METOCLOPRAMIDA","GENTAMICINA","CLINDAMICINA"});
		strings.add(new String[]{"METOCLOPRAMIDA","CEFUROXIMA"});
		strings.add(new String[]{"MIDAZOLAM","FUROSEMIDA","AMPICILINA+SULBACTAM","METOCLOPRAMIDA"});
		strings.add(new String[]{"MIDAZOLAM","AMPICILINA+SULBACTAM","METOCLOPRAMIDA"});
		strings.add(new String[]{"VANCOMICINA","PIPERACILINA+TAZOBACTAM"});
		strings.add(new String[]{"CEFTRIAXONA","METRONIDAZOL"});



		Set <String> interativos = new HashSet<>();
		Set <String> naoInterativos = new HashSet<>();
		ArrayList<Integer> integers = new ArrayList<>();
		int count = 0;
		for (String[] item: strings) {
			List<Medicamento> medicamentos = interacaoMedicamentosaService.parseResultList(item[0]);
			for (int i = 1; i < item.length ; i++) {
				boolean flag = false;
				for (Medicamento med : medicamentos){
					if(item[i].equalsIgnoreCase(med.getNome())){
						interativos.add(String.join(";",item));
						integers.add(count);
						break;
					}
				}
			}
			count++;
		}
		strings.forEach(s->{
			if(!interativos.contains(String.join(";",s))){
					naoInterativos.add(String.join(";",s));
			}
		});
		System.out.println(interativos.size());
		System.out.println(naoInterativos.size());
		System.out.println("------INTERATIVOS-----------");
		interativos.forEach(System.out::println);
		System.out.println("------NÃOINTERATIVOS-----------");
		naoInterativos.forEach(System.out::println);

//		interativos.forEach(e-> System.out.println(e.toString()));
//		interacaoMedicamentosaService.getInteractionList();

	}
}
