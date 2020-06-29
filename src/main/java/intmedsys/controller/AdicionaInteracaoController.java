package intmedsys.controller;

import intmedsys.model.InteracaoMedicamentosa;
import intmedsys.model.Medicamento;
import intmedsys.service.InteracaoMedicamentosaService;
import intmedsys.service.MedicamentoService;
import intmedsys.utils.alerts.AlertManager;
import intmedsys.utils.alerts.AlertMessages;
import intmedsys.utils.screen.ScreenManager;
import intmedsys.utils.screen.ScreenPath;
import javafx.event.ActionEvent;
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
public class AdicionaInteracaoController  implements Initializable, ScreenManager, AlertManager {
    @FXML private TextField medicamentoA;
    @FXML private TextField medicamentoB;
    @FXML private Button btnSalvar;
    @FXML private TextArea descricaoTextArea;
    @FXML private CheckBox addDescricaoCheckBox;
    @FXML private MenuItem buscaMenuItem;
    @FXML private MenuItem buscaAvancadaMenuItem;
    @FXML private MenuItem removeInteracaoMenuItem;
    @FXML private MenuItem addInteracaoMenuItem;
    @FXML private  MenuItem addMedMenuItem;
    @FXML private  MenuItem removeMedMenuItem;
    @Autowired private InteracaoMedicamentosaService interacaoMedicamentosaService;
    @Autowired MedicamentoService medicamentoService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextFields.bindAutoCompletion(medicamentoA, medicamentoService.getAllMedsNames());
        TextFields.bindAutoCompletion(medicamentoB, medicamentoService.getAllMedsNames());
        descricaoTextArea.setDisable(true);
        buscaMenuItem.setOnAction(ev->changeScene(ScreenPath.BUSCA_MEDICAMENTO));
        buscaAvancadaMenuItem.setOnAction(ev->changeScene(ScreenPath.BUSCA_AVANCADA));
        addDescricaoCheckBox.setOnAction(this::setDescriptionAvailable);
        btnSalvar.setOnAction(ev->tryToSaveInteraction().show());
        addMedMenuItem.setOnAction(ev -> changeScene(ScreenPath.ADICIONA_MEDICAMENTO));
        removeInteracaoMenuItem.setOnAction(ev -> changeScene(ScreenPath.REMOVE_INTERACAO));
        removeMedMenuItem.setOnAction(actionEvent -> changeScene(ScreenPath.REMOVE_MEDICAMENTO));
    }

    private Alert tryToSaveInteraction() {
        if(!medicamentoA.getText().isBlank() && !medicamentoB.getText().isBlank()){
            try{
                LinkedList<Medicamento> medicamentos = medicamentoService.getMedicamentos(medicamentoA.getText(), medicamentoB.getText());
                if(medicamentos.getFirst().getId() == medicamentos.getLast().getId())
                    return makeAlert(Alert.AlertType.ERROR,"ERRO", AlertMessages.MESSAGE_SAME_NAME_FAIL.getMessage());
                if(canSaveInteraction(medicamentos))
                    return makeAlert(Alert.AlertType.INFORMATION,"SUCESSO", AlertMessages.MESSAGE_SUCESS_SAVE_INTERACTION.getMessage());
                else
                   return  makeAlert(Alert.AlertType.ERROR,"ERRO", AlertMessages.MESSAGE_ALREADY_HAS_INTERACTION.getMessage());
            }catch (Exception e){
                return makeAlert(Alert.AlertType.ERROR,"ERRO", AlertMessages.MESSAGE_NOT_FOUUND.getMessage());
            }
        }
        return makeAlert(Alert.AlertType.WARNING,"ATENÇÃO", AlertMessages.MESSAGE_BLANK_SPACE.getMessage());
    }

    private void setDescriptionAvailable(ActionEvent actionEvent) {
        if(addDescricaoCheckBox.isSelected()){
            descricaoTextArea.setDisable(false);}
        else{
            descricaoTextArea.setDisable(true);
            descricaoTextArea.setText("");}
    }

    private boolean canSaveInteraction(LinkedList<Medicamento> medicamentos){
        Optional<InteracaoMedicamentosa> interacao = interacaoMedicamentosaService.getInteracaoNames(
                medicamentos.getFirst().getNome(), medicamentos.getLast().getNome());
        if(interacao.isEmpty()) {
            interacaoMedicamentosaService.saveInteraction(descricaoTextArea.getText(),medicamentos.getFirst().getId(), medicamentos.getLast().getId());
            return true;
        }
        return false;
    }

}
