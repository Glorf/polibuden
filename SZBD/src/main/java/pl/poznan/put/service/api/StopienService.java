package pl.poznan.put.service.api;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import pl.poznan.put.model.Stopien;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = StopienService
 *
 * TODO Auto-generated class documentation
 *
 */
@RooService(entity = Stopien.class)
public interface StopienService extends EntityResolver<Stopien, Long>, ValidatorService<Stopien> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Stopien
     */
    public abstract Stopien findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     */
    public abstract void delete(Stopien stopien);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<Stopien> save(Iterable<Stopien> entities);

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
     * @return Stopien
     */
    public abstract Stopien save(Stopien entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Stopien
     */
    public abstract Stopien findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<Stopien> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<Stopien> findAll();

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
    public abstract Page<Stopien> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Stopien> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     * @param wykladowcyToAdd
     * @return Stopien
     */
    public abstract Stopien addToWykladowcy(Stopien stopien, Iterable<Long> wykladowcyToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     * @param wykladowcyToRemove
     * @return Stopien
     */
    public abstract Stopien removeFromWykladowcy(Stopien stopien, Iterable<Long> wykladowcyToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     * @param wykladowcy
     * @return Stopien
     */
    public abstract Stopien setWykladowcy(Stopien stopien, Iterable<Long> wykladowcy);
}
