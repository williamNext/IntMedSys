package intmedsys;

import intmedsys.model.Medicamento;
import intmedsys.repository.MedicametoRepository;
import intmedsys.service.MedicamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class IntmedsysApplicationTests {
	@Autowired
	MedicamentoService medicamentoService;
	@Test
	void contextLoads() {


		String all = medicamentoService.getAll();
		System.out.println(all);
	}

}
