package intmedsys.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class BuscaAvancadaController implements Initializable {
    @FXML
    private AnchorPane BAroot;
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

    }
}
