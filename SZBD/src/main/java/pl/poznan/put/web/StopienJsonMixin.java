package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import pl.poznan.put.model.Stopien;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import pl.poznan.put.model.Wykladowca;

/**
 * = StopienJsonMixin
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = Stopien.class)
public abstract class StopienJsonMixin {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonIgnore
    private Set<Wykladowca> wykladowcy;

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
}
