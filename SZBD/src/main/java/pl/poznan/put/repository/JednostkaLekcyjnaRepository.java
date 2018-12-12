package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import pl.poznan.put.model.JednostkaLekcyjna;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.model.Dzien;
import pl.poznan.put.model.Grupa;
import pl.poznan.put.model.Przedmiot;
import pl.poznan.put.model.Sala;
import pl.poznan.put.model.TypZajec;
import pl.poznan.put.model.Wykladowca;

/**
 * = JednostkaLekcyjnaRepository
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = JednostkaLekcyjna.class)
@Transactional
public interface JednostkaLekcyjnaRepository extends DetachableJpaRepository<JednostkaLekcyjna, Long>, JednostkaLekcyjnaRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param idProwadzacy
     * @return Long
     */
    public abstract long countByIdProwadzacy(Wykladowca idProwadzacy);

    /**
     * TODO Auto-generated method documentation
     *
     * @param idPrzedmiotu
     * @return Long
     */
    public abstract long countByIdPrzedmiotu(Przedmiot idPrzedmiotu);

    /**
     * TODO Auto-generated method documentation
     *
     * @param idSali
     * @return Long
     */
    public abstract long countByIdSali(Sala idSali);

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzien
     * @return Long
     */
    public abstract long countByDzien(Dzien dzien);

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupas
     * @return Long
     */
    public abstract long countByGrupasContains(Grupa grupas);

    /**
     * TODO Auto-generated method documentation
     *
     * @param typ
     * @return Long
     */
    public abstract long countByTyp(TypZajec typ);
}
