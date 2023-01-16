package tvz.main;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import tvz.entiteti.Citac;
import tvz.entiteti.Profesor;

import java.util.ArrayList;
import java.util.List;

public class ProfesoriController {

    @FXML
    TextField imeTextField;

    @FXML
    TextField prezimeTextField;

    @FXML
    TextField sifraTextField;

    @FXML
    TextField titulaTextField;

    @FXML
    private TableView<Profesor> profesoriTableView;

    @FXML
    TableColumn<Profesor, String> imeTableColumn;

    @FXML
    TableColumn<Profesor, String> prezimeTableColumn;

    @FXML
    TableColumn<Profesor, String> sifraTableColumn;

    @FXML
    TableColumn<Profesor, String> titulaTableColumn;

    private static List<Profesor> profesori = new ArrayList<>();

    @FXML
    public void initialize() {
        profesori.clear();

        profesori = Citac.dodajProfesore();

        imeTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIme());
        });

        prezimeTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getPrezime());
        });

        sifraTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getSifra());
        });

        titulaTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getTitula());
        });

        ObservableList<Profesor> profesoriObservableList = FXCollections.observableList(profesori);

        profesoriTableView.setItems(profesoriObservableList);
    }

    public void filter() {
        List<Profesor> filtriraniProfesori = profesori.stream()
                .filter(prof -> prof.getIme().contains(imeTextField.getText()))
                .filter(prof -> prof.getPrezime().contains(prezimeTextField.getText()))
                .filter(prof -> prof.getSifra().contains(sifraTextField.getText()))
                .filter(prof -> prof.getTitula().contains(titulaTextField.getText()))
                .toList();

        ObservableList<Profesor> profesoriObservableList = FXCollections.observableList(filtriraniProfesori);
        profesoriTableView.setItems(profesoriObservableList);
    }
}