package intmedsys;

import intmedsys.model.InteracaoMedicamentosa;
import intmedsys.service.InteracaoMedicamentosaService;
import intmedsys.service.MedicamentoService;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.spring.SpringFxWeaver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javafx.application.Application;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IntmedsysApplication {

	public static void main(String[] args) {
//		SpringApplication.run(IntMedSys.class, args);
	Application.launch(IntMedSys.class);
	}
	@Bean
	public MedicamentoService Medicamentoservice() {
		return new MedicamentoService();
	}
	@Bean
	public InteracaoMedicamentosaService Interacaoservice() {
		return new InteracaoMedicamentosaService() ;
	}
}
