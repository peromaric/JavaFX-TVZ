package tvz.main;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import tvz.entiteti.Citac;
import tvz.entiteti.Profesor;
import tvz.entiteti.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentiController {

    @FXML
    TextField imeTextField;

    @FXML
    TextField prezimeTextField;

    @FXML
    TextField jmbagTextField;

    @FXML
    TextField datumTextField;

    @FXML
    private TableView<Student> studentiTableView;

    @FXML
    TableColumn<Student, String> imeTableColumn;

    @FXML
    TableColumn<Student, String> prezimeTableColumn;

    @FXML
    TableColumn<Student, String> jmbagTableColumn;

    @FXML
    TableColumn<Student, String> datumRodenjaTableColumn;

    private static List<Student> studenti = new ArrayList<>();

    @FXML
    public void initialize() {
        studenti.clear();

        studenti = Citac.dodajStudente();

        imeTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIme());
        });

        prezimeTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getPrezime());
        });

        jmbagTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getJmbag());
        });

        datumRodenjaTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDatumRodjenja().toString());
        });

        ObservableList<Student> StudentiObservableList = FXCollections.observableList(studenti);

        studentiTableView.setItems(StudentiObservableList);
    }

    public void filter() {
        List<Student> filtriraniStudenti = studenti.stream()
                .filter(student -> student.getIme().contains(imeTextField.getText()))
                .filter(student -> student.getPrezime().contains(prezimeTextField.getText()))
                .filter(student -> student.getJmbag().contains(jmbagTextField.getText()))
                .filter(student -> student.getDatumRodjenja().toString().contains(datumTextField.getText()))
                .toList();

        ObservableList<Student> profesoriObservableList = FXCollections.observableList(filtriraniStudenti);
        studentiTableView.setItems(profesoriObservableList);
    }
}