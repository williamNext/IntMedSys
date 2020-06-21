package intmedsys.controller;

import intmedsys.utils.alerts.AlertManager;
import intmedsys.utils.screen.ScreenManager;
import intmedsys.utils.screen.ScreenPath;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;
@Component
public class RemoveInteracaoController implements Initializable, ScreenManager, AlertManager {
    @FXML private Label buscaAvancadaMenuItem;
    @FXML private Label buscaMenuItem;
    @FXML private MenuItem removeInteracaoMenuItem;
    @FXML private MenuItem addInteracaoMenuItem;
    @FXML private  MenuItem addMedMenuItem;
    @FXML private  MenuItem removeMedMenuItem;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buscaAvancadaMenuItem.setOnMouseClicked(ev->changeScene(ScreenPath.BUSCA_AVANCADA));
        addInteracaoMenuItem.setOnAction(ev->changeScene(ScreenPath.ADICIONA_INTERACAO));
        buscaMenuItem.setOnMouseClicked(ev->changeScene(ScreenPath.BUSCA_MEDICAMENTO));
        addMedMenuItem.setOnAction(actionEvent -> changeScene(ScreenPath.ADICIONA_MEDICAMENTO));
    }
}
