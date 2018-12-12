package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import pl.poznan.put.model.Kierunek;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Set;
import pl.poznan.put.model.Grupa;
import pl.poznan.put.model.Wydzial;

/**
 * = KierunekJsonMixin
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = Kierunek.class)
public abstract class KierunekJsonMixin {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonIgnore
    private Set<Grupa> grupy;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = WydzialDeserializer.class)
    private Wydzial wydzial;

    /**
     * TODO Auto-generated method documentation
     *
     * @return Set
     */
    public Set<Grupa> getGrupy() {
        return grupy;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupy
     */
    public void setGrupy(Set<Grupa> grupy) {
        this.grupy = grupy;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Wydzial
     */
    public Wydzial getWydzial() {
        return wydzial;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     */
    public void setWydzial(Wydzial wydzial) {
        this.wydzial = wydzial;
    }
}
