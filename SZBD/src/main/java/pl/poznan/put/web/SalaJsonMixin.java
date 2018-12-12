package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import pl.poznan.put.model.Sala;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Set;
import pl.poznan.put.model.Budynek;
import pl.poznan.put.model.JednostkaLekcyjna;

/**
 * = SalaJsonMixin
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = Sala.class)
public abstract class SalaJsonMixin {

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
    @JsonDeserialize(using = BudynekDeserializer.class)
    private Budynek budynek;

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
     * @return Budynek
     */
    public Budynek getBudynek() {
        return budynek;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     */
    public void setBudynek(Budynek budynek) {
        this.budynek = budynek;
    }
}
