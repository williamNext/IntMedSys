package intmedsys.controller;

import intmedsys.model.InteracaoMedicamentosa;
import intmedsys.model.Medicamento;
import intmedsys.service.InteracaoMedicamentosaService;
import intmedsys.service.MedicamentoService;
import intmedsys.utils.alerts.AlertManager;
import intmedsys.utils.alerts.AlertMessages;
import intmedsys.utils.screen.ScreenManager;
import intmedsys.utils.screen.ScreenPath;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;
@Component
public class RemoveInteracaoController implements Initializable, ScreenManager, AlertManager {
    @FXML private TextField medicamentoA;
    @FXML private TextField medicamentoB;
    @FXML private Button removeBtn;
    @FXML private Label buscaAvancadaMenuItem;
    @FXML private Label buscaMenuItem;
    @FXML private MenuItem removeInteracaoMenuItem;
    @FXML private MenuItem addInteracaoMenuItem;
    @FXML private  MenuItem addMedMenuItem;
    @FXML private  MenuItem removeMedMenuItem;
    @Autowired InteracaoMedicamentosaService interacaoMedicamentosaService;
    @Autowired MedicamentoService medicamentoService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextFields.bindAutoCompletion(medicamentoA, medicamentoService.getAllMedsNames());
        TextFields.bindAutoCompletion(medicamentoB, medicamentoService.getAllMedsNames());
        buscaAvancadaMenuItem.setOnMouseClicked(ev->changeScene(ScreenPath.BUSCA_AVANCADA));
        addInteracaoMenuItem.setOnAction(ev->changeScene(ScreenPath.ADICIONA_INTERACAO));
        buscaMenuItem.setOnMouseClicked(ev->changeScene(ScreenPath.BUSCA_MEDICAMENTO));
        addMedMenuItem.setOnAction(actionEvent -> changeScene(ScreenPath.ADICIONA_MEDICAMENTO));
        removeMedMenuItem.setOnAction(actionEvent -> changeScene(ScreenPath.REMOVE_MEDICAMENTO));
        removeBtn.setOnAction(actionEvent -> checaErros(medicamentoA,medicamentoB));
    }
    private void checaErros(TextField a, TextField b){
        if(!isVazio(medicamentoA) && !isVazio(medicamentoB)){
            try {
                LinkedList<Medicamento> medicamentos = medicamentoService.getMedicamentos(a.getText(), b.getText());
                if(isExistente(medicamentos)){
                    Alert confirm = makeAlert(Alert.AlertType.WARNING, "ATENÇÃO", AlertMessages.MESSAGE_SAVE_MED_CONFIRMATION.getMessage());
                    Optional<ButtonType> result = confirm.showAndWait();
                    result.ifPresent(r-> {
                        if(result.get() == ButtonType.OK){
                            interacaoMedicamentosaService.removeInteraction(medicamentos);
                        }
                    });
                }else{
                    makeAlert(Alert.AlertType.ERROR, "ERRO", AlertMessages.MESSAGE_NO_INTERACTION.getMessage()).show();
                }
            }catch (Exception e){
                makeAlert(Alert.AlertType.ERROR, "ERRO", AlertMessages.MESSAGE_NOT_FOUUND.getMessage()).show();
            }
        }else {
            makeAlert(Alert.AlertType.WARNING, "ERRO", AlertMessages.MESSAGE_BLANK_SPACE.getMessage()).show();
        }

    }
    private boolean isVazio(TextField campo){
        return campo.getText().isBlank();
    }
    private boolean isExistente(LinkedList<Medicamento> medicamentos){
        Optional<InteracaoMedicamentosa> interacao = interacaoMedicamentosaService.getInteracao(medicamentos);
        if (interacao.isPresent()){
            return true;
        }
        return false;
    }

    }
