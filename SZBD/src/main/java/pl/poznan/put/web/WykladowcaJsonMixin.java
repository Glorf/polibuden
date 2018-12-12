package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import pl.poznan.put.model.Wykladowca;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Set;
import pl.poznan.put.model.JednostkaLekcyjna;
import pl.poznan.put.model.Stopien;
import pl.poznan.put.model.Zespol;

/**
 * = WykladowcaJsonMixin
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = Wykladowca.class)
public abstract class WykladowcaJsonMixin {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = ZespolDeserializer.class)
    private Zespol zespol;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonIgnore
    private Set<JednostkaLekcyjna> jednostkiLekcyjne;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = StopienDeserializer.class)
    private Stopien stopien;

    /**
     * TODO Auto-generated method documentation
     *
     * @return Set
     */
    public Set<JednostkaLekcyjna> getJednostkiLekcyjne() {
        return jednostkiLekcyjne;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jednostkiLekcyjne
     */
    public void setJednostkiLekcyjne(Set<JednostkaLekcyjna> jednostkiLekcyjne) {
        this.jednostkiLekcyjne = jednostkiLekcyjne;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Stopien
     */
    public Stopien getStopien() {
        return stopien;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     */
    public void setStopien(Stopien stopien) {
        this.stopien = stopien;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Zespol
     */
    public Zespol getZespol() {
        return zespol;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespol
     */
    public void setZespol(Zespol zespol) {
        this.zespol = zespol;
    }
}
