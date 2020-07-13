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
		ArrayList<String> marcacoes = new ArrayList<>();
		int count = 0;
		for (String[] prescricoes: strings) {

			for (int i = 0; i < prescricoes.length ; i++) {
				boolean flag = false;
				for (int j = 0; j < prescricoes.length ; j++){
					if(interacaoMedicamentosaService.getInteracaoNamesTest(prescricoes[i],prescricoes[j])){
						marcacoes.add("("+prescricoes[i]+";"+prescricoes[j]+") referente a item "+String.join(";",prescricoes));
						interativos.add(String.join(";",prescricoes));
						integers.add(count);
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
		System.out.println("*****************************");
		System.out.println(interativos.size());
		System.out.println("*****************************");
		System.out.println(naoInterativos.size());
		System.out.println("*****************************");
		System.out.println("------INTERATIVOS-----------");
		interativos.forEach(System.out::println);
		System.out.println("------NÃOINTERATIVOS-----------");
		naoInterativos.forEach(System.out::println);
		System.out.println("*****************************");
		marcacoes.forEach(System.out::println);
	}
}
