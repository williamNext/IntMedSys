package intmedsys.controller;

import intmedsys.IntMedSys;
import intmedsys.model.InteracaoMedicamentosa;
import intmedsys.model.Medicamento;
import intmedsys.model.MedicamentoDTO;
import intmedsys.service.InteracaoMedicamentosaService;
import intmedsys.service.MedicamentoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import net.bytebuddy.asm.Advice;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class BuscaAvancadaController implements Initializable {
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
        buscaMenuItem.setOnMouseClicked(ev->{
            try {
                IntMedSys.changeScene(new FXMLLoader(getClass().getResource("/fxml/buscarMedicamentos.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        buscar.setOnAction(actionEvent -> {
            coluna.setCellValueFactory(new PropertyValueFactory<>("nome"));
            if(!medicamento.getText().isBlank()) {
                ObservableList<Medicamento> fxCollections = FXCollections.observableList(parseResultList(medicamento.getText()));
                tabela.setItems(fxCollections);
            }
        });
        tabela.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() ==2){
            String selectedItem = tabela.getSelectionModel().getSelectedItem().getNome();
            System.out.println(selectedItem);}
        });
    }

    private List<Medicamento> parseResultList(String name){
        Medicamento medicamento = medicamentoService.getByName(name.strip().toUpperCase());
        ArrayList<Medicamento> listMeds = new ArrayList<Medicamento>();
        List<Long> interactionList = interacaoMedicamentosaService.getInteractionList(medicamento.getId());
        interactionList.forEach(it->{
            Optional<Medicamento> first = this.meds.stream().filter(m -> m.getId() == it).findFirst();
            if(first.isPresent())
                listMeds.add(first.get());
        });
        return listMeds;
    }


}
