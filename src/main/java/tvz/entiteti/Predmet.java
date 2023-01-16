package tvz.entiteti;

import tvz.entiteti.sucelja.Unos;
import tvz.sortiranje.StudentSorter;

import java.util.List;
import java.util.stream.Collectors;

public class Predmet extends Entitet implements Unos {

    private String sifra;
    private String naziv;
    private Integer brojEctsBodova;
    private Profesor nositelj;
    private List<Student> studenti;

    public Predmet(Long id, String sifra, String naziv, Integer brojEctsBodova, Profesor nositelj, List<Student> studenti) {
        super(id);
        this.sifra = sifra;
        this.naziv = naziv;
        this.brojEctsBodova = brojEctsBodova;
        this.nositelj = nositelj;
        this.studenti = studenti;
    }

    public Predmet(Long id) {
        super(id);
    }

    public List<Student> getStudentiSorted() {
        return getStudenti().stream().sorted(new StudentSorter()).toList();
    }

    public String getStudentiString() {
        return this.getStudentiSorted().stream()
                .map(student -> student.getIme() + " " + student.getPrezime())
                .collect(Collectors.joining("\n"));
    }
    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getBrojEctsBodova() {
        return brojEctsBodova;
    }

    public void setBrojEctsBodova(Integer brojEctsBodova) {
        this.brojEctsBodova = brojEctsBodova;
    }

    public Profesor getNositelj() {
        return nositelj;
    }

    public void setNositelj(Profesor nositelj) {
        this.nositelj = nositelj;
    }

    public List<Student> getStudenti() {
        return studenti;
    }

    public List<Student> getStudentiList() {
        return studenti.stream().toList();
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }
}
