package aktywa;

import helpery.Kraj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Aktywo - waluta
 */
public class Waluta implements Serializable {
    private String nazwa;
    private List<Kraj> kraje;

    public Waluta(String nazwa) {
        this.nazwa = nazwa;
        kraje = new ArrayList<>();
    }

    @Override
    public String toString() {
        return nazwa;
    }

    public void addKraje(Kraj kraj) {
        kraje.add(kraj);
    }
    public List<Kraj> getKraje() {
        return kraje;
    }

    public String getNazwa() {
        return nazwa;
    }
}
