package helpery;

import java.io.Serializable;

/**
 * Kraj
 */
public class Kraj implements Serializable {
    private String nazwa;
    private String kod;

    public String toString() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    public String getNazwa() {
        return nazwa;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }
    public String getKod() {
        return kod;
    }
}
