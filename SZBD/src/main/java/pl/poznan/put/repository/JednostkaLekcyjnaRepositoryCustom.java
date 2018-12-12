package pl.poznan.put.repository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustom;
import pl.poznan.put.model.JednostkaLekcyjna;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.poznan.put.model.Dzien;
import pl.poznan.put.model.Grupa;
import pl.poznan.put.model.Przedmiot;
import pl.poznan.put.model.Sala;
import pl.poznan.put.model.TypZajec;
import pl.poznan.put.model.Wykladowca;

/**
 * = JednostkaLekcyjnaRepositoryCustom
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustom(entity = JednostkaLekcyjna.class)
public interface JednostkaLekcyjnaRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param idProwadzacy
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<JednostkaLekcyjna> findByIdProwadzacy(Wykladowca idProwadzacy, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param idPrzedmiotu
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<JednostkaLekcyjna> findByIdPrzedmiotu(Przedmiot idPrzedmiotu, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param idSali
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<JednostkaLekcyjna> findByIdSali(Sala idSali, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzien
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<JednostkaLekcyjna> findByDzien(Dzien dzien, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupas
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<JednostkaLekcyjna> findByGrupasContains(Grupa grupas, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param typ
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<JednostkaLekcyjna> findByTyp(TypZajec typ, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<JednostkaLekcyjna> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<JednostkaLekcyjna> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);
}
