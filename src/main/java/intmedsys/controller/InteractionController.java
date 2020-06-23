package intmedsys.controller;

import intmedsys.utils.alerts.AlertManager;
import intmedsys.utils.alerts.AlertMessages;
import intmedsys.model.InteracaoMedicamentosa;
import intmedsys.service.InteracaoMedicamentosaService;
import intmedsys.service.MedicamentoService;
import intmedsys.utils.screen.ScreenManager;
import intmedsys.utils.screen.ScreenPath;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.*;
import java.util.List;

@Component
public class InteractionController implements Initializable, ScreenManager, AlertManager {
    @FXML AnchorPane root;
    @FXML private TextField medicamentoA;
    @FXML private TextField medicamentoB;
    @FXML private TextArea descricaoField;
    @FXML private Label statusLabel;
    @FXML private Button buscar;
    @FXML private Label buscaAvancadaMenuItem;
    @FXML private MenuItem removeInteracaoMenuItem;
    @FXML private MenuItem addInteracaoMenuItem;
    @FXML private  MenuItem addMedMenuItem;
    @FXML private  MenuItem removeMedMenuItem;
    @Autowired private MedicamentoService medicamentoService;
    @Autowired private InteracaoMedicamentosaService interacaoMedicamentosaService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextFields.bindAutoCompletion(medicamentoA, this.getAll());
        TextFields.bindAutoCompletion(medicamentoB, this.getAll());

        buscar.setOnAction(event ->makeInteractionSearch());

        buscaAvancadaMenuItem.setOnMouseClicked(ev->changeScene(ScreenPath.BUSCA_AVANCADA));
;       addInteracaoMenuItem.setOnAction(ev->changeScene(ScreenPath.ADICIONA_INTERACAO));
        addMedMenuItem.setOnAction(ev -> changeScene(ScreenPath.ADICIONA_MEDICAMENTO));
        removeInteracaoMenuItem.setOnAction(ev -> changeScene(ScreenPath.REMOVE_INTERACAO));
        removeMedMenuItem.setOnAction(actionEvent -> changeScene(ScreenPath.REMOVE_MEDICAMENTO));
    }

    private void makeInteractionSearch() {
        statusLabel.setText("");
        if(medicamentoA.getText().isBlank() || medicamentoB.getText().isBlank()){
            makeAlert(Alert.AlertType.WARNING,"Atenção", AlertMessages.MESSAGE_BLANK_SPACE.getMessage()).show();
        }else{
            try {
                checkInteraction();
            }catch (IllegalStateException | NoSuchElementException ex){
                makeAlert(Alert.AlertType.ERROR,"Atenção",AlertMessages.MESSAGE_NOT_FOUUND.getMessage()).show();
            }}
    }


    private void checkInteraction() {
        Optional<InteracaoMedicamentosa> interacao = interacaoMedicamentosaService
                                                    .getInteracaoNames(medicamentoA.getText(), medicamentoB.getText());
        if (interacao.isPresent()) {
            descricaoField.setText(interacao.get().getDescricao());
            statusLabel.setText("COM INTERAÇÃO");
            statusLabel.setTextFill(Paint.valueOf("#ff1808"));
            makeAlert(Alert.AlertType.WARNING, "Atenção", AlertMessages.MESSAGE_HAS_INTERACTION.getMessage()).show();
        }else{
            descricaoField.setText("");
            statusLabel.setText("SEM INTERAÇÃO");
            statusLabel.setTextFill(Paint.valueOf("#75db00"));
            makeAlert(Alert.AlertType.INFORMATION, "Atenção", AlertMessages.MESSAGE_NO_INTERACTION.getMessage()).show();
        }
    }

    public List<String> getAll() {
        return medicamentoService.getAllMedsNames();
    }
}
