package intmedsys.controller;

import intmedsys.IntMedSys;
import intmedsys.model.AlertMessages;
import intmedsys.model.InteracaoMedicamentosa;
import intmedsys.model.Medicamento;
import intmedsys.repository.MedicametoRepository;
import intmedsys.service.InteracaoMedicamentosaService;
import intmedsys.service.MedicamentoService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import net.rgielen.fxweaver.core.FxmlView;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InteractionController implements Initializable {
    @FXML AnchorPane root;
    @FXML private TextField medicamentoA;
    @FXML private TextField medicamentoB;
    @FXML private TextArea descricaoField;
    @FXML private Label statusLabel;
    @FXML private Button buscar;
    @FXML private Label buscaAvancadaMenuItem;
    @FXML private Label buscaMenuItem;
    @Autowired private MedicamentoService medicamentoService;
    @Autowired private InteracaoMedicamentosaService interacaoMedicamentosaService;
    private List<String> all;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextFields.bindAutoCompletion(medicamentoA, this.getAll());
        TextFields.bindAutoCompletion(medicamentoB, this.getAll());

        buscar.setOnAction(event ->{
            statusLabel.setText("");
            if(medicamentoA.getText().isBlank() || medicamentoB.getText().isBlank()){
                Alert aBlank = getAlert(Alert.AlertType.WARNING,"Atenção", AlertMessages.MESSAGE_BLANK_SPACE.getMessage());
                aBlank.show();
            }else{
                try {
                    Optional<InteracaoMedicamentosa> interacao = interacaoMedicamentosaService.getInteracao(medicamentoA.getText(),medicamentoB.getText());
                    if (interacao.isPresent()) {
                        statusLabel.setText("EXISTE INTERAÇÃO ENTRE ESTES MEDICAMENTOS");
                        statusLabel.setTextFill(Paint.valueOf("#ff1808"));
                    }else{
                        statusLabel.setText("SEM INTERAÇÃO");
                        statusLabel.setTextFill(Paint.valueOf("#75db00"));
                        Alert aNoInt = getAlert(Alert.AlertType.INFORMATION, "Atenção", AlertMessages.MESSAGE_NO_INTERACTION.getMessage());
                        aNoInt.show();
                    }
                }catch (IllegalStateException | NoSuchElementException ex){
                   Alert aNotFound = getAlert(Alert.AlertType.ERROR,"Atenção",AlertMessages.MESSAGE_NOT_FOUUND.getMessage());
                   aNotFound.show();
                } } });

        buscaAvancadaMenuItem.setOnMouseClicked(ev->{
            root.getChildren().setAll(getAnchorPane("/fxml/buscaAvancada.fxml"));
        });

    }

    private AnchorPane getAnchorPane(String path) {
        AnchorPane pane = null;
        try {pane = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) { e.printStackTrace();}
        return pane;
    }

    public List<String> getAll() {
        return medicamentoService.getAll();
    }
    private Alert getAlert(Alert.AlertType type, String headerMessage, String contentMessage){
        Alert alert = new Alert(type);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeaderText(headerMessage);
        alert.setContentText(contentMessage);
        return  alert;
    }


}
