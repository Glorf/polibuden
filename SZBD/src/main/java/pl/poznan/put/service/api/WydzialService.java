package pl.poznan.put.service.api;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import pl.poznan.put.model.Wydzial;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = WydzialService
 *
 * TODO Auto-generated class documentation
 *
 */
@RooService(entity = Wydzial.class)
public interface WydzialService extends EntityResolver<Wydzial, Long>, ValidatorService<Wydzial> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Wydzial
     */
    public abstract Wydzial findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     */
    public abstract void delete(Wydzial wydzial);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<Wydzial> save(Iterable<Wydzial> entities);

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
     * @return Wydzial
     */
    public abstract Wydzial save(Wydzial entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Wydzial
     */
    public abstract Wydzial findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<Wydzial> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<Wydzial> findAll();

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
    public abstract Page<Wydzial> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Wydzial> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param kierunkiToAdd
     * @return Wydzial
     */
    public abstract Wydzial addToKierunki(Wydzial wydzial, Iterable<Long> kierunkiToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param kierunkiToRemove
     * @return Wydzial
     */
    public abstract Wydzial removeFromKierunki(Wydzial wydzial, Iterable<Long> kierunkiToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param kierunki
     * @return Wydzial
     */
    public abstract Wydzial setKierunki(Wydzial wydzial, Iterable<Long> kierunki);

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param zespolyToAdd
     * @return Wydzial
     */
    public abstract Wydzial addToZespoly(Wydzial wydzial, Iterable<Long> zespolyToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param zespolyToRemove
     * @return Wydzial
     */
    public abstract Wydzial removeFromZespoly(Wydzial wydzial, Iterable<Long> zespolyToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param zespoly
     * @return Wydzial
     */
    public abstract Wydzial setZespoly(Wydzial wydzial, Iterable<Long> zespoly);
}
