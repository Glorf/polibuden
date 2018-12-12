package pl.poznan.put.service.api;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
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
 * = JednostkaLekcyjnaService
 *
 * TODO Auto-generated class documentation
 *
 */
@RooService(entity = JednostkaLekcyjna.class)
public interface JednostkaLekcyjnaService extends EntityResolver<JednostkaLekcyjna, Long>, ValidatorService<JednostkaLekcyjna> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return JednostkaLekcyjna
     */
    public abstract JednostkaLekcyjna findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param jednostkaLekcyjna
     */
    public abstract void delete(JednostkaLekcyjna jednostkaLekcyjna);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<JednostkaLekcyjna> save(Iterable<JednostkaLekcyjna> entities);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    public abstract void delete(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return JednostkaLekcyjna
     */
    public abstract JednostkaLekcyjna save(JednostkaLekcyjna entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return JednostkaLekcyjna
     */
    public abstract JednostkaLekcyjna findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<JednostkaLekcyjna> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<JednostkaLekcyjna> findAll();

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public abstract long count();

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
     * @param typ
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<JednostkaLekcyjna> findByTyp(TypZajec typ, GlobalSearch globalSearch, Pageable pageable);

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
     * @param typ
     * @return Long
     */
    public abstract long countByTyp(TypZajec typ);
}
