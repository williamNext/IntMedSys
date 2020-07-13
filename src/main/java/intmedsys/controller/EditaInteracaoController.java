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
public class EditaInteracaoController implements Initializable, ScreenManager, AlertManager {
    @FXML private TextField medicamentoA;
    @FXML private TextField medicamentoB;
    @FXML private Button btnSalvar;
    @FXML private Button buscarBtn;
    @FXML private TextArea descricaoTextArea;
    @FXML private MenuItem buscaMenuItem;
    @FXML private MenuItem buscaAvancadaMenuItem;
    @FXML private MenuItem removeInteracaoMenuItem;
    @FXML private MenuItem addInteracaoMenuItem;
    @FXML private  MenuItem addMedMenuItem;
    @FXML private  MenuItem removeMedMenuItem;
    @Autowired
    private InteracaoMedicamentosaService interacaoMedicamentosaService;
    @Autowired
    MedicamentoService medicamentoService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextFields.bindAutoCompletion(medicamentoA, medicamentoService.getAllMedsNames());
        TextFields.bindAutoCompletion(medicamentoB, medicamentoService.getAllMedsNames());
        descricaoTextArea.setDisable(true);
        this.btnSalvar.setDisable(true);
        buscaMenuItem.setOnAction(ev->changeScene(ScreenPath.BUSCA_MEDICAMENTO));
        buscaAvancadaMenuItem.setOnAction(ev->changeScene(ScreenPath.BUSCA_AVANCADA));
        buscarBtn.setOnAction(ev-> buscaInteraction().show());
        addMedMenuItem.setOnAction(ev -> changeScene(ScreenPath.ADICIONA_MEDICAMENTO));
        addInteracaoMenuItem.setOnAction(ev-> changeScene(ScreenPath.ADICIONA_INTERACAO));
        removeInteracaoMenuItem.setOnAction(ev -> changeScene(ScreenPath.REMOVE_INTERACAO));
        removeMedMenuItem.setOnAction(actionEvent -> changeScene(ScreenPath.REMOVE_MEDICAMENTO));
        btnSalvar.setOnAction(ev->saveDescription());
    }

    private Alert buscaInteraction() {
        if(!medicamentoA.getText().isBlank() && !medicamentoB.getText().isBlank()){
            try{
                LinkedList<Medicamento> medicamentos = medicamentoService.getMedicamentos(medicamentoA.getText(), medicamentoB.getText());
                if(hasInteraction(medicamentos)){
                    setDescriptionAvailable(true);
                    return makeAlert(Alert.AlertType.INFORMATION,"ATENÇÃO", AlertMessages.MESSAGE_EDITAVEL_DESCRIPTION_.getMessage());}
                else{
                    clearFields();
                    setDescriptionAvailable(false);
                    return  makeAlert(Alert.AlertType.ERROR,"ERRO", AlertMessages.MESSAGE_NO_INTERACTION.getMessage());}
            }catch (Exception e){
                clearFields();
                return makeAlert(Alert.AlertType.ERROR,"ERRO", AlertMessages.MESSAGE_NOT_FOUUND.getMessage());
            }
        }
        clearFields();
        return makeAlert(Alert.AlertType.WARNING,"ATENÇÃO", AlertMessages.MESSAGE_BLANK_SPACE.getMessage());
    }

    private void setDescriptionAvailable(boolean isLiberated){
        if(isLiberated){
            descricaoTextArea.setDisable(false);
            this.btnSalvar.setDisable(false);
        }
        else{
            descricaoTextArea.setDisable(true);
            descricaoTextArea.setText("");
            this.medicamentoA.setText("");
            this.medicamentoB.setText("");
        }
    }
    private void saveDescription(){
        try {
            LinkedList<Medicamento> medicamentos = medicamentoService.getMedicamentos(medicamentoA.getText(), medicamentoB.getText());
            interacaoMedicamentosaService.saveDescription(medicamentos.getFirst().getNome(),medicamentos.getLast().getNome(),this.descricaoTextArea.getText());
            makeAlert(Alert.AlertType.INFORMATION,"SUCESSO",AlertMessages.MESSAGE_SAVE_DESCRIPTION.getMessage()).show();
        }catch (Exception e){
            makeAlert(Alert.AlertType.ERROR,"ERRO",AlertMessages.MESSAGE_SAVE_DESCRIPTION_ERROR.getMessage()).show();
        }
        clearFields();
    }

    private void clearFields() {
        this.medicamentoB.setText("");
        this.medicamentoA.setText("");
        this.btnSalvar.setDisable(true);
        this.descricaoTextArea.setDisable(true);
        this.descricaoTextArea.setText("");
    }

    private boolean hasInteraction(LinkedList<Medicamento> medicamentos){
        Optional<InteracaoMedicamentosa> interacao = interacaoMedicamentosaService.getInteracaoNames(
                medicamentos.getFirst().getNome(), medicamentos.getLast().getNome());
        if(interacao.isPresent()){
            this.descricaoTextArea.setText(interacao.get().getDescricao());
            return true;
        }
        return false;
    }
}
