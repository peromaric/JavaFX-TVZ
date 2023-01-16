package tvz.entiteti;

import java.time.LocalDate;
import java.util.Objects;

public class Student extends Osoba {

    private String jmbag;
    private LocalDate datumRodjenja;

    public Student(Long id, String ime, String prezime, String jmbag, LocalDate datumRodjenja) {
        super(id, ime, prezime);
        this.jmbag = jmbag;
        this.datumRodjenja = datumRodjenja;
    }

    public Student(Long id) {
        super(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return jmbag.equals(student.jmbag) && datumRodjenja.equals(student.datumRodjenja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jmbag, datumRodjenja);
    }

    public String getJmbag() {
        return jmbag;
    }

    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }
}
