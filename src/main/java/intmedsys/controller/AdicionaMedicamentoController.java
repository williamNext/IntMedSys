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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class AdicionaMedicamentoController implements Initializable, ScreenManager, AlertManager {
    @FXML private MenuItem buscaAvancadaMenuItem;
    @FXML private MenuItem buscaMenuItem;
    @FXML private MenuItem editarInteracaoMenuItem;
    @FXML private MenuItem removeInteracaoMenuItem;
    @FXML private MenuItem addInteracaoMenuItem;
    @FXML private  MenuItem addMedMenuItem;
    @FXML private  MenuItem removeMedMenuItem;
    @FXML private TextField nomeMedicamento;
    @FXML private TableView<Medicamento> tabelaMedicamentos;
    @FXML private TableColumn<Medicamento, String> colunaMedicamentos;
    @FXML private Button addMedicamentoBtn;
    @FXML private TextField filtroTextField;
    @FXML private Button filtroBtn;
    @Autowired private MedicamentoService medicamentoService;
    @Autowired private InteracaoMedicamentosaService interacaoMedicamentosaService;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colunaMedicamentos.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabelaMedicamentos.setItems(FXCollections.observableList(setOnLoadTableList()));
        filtroBtn.setOnAction(ev->refreshTableList(filtroTextField.getText()));
        removeMedMenuItem.setOnAction(actionEvent -> changeScene(ScreenPath.REMOVE_MEDICAMENTO));
        buscaAvancadaMenuItem.setOnAction(ev->changeScene(ScreenPath.BUSCA_AVANCADA));
        buscaMenuItem.setOnAction(ev->changeScene(ScreenPath.BUSCA_MEDICAMENTO));
        addMedicamentoBtn.setOnAction(ev-> makeThings());
        addInteracaoMenuItem.setOnAction(ev->changeScene(ScreenPath.ADICIONA_INTERACAO));
        removeInteracaoMenuItem.setOnAction(ev -> changeScene(ScreenPath.REMOVE_INTERACAO));
        editarInteracaoMenuItem.setOnAction(ev->changeScene(ScreenPath.EDITA_INTERACAO));
    }

    private void makeThings() {
        Alert alert = checaErros();
        if(alert!= null){
            alert.show();
        }else{
            Alert confirm = makeAlert(Alert.AlertType.WARNING, "ATENÇÃO", AlertMessages.MESSAGE_SAVE_MED_CONFIRMATION.getMessage());
            Optional<ButtonType> result = confirm.showAndWait();
            result.ifPresent(r-> {
                if(result.get() == ButtonType.OK){
                   saveMedicamento(nomeMedicamento.getText());
                    makeAlert(Alert.AlertType.INFORMATION, "SUCESSO", AlertMessages.MESSAGE_MED_DELETE_SUCESS.getMessage()).show();
                }
            });
        }
    }

    private Alert checaErros(){
        if(isVazio(nomeMedicamento)){
          return  makeAlert(Alert.AlertType.WARNING,"ATENÇÃO", AlertMessages.MESSAGE_BLANK_SPACE.getMessage());
        }else if (isExistente()){
          return  makeAlert(Alert.AlertType.ERROR, "ERRO",AlertMessages.MESSAGE_SAME_MED_FAIL.getMessage());
        }
        return null;
    }

    private boolean isVazio(TextField campo){
        return campo.getText().isBlank();
    }

    private boolean isExistente(){
        try{
            medicamentoService.getByName(nomeMedicamento.getText().strip().toUpperCase());
            return true;
        }catch (Exception e){}
        return false;
    }

    private void saveMedicamento(String nome){
        medicamentoService.save(new Medicamento(nome.strip().toUpperCase()));
    }

    private List<Medicamento> setOnLoadTableList(){
        return medicamentoService.getAll();
    }

    private void refreshTableList(String prefix){
        List<Medicamento> collect = medicamentoService.getAll()
                .stream()
                .filter(i -> i.getNome().startsWith(prefix.strip().toUpperCase()))
                .collect(Collectors.toList());
        tabelaMedicamentos.setItems(FXCollections.observableList(collect));
    }
}
