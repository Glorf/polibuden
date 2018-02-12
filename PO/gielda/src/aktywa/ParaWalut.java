package aktywa;

/**
 * Aktywo - para walut
 */
public class ParaWalut extends Aktywa {
    private Waluta walutaZ;
    private Waluta walutaNa;

    public ParaWalut(Waluta z, Waluta na, double cena) {
        super(z.getNazwa()+"/"+na.getNazwa(), cena);
        walutaZ = z;
        walutaNa = na;
    }

    public Waluta getWalutaNa() {
        return walutaNa;
    }

    public Waluta getWalutaZ() {
        return walutaZ;
    }
}
