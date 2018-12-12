package pl.poznan.put.config.jackson;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDomainModelModule;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.jackson.JsonComponent;
import pl.poznan.put.model.Budynek;
import pl.poznan.put.model.Dzien;
import pl.poznan.put.model.Grupa;
import pl.poznan.put.model.JednostkaLekcyjna;
import pl.poznan.put.model.Kierunek;
import pl.poznan.put.model.Literatura;
import pl.poznan.put.model.Przedmiot;
import pl.poznan.put.model.Sala;
import pl.poznan.put.model.Stopien;
import pl.poznan.put.model.Student;
import pl.poznan.put.model.TypZajec;
import pl.poznan.put.model.Wydzial;
import pl.poznan.put.model.Wykladowca;
import pl.poznan.put.model.Zespol;
import pl.poznan.put.web.BudynekJsonMixin;
import pl.poznan.put.web.DzienJsonMixin;
import pl.poznan.put.web.GrupaJsonMixin;
import pl.poznan.put.web.JednostkaLekcyjnaJsonMixin;
import pl.poznan.put.web.KierunekJsonMixin;
import pl.poznan.put.web.LiteraturaJsonMixin;
import pl.poznan.put.web.PrzedmiotJsonMixin;
import pl.poznan.put.web.SalaJsonMixin;
import pl.poznan.put.web.StopienJsonMixin;
import pl.poznan.put.web.StudentJsonMixin;
import pl.poznan.put.web.TypZajecJsonMixin;
import pl.poznan.put.web.WydzialJsonMixin;
import pl.poznan.put.web.WykladowcaJsonMixin;
import pl.poznan.put.web.ZespolJsonMixin;

/**
 * = DomainModelModule
 *
 * TODO Auto-generated class documentation
 *
 */
@RooDomainModelModule
@JsonComponent
public class DomainModelModule extends SimpleModule {

    /**
     * TODO Auto-generated constructor documentation
     *
     */
    public DomainModelModule() {
        // Mixin registration
        setMixInAnnotation(Budynek.class, BudynekJsonMixin.class);
        setMixInAnnotation(Dzien.class, DzienJsonMixin.class);
        setMixInAnnotation(Grupa.class, GrupaJsonMixin.class);
        setMixInAnnotation(JednostkaLekcyjna.class, JednostkaLekcyjnaJsonMixin.class);
        setMixInAnnotation(Kierunek.class, KierunekJsonMixin.class);
        setMixInAnnotation(Literatura.class, LiteraturaJsonMixin.class);
        setMixInAnnotation(Przedmiot.class, PrzedmiotJsonMixin.class);
        setMixInAnnotation(Sala.class, SalaJsonMixin.class);
        setMixInAnnotation(Stopien.class, StopienJsonMixin.class);
        setMixInAnnotation(Student.class, StudentJsonMixin.class);
        setMixInAnnotation(TypZajec.class, TypZajecJsonMixin.class);
        setMixInAnnotation(Wydzial.class, WydzialJsonMixin.class);
        setMixInAnnotation(Wykladowca.class, WykladowcaJsonMixin.class);
        setMixInAnnotation(Zespol.class, ZespolJsonMixin.class);
    }
}
