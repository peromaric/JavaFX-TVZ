package tvz.entiteti.sucelja;

import tvz.entiteti.Ispit;

public sealed interface Online permits Ispit {
    void inputNazivSoftvera(String nazivSoftvera);
}
