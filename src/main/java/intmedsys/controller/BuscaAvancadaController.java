package intmedsys.controller;

import intmedsys.model.Medicamento;
import intmedsys.service.InteracaoMedicamentosaService;
import intmedsys.service.MedicamentoService;
import intmedsys.utils.screen.ScreenManager;
import intmedsys.utils.screen.ScreenPath;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class BuscaAvancadaController implements Initializable, ScreenManager {
    @FXML private Label buscaMenuItem;
    @FXML private TextField medicamento;
    @FXML private TableView<Medicamento> tabela;
    @FXML private TableColumn<Medicamento, String> coluna;
    @FXML private Button buscar;
    @Autowired MedicamentoService medicamentoService;
    @Autowired InteracaoMedicamentosaService interacaoMedicamentosaService;
    private List<Medicamento> meds;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextFields.bindAutoCompletion(medicamento, medicamentoService.getAll());
        this.meds = medicamentoService.getAllMeds();

        buscaMenuItem.setOnMouseClicked(ev->changeScene(ScreenPath.BUSCA_MEDICAMENTO));

        buscar.setOnAction(actionEvent -> searchInteractionList());

        tabela.setOnMouseClicked(this::refreshTableList);
    }

    private void searchInteractionList() {
        coluna.setCellValueFactory(new PropertyValueFactory<>("nome"));
        if(!medicamento.getText().isBlank()) {
            tabela.setItems(FXCollections.observableList(parseResultList(medicamento.getText())));
        }
    }

    private void refreshTableList(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() ==2){
            medicamento.setText(tabela.getSelectionModel().getSelectedItem().getNome());
            tabela.setItems(FXCollections.observableList(
                    parseResultList(tabela.getSelectionModel().getSelectedItem().getNome()))
            );
        }
    }

    private List<Medicamento> parseResultList(String name){
        Medicamento medicamento = medicamentoService.getByName(name.strip().toUpperCase());
        ArrayList<Medicamento> listMeds = new ArrayList<Medicamento>();
        List<Long> interactionList = interacaoMedicamentosaService.getInteractionList(medicamento.getId());
        interactionList.forEach(it->{
            Optional<Medicamento> first = this.meds.stream().filter(m -> m.getId() == it).findFirst();
            first.ifPresent(listMeds::add);
        });
        return listMeds;
    }


}
