package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import pl.poznan.put.model.Wydzial;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import pl.poznan.put.model.Kierunek;
import pl.poznan.put.model.Zespol;

/**
 * = WydzialJsonMixin
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = Wydzial.class)
public abstract class WydzialJsonMixin {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonIgnore
    private Set<Kierunek> kierunki;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonIgnore
    private Set<Zespol> zespoly;

    /**
     * TODO Auto-generated method documentation
     *
     * @return Set
     */
    public Set<Kierunek> getKierunki() {
        return kierunki;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunki
     */
    public void setKierunki(Set<Kierunek> kierunki) {
        this.kierunki = kierunki;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Set
     */
    public Set<Zespol> getZespoly() {
        return zespoly;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespoly
     */
    public void setZespoly(Set<Zespol> zespoly) {
        this.zespoly = zespoly;
    }
}
