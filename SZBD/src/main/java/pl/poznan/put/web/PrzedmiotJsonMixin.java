package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import pl.poznan.put.model.Przedmiot;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import pl.poznan.put.model.JednostkaLekcyjna;
import pl.poznan.put.model.Literatura;

/**
 * = PrzedmiotJsonMixin
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = Przedmiot.class)
public abstract class PrzedmiotJsonMixin {

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
    @JsonIgnore
    private Set<Literatura> literatura;

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
     * @return Set
     */
    public Set<Literatura> getLiteratura() {
        return literatura;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param literatura
     */
    public void setLiteratura(Set<Literatura> literatura) {
        this.literatura = literatura;
    }
}
