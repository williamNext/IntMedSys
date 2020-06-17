package intmedsys.controller;

import intmedsys.IntMedSys;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
@Component
public class BuscaAvancadaController implements Initializable {
    @FXML
    private AnchorPane BAroot;
    @FXML
    private Label buscaMenuItem;
    @FXML
    private TextField medicamento;

    @FXML
    private TableView<?> tabela;

    @FXML
    private TableColumn<?, ?> coluna;

    @FXML
    private Button buscar;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buscaMenuItem.setOnMouseClicked(ev->{
            try {
                IntMedSys.changeScene(new FXMLLoader(getClass().getResource("/fxml/buscarMedicamentos.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Parent getAnchorPane(String path) {
        Parent pane = null;
        try {pane = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) { e.printStackTrace();}
        return pane;
    }
}
