package tvz.entiteti.enumeracije;

public enum TipObrazovneUstanove {
    NEPOZNATO(0),
    VISOKOSKOLSKA(1),
    FAKULTET(2);

    private final Integer tipInt;

    TipObrazovneUstanove(Integer tipInt) {
        this.tipInt = tipInt;
    }

    public static TipObrazovneUstanove parseInt(int broj) {
        for (TipObrazovneUstanove tip : TipObrazovneUstanove.values()) {
            if(tip.getTipInt() == broj) {
                return tip;
            }
        }

        return TipObrazovneUstanove.NEPOZNATO;
    }

    public Integer getTipInt() {
        return tipInt;
    }
}