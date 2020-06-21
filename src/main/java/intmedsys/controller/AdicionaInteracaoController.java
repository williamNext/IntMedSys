package intmedsys.controller;

import intmedsys.utils.alerts.AlertManager;
import intmedsys.utils.screen.ScreenManager;
import intmedsys.utils.screen.ScreenPath;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AdicionaInteracaoController  implements Initializable, ScreenManager, AlertManager {
    @FXML private TextField medicamentoA;
    @FXML private TextField medicamentoB;
    @FXML private Button btnSalvar;
    @FXML private TextArea descricaoTextArea;
    @FXML private CheckBox addDescricaoCheckBox;
    @FXML private Label buscaMenuItem;
    @FXML private Label buscaAvancadaMenuItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        descricaoTextArea.setDisable(true);
        buscaMenuItem.setOnMouseClicked(ev->changeScene(ScreenPath.BUSCA_MEDICAMENTO));
        buscaAvancadaMenuItem.setOnMouseClicked(ev->changeScene(ScreenPath.BUSCA_AVANCADA));
        addDescricaoCheckBox.setOnAction(this::setDescriptionAvailable);
    }

    private void setDescriptionAvailable(ActionEvent actionEvent) {
        if(addDescricaoCheckBox.isSelected()){
            descricaoTextArea.setDisable(false);}
        else{
            descricaoTextArea.setDisable(true);
            descricaoTextArea.setText("");}
    }

}
