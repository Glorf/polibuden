package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import pl.poznan.put.model.Zespol;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Set;
import pl.poznan.put.model.Wydzial;
import pl.poznan.put.model.Wykladowca;

/**
 * = ZespolJsonMixin
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = Zespol.class)
public abstract class ZespolJsonMixin {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonIgnore
    private Set<Wykladowca> wykladowcy;

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
    public Set<Wykladowca> getWykladowcy() {
        return wykladowcy;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wykladowcy
     */
    public void setWykladowcy(Set<Wykladowca> wykladowcy) {
        this.wykladowcy = wykladowcy;
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
