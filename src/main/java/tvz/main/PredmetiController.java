package tvz.main;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import tvz.entiteti.Citac;
import tvz.entiteti.Predmet;
import tvz.entiteti.Profesor;
import tvz.entiteti.Student;

import java.util.ArrayList;
import java.util.List;

public class PredmetiController {

    @FXML
    TextField sifraTextField;

    @FXML
    TextField nazivTextField;

    @FXML
    TextField brojEctsBodovaTextField;

    @FXML
    TextField nositeljTextField;

    @FXML
    TextField studentTextField;

    @FXML
    private TableView<Predmet> predmetiTableView;

    @FXML
    TableColumn<Predmet, String> sifraTableColumn;

    @FXML
    TableColumn<Predmet, String> nazivTableColumn;

    @FXML
    TableColumn<Predmet, String> brojEctsBodovaTableColumn;

    @FXML
    TableColumn<Predmet, String> noisteljTableColumn;

    @FXML
    TableColumn<Predmet, String> studentiTableColumn;

    private static List<Predmet> predmeti = new ArrayList<>();

    @FXML
    public void initialize() {
        predmeti.clear();
        List<Profesor> profesori = Citac.dodajProfesore();
        List<Student> studenti = Citac.dodajStudente();

        predmeti = Citac.dodajPredmete(profesori, studenti);

        sifraTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getSifra());
        });

        nazivTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getNaziv());
        });

        brojEctsBodovaTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getBrojEctsBodova().toString());
        });

        noisteljTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getNositelj().toString());
        });

        studentiTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getStudentiString());
        });

        ObservableList<Predmet> predmetiObservableList = FXCollections.observableList(predmeti);
        predmetiTableView.setItems(predmetiObservableList);
    }

    public void filter() {
        List<Predmet> filtriraniPredmeti = predmeti.stream()
                .filter(pred -> pred.getNositelj().toString().contains(nositeljTextField.getText()))
                .filter(prof -> prof.getNaziv().contains(nazivTextField.getText()))
                .filter(prof -> prof.getSifra().contains(sifraTextField.getText()))
                .filter(prof -> prof.getStudentiString().contains(studentTextField.getText()))
                .filter(prof -> prof.getBrojEctsBodova().toString().contains(brojEctsBodovaTextField.getText()))
                .toList();

        ObservableList<Predmet> predmetiObservableList = FXCollections.observableList(filtriraniPredmeti);
        predmetiTableView.setItems(predmetiObservableList);
    }
}