package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import pl.poznan.put.model.Grupa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Set;
import pl.poznan.put.model.JednostkaLekcyjna;
import pl.poznan.put.model.Kierunek;
import pl.poznan.put.model.Student;

/**
 * = GrupaJsonMixin
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = Grupa.class)
public abstract class GrupaJsonMixin {

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
    @JsonDeserialize(using = KierunekDeserializer.class)
    private Kierunek kierunek;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonIgnore
    private Set<Student> studenci;

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
    public Set<Student> getStudenci() {
        return studenci;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param studenci
     */
    public void setStudenci(Set<Student> studenci) {
        this.studenci = studenci;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Kierunek
     */
    public Kierunek getKierunek() {
        return kierunek;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     */
    public void setKierunek(Kierunek kierunek) {
        this.kierunek = kierunek;
    }
}
