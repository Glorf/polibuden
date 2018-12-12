package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import pl.poznan.put.model.Dzien;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import pl.poznan.put.model.JednostkaLekcyjna;

/**
 * = DzienJsonMixin
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = Dzien.class)
public abstract class DzienJsonMixin {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonIgnore
    private Set<JednostkaLekcyjna> jednostkiLekcyjne;

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
}
