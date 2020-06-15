package intmedsys.controller;

import intmedsys.model.Medicamento;
import intmedsys.repository.MedicametoRepository;
import intmedsys.service.MedicamentoService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class InteractionController implements Initializable {
    @FXML
    private TextField medicamentoA;

    @FXML
    private TextField medicamentoB;

    @FXML
    private TextArea descricaoField;

    @FXML
    private Button buscar;
    @Autowired
    private MedicamentoService medicamentoService;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buscar.setOnAction(event ->{

            descricaoField.setText(medicamentoService.getAll());
        });
    }
}
