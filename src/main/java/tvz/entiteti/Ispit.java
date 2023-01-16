package tvz.entiteti;

import tvz.entiteti.enumeracije.Ocjena;
import tvz.entiteti.sucelja.Online;
import tvz.entiteti.sucelja.Unos;
import tvz.entiteti.zapisi.Dvorana;

import java.time.LocalDateTime;

public final class Ispit extends Entitet implements Online, Unos {

    private Predmet predmet;
    private Student student;
    private Ocjena ocjena;
    private LocalDateTime datumIVrijeme;
    private Dvorana dvorana;
    private String nazivSoftvera;

    public Ispit(Long id, Predmet predmet, Student student, Ocjena ocjena, LocalDateTime datumIVrijeme, Dvorana dvorana) {
        super(id);
        this.predmet = predmet;
        this.student = student;
        this.ocjena = ocjena;
        this.datumIVrijeme = datumIVrijeme;
        this.dvorana = dvorana;
    }

    public void inputNazivSoftvera(String nazivSoftvera) {
        this.nazivSoftvera = nazivSoftvera;
    }

    public Dvorana getDvorana() {
        return dvorana;
    }

    public void setDvorana(Dvorana dvorana) {
        this.dvorana = dvorana;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Ocjena getOcjena() {
        return ocjena;
    }

    public void setOcjena(Ocjena ocjena) {
        this.ocjena = ocjena;
    }

    public String getNazivSoftvera() {
        return nazivSoftvera;
    }

    public void setNazivSoftvera(String nazivSoftvera) {
        this.nazivSoftvera = nazivSoftvera;
    }

    public LocalDateTime getDatumIVrijeme() {
        return datumIVrijeme;
    }

    public void setDatumIVrijeme(LocalDateTime datumIVrijeme) {
        this.datumIVrijeme = datumIVrijeme;
    }
}
