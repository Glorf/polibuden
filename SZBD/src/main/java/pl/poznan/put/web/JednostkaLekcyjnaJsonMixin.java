package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import pl.poznan.put.model.JednostkaLekcyjna;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import pl.poznan.put.model.Dzien;
import pl.poznan.put.model.Przedmiot;
import pl.poznan.put.model.Sala;
import pl.poznan.put.model.TypZajec;
import pl.poznan.put.model.Wykladowca;

/**
 * = JednostkaLekcyjnaJsonMixin
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = JednostkaLekcyjna.class)
public abstract class JednostkaLekcyjnaJsonMixin {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = WykladowcaDeserializer.class)
    private Wykladowca idProwadzacy;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = PrzedmiotDeserializer.class)
    private Przedmiot idPrzedmiotu;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = SalaDeserializer.class)
    private Sala idSali;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = DzienDeserializer.class)
    private Dzien dzien;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = TypZajecDeserializer.class)
    private TypZajec typ;

    /**
     * TODO Auto-generated method documentation
     *
     * @return Dzien
     */
    public Dzien getDzien() {
        return dzien;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzien
     */
    public void setDzien(Dzien dzien) {
        this.dzien = dzien;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Wykladowca
     */
    public Wykladowca getIdProwadzacy() {
        return idProwadzacy;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param idProwadzacy
     */
    public void setIdProwadzacy(Wykladowca idProwadzacy) {
        this.idProwadzacy = idProwadzacy;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Przedmiot
     */
    public Przedmiot getIdPrzedmiotu() {
        return idPrzedmiotu;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param idPrzedmiotu
     */
    public void setIdPrzedmiotu(Przedmiot idPrzedmiotu) {
        this.idPrzedmiotu = idPrzedmiotu;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Sala
     */
    public Sala getIdSali() {
        return idSali;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param idSali
     */
    public void setIdSali(Sala idSali) {
        this.idSali = idSali;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return TypZajec
     */
    public TypZajec getTyp() {
        return typ;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param typ
     */
    public void setTyp(TypZajec typ) {
        this.typ = typ;
    }
}
