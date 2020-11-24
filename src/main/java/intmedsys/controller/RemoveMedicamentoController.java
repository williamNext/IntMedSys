package intmedsys.controller;

import intmedsys.model.Medicamento;
import intmedsys.service.InteracaoMedicamentosaService;
import intmedsys.service.MedicamentoService;
import intmedsys.utils.alerts.AlertManager;
import intmedsys.utils.alerts.AlertMessages;
import intmedsys.utils.screen.ScreenManager;
import intmedsys.utils.screen.ScreenPath;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class RemoveMedicamentoController implements Initializable, ScreenManager, AlertManager {
    @FXML private MenuItem buscaAvancadaMenuItem;
    @FXML private MenuItem buscaMenuItem;
    @FXML private MenuItem removeInteracaoMenuItem;
    @FXML private MenuItem addInteracaoMenuItem;
    @FXML private MenuItem addMedMenuItem;
    @FXML private TextField nomeMedicamento;
    @FXML private TableView<Medicamento> tabelaMedicamentos;
    @FXML private MenuItem editarInteracaoMenuItem;
    @FXML private TableColumn<Medicamento, String> colunaMedicamentos;
    @FXML private Button removeMedicamentoBtn;
    @FXML private TextField filtroTextField;
    @FXML private Button filtroBtn;
    @Autowired private MedicamentoService medicamentoService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextFields.bindAutoCompletion( nomeMedicamento, medicamentoService.getAllMedsNames());
        colunaMedicamentos.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabelaMedicamentos.setItems(FXCollections.observableList(setOnLoadTableList()));
        filtroBtn.setOnAction(ev -> refreshTableList(filtroTextField.getText()));
        buscaAvancadaMenuItem.setOnAction(ev -> changeScene(ScreenPath.BUSCA_AVANCADA));
        addInteracaoMenuItem.setOnAction(ev -> changeScene(ScreenPath.ADICIONA_INTERACAO));
        buscaMenuItem.setOnAction(ev -> changeScene(ScreenPath.BUSCA_MEDICAMENTO));
        removeMedicamentoBtn.setOnAction(ev -> makeThings());
        removeInteracaoMenuItem.setOnAction(ev -> changeScene(ScreenPath.REMOVE_INTERACAO));
        addMedMenuItem.setOnAction(ev -> changeScene(ScreenPath.ADICIONA_MEDICAMENTO));
        editarInteracaoMenuItem.setOnAction(ev->changeScene(ScreenPath.EDITA_INTERACAO));
    }

    private void makeThings() {
        Alert alert = checaErros();
        if (alert != null) {
            alert.show();
        } else {
            Alert confirm = makeAlert(Alert.AlertType.WARNING, "ATENÇÃO", AlertMessages.MESSAGE_CONFIRM_MED_DELETE.getMessage());
            Optional<ButtonType> result = confirm.showAndWait();
            result.ifPresent(r -> {
                if (result.get() == ButtonType.OK) {
                    deleteMedicamento(getMed());
                    makeAlert(Alert.AlertType.INFORMATION, "SUCESSO", AlertMessages.MESSAGE_MED_DELETE_SUCESS.getMessage()).show();
                }
            });
        }
    }

    private Alert checaErros() {
        if (isVazio(nomeMedicamento)) {
            return makeAlert(Alert.AlertType.WARNING, "ATENÇÃO", AlertMessages.MESSAGE_BLANK_SPACE.getMessage());
        } else if (!isExistente()) {
            return makeAlert(Alert.AlertType.ERROR, "ERRO", AlertMessages.MESSAGE_NOT_FOUUND.getMessage());
        }
        return null;
    }

    private boolean isVazio(TextField campo) {
        return campo.getText().isBlank();
    }

    private boolean isExistente() {
        try {
            medicamentoService.getByName(nomeMedicamento.getText().strip().toUpperCase());
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    private Medicamento getMed(){
        return medicamentoService.getByName(nomeMedicamento.getText().strip().toUpperCase());
    }

    private void deleteMedicamento(Medicamento medicamento) {
        medicamentoService.delete(medicamento);
    }

    private List<Medicamento> setOnLoadTableList() {
        return medicamentoService.getAll();
    }

    private void refreshTableList(String prefix) {
        List<Medicamento> collect = medicamentoService.getAll()
                .stream()
                .filter(i -> i.getNome().startsWith(prefix.strip().toUpperCase()))
                .collect(Collectors.toList());
        tabelaMedicamentos.setItems(FXCollections.observableList(collect));
    }
}