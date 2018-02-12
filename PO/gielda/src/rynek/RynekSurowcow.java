package rynek;

import aktywa.Surowiec;

/**
 * Rynek aktywów które są surowcami
 */
public class RynekSurowcow extends Rynek<Surowiec> {
    public RynekSurowcow(String nazwa, double marzaproc) {
        super(nazwa, marzaproc);
    }
}
