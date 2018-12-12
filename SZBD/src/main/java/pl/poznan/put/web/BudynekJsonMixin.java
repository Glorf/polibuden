package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import pl.poznan.put.model.Budynek;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import pl.poznan.put.model.Sala;

/**
 * = BudynekJsonMixin
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = Budynek.class)
public abstract class BudynekJsonMixin {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonIgnore
    private Set<Sala> sale;

    /**
     * TODO Auto-generated method documentation
     *
     * @return Set
     */
    public Set<Sala> getSale() {
        return sale;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sale
     */
    public void setSale(Set<Sala> sale) {
        this.sale = sale;
    }
}
