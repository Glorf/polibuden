package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import pl.poznan.put.model.Student;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import pl.poznan.put.model.Grupa;

/**
 * = StudentJsonMixin
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = Student.class)
public abstract class StudentJsonMixin {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = GrupaDeserializer.class)
    private Grupa podgrupa;

    /**
     * TODO Auto-generated method documentation
     *
     * @return Grupa
     */
    public Grupa getPodgrupa() {
        return podgrupa;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param podgrupa
     */
    public void setPodgrupa(Grupa podgrupa) {
        this.podgrupa = podgrupa;
    }
}
