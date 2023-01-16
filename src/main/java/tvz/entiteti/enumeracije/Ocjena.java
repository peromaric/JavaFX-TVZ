package tvz.entiteti.enumeracije;

public enum Ocjena {

    NULA(0, "nepoznato"),
    JEDAN(1, "nedovoljan"),
    DVA(2, "dovoljan"),
    TRI(3, "dobar"),
    CETIRI(4, "vrlo dobar"),
    PET(5, "odličan");

    private final Integer ocjena;
    private final String naziv;

    Ocjena(Integer ocjena, String naziv) {
        this.ocjena = ocjena;
        this.naziv = naziv;
    }

    public Integer getOcjena() {
        return ocjena;
    }
    public static Ocjena parseInt(int ocjenaInt) {
        for (Ocjena ocjena : Ocjena.values()) {
            if(ocjena.getOcjena() == ocjenaInt) {
                return ocjena;
            }
        }

        return Ocjena.NULA;
    }

    public String getNaziv() {
        return naziv;
    }
}
