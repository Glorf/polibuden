package pl.poznan.put.service.api;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import pl.poznan.put.model.Zespol;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.poznan.put.model.Wydzial;

/**
 * = ZespolService
 *
 * TODO Auto-generated class documentation
 *
 */
@RooService(entity = Zespol.class)
public interface ZespolService extends EntityResolver<Zespol, Long>, ValidatorService<Zespol> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Zespol
     */
    public abstract Zespol findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespol
     */
    public abstract void delete(Zespol zespol);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<Zespol> save(Iterable<Zespol> entities);

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
     * @return Zespol
     */
    public abstract Zespol save(Zespol entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Zespol
     */
    public abstract Zespol findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<Zespol> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<Zespol> findAll();

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
    public abstract Page<Zespol> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Zespol> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespol
     * @param wykladowcyToAdd
     * @return Zespol
     */
    public abstract Zespol addToWykladowcy(Zespol zespol, Iterable<Long> wykladowcyToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespol
     * @param wykladowcyToRemove
     * @return Zespol
     */
    public abstract Zespol removeFromWykladowcy(Zespol zespol, Iterable<Long> wykladowcyToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespol
     * @param wykladowcy
     * @return Zespol
     */
    public abstract Zespol setWykladowcy(Zespol zespol, Iterable<Long> wykladowcy);

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Zespol> findByWydzial(Wydzial wydzial, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @return Long
     */
    public abstract long countByWydzial(Wydzial wydzial);
}
