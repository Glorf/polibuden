package helpery;

import aktywa.Spolka;
import rynek.Gielda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Indeks grupujący n najlepszych spółek na danej giełdzie
 */
public class IndeksNajlepsze extends Indeks {
    private int nSpolek;
    private Gielda gielda;

    public IndeksNajlepsze(String nazwa, Gielda g, int n) {
        super(nazwa);
        this.nSpolek = n;
        this.gielda = g;
    }

    public void refresh() {
        List<Spolka> list = new ArrayList<>();
        list.addAll(gielda.getAktywa());
        Collections.sort(list);

        getSpolki().clear();

        for(int i=gielda.getAktywa().size()-1; i>gielda.getAktywa().size()-nSpolek; i--)
            getSpolki().add(gielda.getAktywa().get(i));
    }
}
