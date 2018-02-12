package rynek;

import aktywa.ParaWalut;

/**
 * Rynek aktywów które są walutami
 */
public class RynekWalut extends Rynek<ParaWalut> {
    public RynekWalut(String nazwa, double marzaproc) {
        super(nazwa, marzaproc);
    }
}
